package com.example.reactiveexample;

import reactor.core.publisher.Mono;

public class FirstMono {
    public static void main(String[] args) {
        // publisher
        Mono<String> stringMono = Mono.just("우근").log(); // 2개 이상이면 error

//        Mono<Object> stringMono = Mono.error(new RuntimeException()).log();

        stringMono.subscribe(System.out::println);
    }
}
