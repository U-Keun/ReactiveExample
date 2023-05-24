package com.example.reactiveexample;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamToFlux {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("우근", "현진", "성윤");
//        List<String? list = Arrays.asList(); <- 빈 리스트를 이용해도 괜찮다!

        Stream<String> stream = list.stream();

        Flux<String> stringFlux = Flux.fromStream(stream).log();

        stringFlux.subscribe(System.out::println);
    }
}
