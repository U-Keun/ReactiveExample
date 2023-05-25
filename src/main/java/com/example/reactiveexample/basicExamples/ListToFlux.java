package com.example.reactiveexample.basicExamples;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class ListToFlux {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("우근", "현진", "승연", "정아");
        List<String> list = Arrays.asList();
        // list = null은 안 됨!

        Flux<String> stringFlux = Flux.fromIterable(list).log();

        stringFlux.subscribe(System.out::println);
    }
}
