package com.example.reactiveexample.basicExamples;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureExample {
    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.range(1, 10).log();

        /*
         * integerFlux.subscribe(System.out::println,
         *      ex -> ex.printStackTrace(),
         *      () -> System.out.println("Completed"));
         *  아래의 코드는 이 코드에서 요청의 개수를 제한하도록 설정해보는 것이다.
         */
        integerFlux.subscribe(new BaseSubscriber<Integer>() {
            // 구독해서 데이터를 불러올 때 요청을 몇 번 할 지 설정할 수 있다.
            protected void hookOnSubscribe(Subscription subscription) {
//                subscription.request(5); // 요청 5번만!
                for (int i = 0; i < 5; i++) {
                    subscription.request(2);
                } // 요청을 2개씩 5번으로 나눠서 보내기
            }
            // 요청시 onNext 이벤트에서 값을 출력하도록 설정
            protected void hookOnNext(Integer value) {
                if (value == 3) {
                    cancel();
                } // 3이라는 값을 받고 나서 나머지 요청들을 취소한다.
                System.out.println(value);
            }
            // onComplete 이벤트 발생시 메시지 출력하도록 설정
            protected  void hookOnComplete() {
                System.out.println("Completed");
            } // 전체 데이터의 수는 10개인데, 5개만 요청했으므로 onComplete 이벤트가 발생하지 않는다.
            // onError 이벤트 발생시 printStackTrace 메서드 실행하도록 설정
            protected void hookOnError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }
}
