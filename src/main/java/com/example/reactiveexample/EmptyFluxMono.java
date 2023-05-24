package com.example.reactiveexample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EmptyFluxMono {
    public static void main(String[] args) {
        Flux<?> emptyFlux = Flux.empty().log(); //  빈 Flux 객체
//        Flux<?> emptyFlux = Flux.just(null).log(); // null을 집어넣는 것은 안됨(NullPointerException)

        emptyFlux.subscribe(System.out::println); // request(unbounded) -> onComplete()

        String str = null;

//        Mono<?> emptyMono = Mono.justOrEmpty(null).log();
        Mono<?> emptyMono = Mono.justOrEmpty(str).log();

        // 빈 Mono 객체도 Flux와 똑같이 된다!
//        Mono<?> emptyMono = Mono.empty().log();
////        Mono<?> emptyMono = Mono.just(null).log(); 이것도 안 됨.

        emptyMono.subscribe(System.out::println);
    }
}
