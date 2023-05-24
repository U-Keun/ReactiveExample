package com.example.reactiveexample;

import com.example.reactiveexample.pojo.Item;
import reactor.core.publisher.Flux;

public class FluxFlatMap {
    public static void main(String[] args) {
        Flux<Long> longFlux = Flux.just(1L, 2L, 3L);

        // input 하나에 여러개의 output을 원할 때 사용한다.
        Flux<Item> itemFlux = longFlux.flatMap(orderId -> getItems(orderId));

        itemFlux.subscribe(item -> System.out.println(item.getItemName()));
    }

    public static Flux<Item> getItems(Long orderId) {
        return Flux.just(
                new Item(1L, "Item-1"),
                new Item(2L, "Item-2"),
                new Item(3L, "Item-3")
        );
    }
}
