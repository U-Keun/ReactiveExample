package com.example.reactiveexample.schedulerExamples;

import com.example.reactiveexample.pojo.Order;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BoundedElasticScheduler {
    public static void main(String[] args) throws InterruptedException {
        // 각각의 동작들이 어떤 thread에서 작동하는지 출력하도록 설정
        System.out.println("Started with - " + Thread.currentThread().getName());

        Flux<Order> orderFlux = Flux.just(
                new Order(1L, 100.0),
                new Order(2L, 200.0),
                new Order(3L, 300.0)
        );

        Flux<Long> fluxLong = orderFlux
                .publishOn(Schedulers.boundedElastic()) // parallel()과 동작 자체는 크게 다르지 않다.
//                .publishOn(Schedulers.elastic()) // @Deprecated
                .map(order -> {
                    System.out.println("Map 1 with - " + Thread.currentThread().getName());
                    return order.getId();
                })
                .publishOn(Schedulers.boundedElastic()) // 비동기적으로 처리하고 싶은 작업이 있을 때 사용한다.
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
/*
parallel() scheduler와 비교(GPT)

parallel() 스케줄러는 각각의 코어에 고정 크기의 바운드를 가진 워커 스레드 풀을 사용합니다.
CPU 바운드 작업에 적합하며, 병렬 처리 작업에 알맞습니다.
기본적으로 parallel() 스케줄러는 시스템 CPU 코어의 수에 따라 워커 스레드 수를 설정하게 됩니다.

boundedElastic() 스케줄러는 비동기 작업이 아닌 블로킹 작업(예: IO 작업)에 최적화된 고정된 크기의 스레드 풀을 사용합니다.
이 스케줄러는 스레드 수가 제한된 경우에 스케줄링 할 수 있는 더 큰 딜레이와 함께 안정적인 환경을 제공합니다.
블로킹 I/O 처리와 함께 다양한 CPU 리소스를 동시에 사용해야 하는 작업에 적합합니다.
 */
