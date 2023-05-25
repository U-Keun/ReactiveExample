package com.example.reactiveexample.schedulerExamples;

import com.example.reactiveexample.pojo.Order;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ImmediateScheduler {
    public static void main(String[] args) {
        // 각각의 동작들이 어떤 thread에서 작동하는지 출력하도록 설정
        System.out.println("Started with - " + Thread.currentThread().getName());

        Flux<Order> orderFlux = Flux.just(
                new Order(1L, 100.0),
                new Order(2L, 200.0),
                new Order(3L, 300.0)
                );

        Flux<Long> fluxLong = orderFlux
                .publishOn(Schedulers.immediate()) // 호출자의 thread를 즉시 실행한다.
                // 현재 코드에서는 호출자가 main thread
                .map(order -> {
                    System.out.println("Map with - " + Thread.currentThread().getName());
                    return order.getId();
                });

        fluxLong.subscribe(orderId -> System.out.println("Sub with - " + Thread.currentThread().getName()));
    }
}
