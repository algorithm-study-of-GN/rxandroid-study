package com.nobrain.rx_study;

import org.junit.Test;

import io.reactivex.BackpressureStrategy;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class ExampleUnitTest {

    @Test
    public void test() throws Exception {

//        List<String> values = Arrays.asList("1", "2", "3", "4", "5");
//
//        StringBuffer buffer = new StringBuffer();
//        Observable.fromIterable(values) // 1,2,3,4,5
//                .map(Integer::parseInt)
//                .flatMap(input -> Observable.range(input * 10 - 9, 10))
//                .subscribe(s -> buffer.append(s),
//                        Throwable::printStackTrace,
//                        () -> System.out.println(buffer.toString())); // -> 1 2 3 4 5
//
//        Observable.just("One", "Two")
//                .subscribeOn(Schedulers.io())
//                .doOnDispose(() -> log("doOnDispose"))
//                .doOnComplete(() -> log("doOnComplete"))
//                .doOnNext(e -> log("doOnNext", e))
//                .doOnEach(e -> log("doOnEach"))
//                .doOnSubscribe((e) -> log("doOnSubscribe"))
//                .doOnTerminate(() -> log("doOnTerminate"))
//                .doFinally(() -> log("doFinally"))
//                .observeOn(Schedulers.computation())
//                .subscribe(e -> log("subscribe", e));
//
//        Thread.sleep(1000);

        PublishSubject<String> subject = PublishSubject.create();
        subject.toFlowable(BackpressureStrategy.MISSING)
                .observeOn(Schedulers.computation())
                .doOnNext(_1 -> Thread.sleep(100))
                .subscribe(this::log, this::log);

        for (int i = 0; i < 1000000; i++) {
            subject.onNext(String.valueOf(i));
        }

        Thread.sleep(1000000);
    }

    private void log(Throwable throwable) {
        System.out.println("APP/error" + ":" + Thread.currentThread().getName() + ":" + throwable.getMessage());
    }

    private void log(String stage, String item) {
        System.out.println("APP/" + stage + ":" + Thread.currentThread().getName() + ":" + item);
    }

    private void log(String stage) {
        System.out.println("APP/" + stage + ":" + Thread.currentThread().getName());
    }

    // input : 1 - 10 -> output 1 - 100 -> StringBuffer  onComplete, collect
}