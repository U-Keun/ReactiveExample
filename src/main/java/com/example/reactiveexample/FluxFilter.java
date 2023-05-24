package com.example.reactiveexample;

import reactor.core.publisher.Flux;

public class FluxFilter {
    public static void main(String[] args) {
        // .filter 사용
        Flux<String> stringFlux = Flux.just("우근", "형근")
                .filter(s -> s.length() > 3)
                .log();

        stringFlux.subscribe(System.out::println);
    }
}
