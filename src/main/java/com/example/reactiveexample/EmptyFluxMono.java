package com.example.reactiveexample;

import reactor.core.publisher.Flux;

public class EmptyFluxMono {
    public static void main(String[] args) {
        Flux<?> emptyFlux = Flux.empty().log(); //  빈 Flux 객체

        emptyFlux.subscribe(System.out::println); // request(unbounded) -> onComplete()
    }
}
