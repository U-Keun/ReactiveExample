package com.example.reactiveexample;

import com.example.reactiveexample.pojo.Order;
import reactor.core.publisher.Flux;
public class FluxMap {
    public static void main(String[] args) {
        Flux<Order> orderFlux = Flux.just(
                new Order(1L, 100.0),
                new Order(1L, 100.0),
                new Order(3L, 300.0)
                );

        Flux<Long> fluxLong = orderFlux
                .filter(order -> order.getAmount() > 100.0)
                .map(order -> order.getId())
                .log();

        fluxLong.subscribe(System.out::println);
    }
}
