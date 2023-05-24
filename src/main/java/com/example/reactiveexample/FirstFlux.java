package com.example.reactiveexample;

import reactor.core.publisher.Flux;

public class FirstFlux {
    public static void main(String[] args) {
        // Flux는 publisher 역할
        Flux<String> stringFlux = Flux.just("우근", "현진", "승연", "정아")
//                .concatWith(Flux.error(new RuntimeException())) // error 띄우기
                .concatWithValues("16조")
                .concatWith(Flux.just("챌린지팀")).log();

//        // Flux에 데이터를 직접추가
//        stringFlux = stringFlux.concatWithValues("16조");
//
//        Flux<String> secondFlux = Flux.just("챌린지팀");
//
//        // Flux에 다른 Flux 데이터 추가
//        stringFlux = stringFlux.concatWith(secondFlux);

        // Flux에 있는 데이터를 출력하는 방법
        stringFlux.subscribe(System.out::println,
                e -> System.out.println("-----Exception-----"), // exception(error)이 있을 때 나오는 메시지
                () -> System.out.println("-----Success-----")); // exception 없이 잘 동작했을 때 나오는 메시지
    }
}
