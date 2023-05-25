package com.example.reactiveexample.schedulerExamples;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxWithDelay {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Strated with - " + Thread.currentThread().getName());

        // 데이터를 하나 받을 때마다 1초를 기다리고 싶다..!
        Flux<Long> fluxLong = Flux.just(1L, 2L, 3L)
                .delayElements(Duration.ofSeconds(1));
        // delayElements() 메서드는 parallel()을 포함하고 있다.
        // thread 이름을 확인해보면 parallel-1이 나오는 것을 확인할 수 있다.
        // ofSeconds() 이외에도 다른 단위 시간도 사용할 수 있다.

        fluxLong.subscribe(value ->
                System.out.println("Sub with - " + Thread.currentThread().getName()));

        Thread.sleep(4000);
    }
}
