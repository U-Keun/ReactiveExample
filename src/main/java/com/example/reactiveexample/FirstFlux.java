package com.example.reactiveexample;

import reactor.core.publisher.Flux;

public class FirstFlux {
    public static void main(String[] args) {
        // Flux는 publisher 역할
        Flux<String> stringFlux = Flux.just("우근", "현진", "승연", "정아");
        // Flux에 있는 데이터를 출력하는 방법
        stringFlux.subscribe(System.out::println);
    }
}
