package com.example.kotlintest32;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import rx.Single;

import static kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames.throwable;

public class RxJavaRAP extends AppCompatActivity {

    @BindView(R.id.hello_world_salute)
    TextView helloText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_r_a_p);

        Observable.just("First item", "Second item")
                .subscribeOn(Schedulers.io())
                .subscribe();

        //일반적으로 사용되는 주요 스케줄러
        // Schedulers.io()
        // Schedulers.computation()
        // Schedulers.newThread()
        // AndroidSchedulers.mainThread()

        Observable.just("First item", "Second item")
                .doOnNext(e -> Log.d("APP", "on-next:" +
                Thread.currentThread().getName() + ":" + e))
                .subscribe(e -> Log.d("APP", "subscribe:" +
                Thread.currentThread().getName() + ":" + e));

        Observable.just("First item", "Second item").subscribeOn(Schedulers.io())
                .doOnNext(e -> Log.d("APP", "on-next:" +
                Thread.currentThread().getName() + ":" + e))
                .subscribe(e -> Log.d("APP", "subscribe:" +
                Thread.currentThread().getName() + ":" + e));

        /*Observable.just("First item", "Second item").subscribeOn(Schedulers.io())
                .doOnNext(e -> Log.d("App", "On-next:" +
                Thread.currentThread().getName() + ":" + e))
                .observeOn(RxJavaRAP.mainThread()) -> mainThread()가 에러가 나는데 왜 나는지 알아보기
                .subscribe(e -> Log.d("APP", "subscribe:" + Thread.currentThread().getName() + ":" + e));*/

        // 옵저버블의 흐름 조사

        //코드가 어떻게 동작하고 있는지 이해하고 싶을 때 옵저버블의 단계 안에서 로그를 남기는 것은 매우 강력한 도구라 할 수 있다.

        PublishSubject<Integer> observable = PublishSubject.create();

        observable
                .observeOn(Schedulers.computation())
                .subscribe(v -> log("s", v.toString()), this::log);
        for(int i = 0; i < 1000000; i++){
            observable.onNext(i);
        }

       /* observable.toFlowable(BackpressureStrategy.MISSING).observeOn(Schedulers.computation()).subscribe(v -> log("s", v.toString()), this::log);*/

        // 싱글
        Single.just("One item");
        Single.just("One item").subscribe((item) ->{
          log(item);  
        }, (throwable) -> {
           log(throwable); 
        });
        
        // 메이비
        // 메이비 타입은 싱글 타입과 매우 유사하다. 하지만 마지막에 아이템이 반환되지 않을 수도 있다.

        // 하지만 .subscribe()는 onSuccess(받은 아이템을 위해), onError(에러를 다루기 위해), onComplete(아이템을 처리하고 마지막 행위를 하기 위해)를 다루려면 인자와 함께 호출해야한다.


    }

    private void log(String item) {
    }


    private void log(Throwable throwable){
        Log.e("APP", "Error", throwable);
    }

    private void log(String s, String toString) {
    }
  /*  private void log(String stage, String item){
        Log.d("APP",stage +:+ + Thread.currentThread().getName() + ":" + item);
    }
    private void log(String stage){
        Log.d("APP", stage +:+ + Thread.currentThread().getName());
    }
    */


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_r_a_p);

        ButterKnife.bind(this);

        Observable.just("Hello! please use this app responsbly!").subscribe(new Consumer<String>(){
           @Override
           public void accept(String s){
               helloText.setText(s);
           }
        });
    }*/
    // 주식 데이터 값 객체

    // 앱이 주식 업데이트를 처리할 때 문자열 데이터만으로는 작업하기가 매우 어렵다는 것을 알 수 있다.

    // 레트로람다

    // 옵저버플

    // 옵저버블은 전손됭 데이터에 관해서 관찰(observe)할 수 있는 데이터의 출처다. 대부분의 상황에서(적어도 이 책에서는) 옵저버플 클래스를 이용해 작업할 것이다.

    // 기본적으로 리액티브 방식으로 데이터 스트림을 활용하는 범용 인터페이스다.

    // 옵저버블을 생성할 수 있는 다양한 방법이 있다. 가장 간단한 방법은 .just() 메소드를 이전에 사용한 것처럼 하는 것이다.

    // 항상은 아니지만 옵저버블은 누군가 구독해야 활성화된다.

    // 핫 그리고 콛르 옵저버블

    // 디스포저블 옵저버블의 생애 주기를 제어하기 위해 사용하는 도구

    // 만약 옵저버블이 생성하는 데이터의 스트림이 끝이 없다면 영원히 활성화 상태로 남아 있어야 한다는 의미가 된다. 서버에는 문제가 없을수도 있지만 안드로이드에서는 심각한 문제를 초래할 수도 있다.
    // 일반적으로 메모리 누수의 원인이 된다.

    // 스케줄러
    // 일반적인 문서에 설명돼 있듯이, 스케줄러는 현재 또는 나중에 동작해야 할 단위의 일정을 만드는 것이다. 실제로는 스케줄러를 이용해 코드가 어디서 실행돼야 하는지 제어할수 있다는 의미이자 이것은 특정한 스레드(thread)를 선택할 수 있다는 뜻이다.

    // 대부분 구독자는 백그라운드 스레드 에서 긴 시간 동안 실행된 작업을 하므로

    //플로어블은 옵저버블의 특별한 타입으로 간주할 수 있다.(하지만 내부적으로는 아니다.) 대부분은 옵저버블과 비슷한 메소드의 특징을 갖고 있다.


    //필요한 레트로핏 디펜던시와 프로가드 설정 방법

    // 간단한 HTTP 요청을 만들기 위한 레트로핏 인터페이스 객체 설정 방법

    // JSON 요청 분석 방법

    // RxJava를 이용한 HTTP 요청을 주기적으로 실행하는 방법

    // 반환된 데이터를 안드로이드 UI에 표시하는 방법

    // 레트로핏 설정




}