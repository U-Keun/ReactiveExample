package com.example.reactiveexample.basicExamples;

import reactor.core.publisher.Flux;

public class ArrayToFlux {
    public static void main(String[] args) {
        String[] array = new String[] {"우근", "성윤", "현진"};
//        String[] array = new String[] {}; // 빈 배열도 들어갈 수 있다.
//        String[] array = null; // NullPointerException

        Flux<String> stringFlux = Flux.fromArray(array).log();

        stringFlux.subscribe(System.out::println);
    }
}
