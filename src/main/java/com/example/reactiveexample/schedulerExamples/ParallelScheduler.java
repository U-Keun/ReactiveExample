package com.example.reactiveexample.schedulerExamples;

import com.example.reactiveexample.pojo.Order;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelScheduler {
    public static void main(String[] args) throws InterruptedException {
        // 각각의 동작들이 어떤 thread에서 작동하는지 출력하도록 설정
        System.out.println("Started with - " + Thread.currentThread().getName());

        Flux<Order> orderFlux = Flux.just(
                new Order(1L, 100.0),
                new Order(2L, 200.0),
                new Order(3L, 300.0)
        );

        Flux<Long> fluxLong = orderFlux
                .publishOn(Schedulers.parallel()) // 현재 줄부터 새로운 thread(parallel-2)로 작업한다.
                // .publishOn(Schedulers.newParallel("my-parallel")) // thread에 이름을 붙일 수 있다.
                .map(order -> {
                    System.out.println("Map 1 with - " + Thread.currentThread().getName());
                    return order.getId();
                })
                .publishOn(Schedulers.parallel()) // 현재 줄부터 새로운 thread(parallel-1)로 작업한다.
                .map(order -> {
                    System.out.println("Map 2 with - " + Thread.currentThread().getName());
                    return order * 2;
                });

        fluxLong.subscribe(orderId -> System.out.println("Sub with - " + Thread.currentThread().getName()));

        /*
        main thread를 재우지 않으면
        parallel() 메서드가 실행된 순간 main thread의 작업이 종료된 것으로 판단하고
        실행이 종료된다. -> parallel-1(또는 2)이 한 번은 나옴!
         */
        Thread.sleep(3000);
    }
}
