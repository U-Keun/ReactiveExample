package com.example.reactiveexample;

import reactor.core.publisher.Mono;

import java.util.Optional;

public class OptionalToMono {
    public static void main(String[] args) {
//        Optional<String> optional = Optional.of("우근");

        Optional<String> optional = Optional.empty();

        Mono<String> stringMono = Mono.justOrEmpty(optional).log();

        stringMono.subscribe(System.out::println);
    }

}
