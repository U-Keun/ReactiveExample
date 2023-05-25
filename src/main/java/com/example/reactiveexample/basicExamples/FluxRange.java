package com.example.reactiveexample.basicExamples;

import reactor.core.publisher.Flux;

public class FluxRange {
    public static void main(String[] args) {
        // 5부터 숫자 10개
        Flux<Integer> integerFlux = Flux.range(5, 10).log();

        integerFlux.subscribe(System.out::println);
    }
}
