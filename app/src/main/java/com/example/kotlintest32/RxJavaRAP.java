package com.example.kotlintest32;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import java.util.concurrent.TimeUnit;
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
import rx.plugins.RxJavaPlugins;

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

        /*Observable.interval(0, 5, TimeUnit.SECONDS)
                .flatMap(
                        i -> yahooService.yqlQuery(query, env).toObservable()
                ).subscribeOn(Schedulers.io())
                .map(r -> r.getQuery().)*/

        Observable.just("One").doOnNext(i -> {
            throw new RuntimeException();
        }).subscribe(item -> {
            log("subscrive", item);
        });

        //여기서는 .doOnNext() 연산자에 예외가 발생하기 때문에 전체 애플리케이션이 종료된다. .subscribe() 메소드의 두 번째 인수를 사용하면 쉽게 완하 가능

        Observable.just("One").doOnNext(i -> {
            throw new RuntimeException();
        }).subscribe(item -> {
            log("subscribe", item);
        }, throwable -> {
            log(throwable);
        });

        // 좀 더 간결하게 다음처럼 할 수 있다.

        /*Observable.just("One")
                doOnNext(i -> {
                    throw new RuntimeException("Very wrong");
                }).subscribe(item -> log("subscribe", item), this::log);*/ // 책에서 나오는데로 하긴 했는데 안되는 부분이 있다. ; 이걸 찍으라던지 doOnNext이걸 쓰라던지 좀 안되는 부분이 많은거 같다.

        // onEsceptionResumeNest() 사용

        //원본이 실패한 경우 백업 옵저버블에 플러그인하는 메커니믖으로 사용할 수 있다. 이는 원격 서비스에서 새로운 주식 시세 데이터를 가져올 수 없는 경우를 처리하기 위해 이우 절에서 할 내용

        //onExceptionResumeNext()

        Observable.<String>error(new RuntimeException("Crash!"))
                .onExceptionResumeNext(Observable.just("Second"))
                .subscribe(item -> {
                    log("subscribe", item);
                }, e -> log("subscribe", e));

        //이번에는 로그에서 예외가 표시되지 않고 두 번째(Second)아이템이 정상적으로 처리된다.
        //따라서 예외가 발생하면 onExceptionResumeNext() 연산자에 도달하고 두 번째 옵저버블에서 시퀀스를 재개했다.
        // 또한 이 경우에 에러를 발생시키고 작동을 멈추는 특수옵저버블을 사용

        //onErrorReturn

        Observable.<String>error(new Error("Crash!"))
                .onErrorReturn(throwable -> "Return")
                .subscribe(item -> {
                    log("subscribe", item);
                }, e -> log("subscribe", e));

        //OnErrorReturnItem

        Observable.<String>error(new Error("Crash!"))
                .onErrorReturnItem("ReturnItem")
                .subscribe(item -> {
                    log("subscribe", item);
                }, e -> log("subscribe", e));

        // 안드로이드 UI의 오류 표시

        // 오류를 기록하고 앱을 죽지 않게 할 뿐만 아니라, 사용자에게 어떤 일이 발생했는지 항상 알려줃도록 처리하는 것이 중요하다. 다른 종류의 실패에 대해 수행할 수 있는 몇가지 패턴이 있다.

        // 주식 업데이트를 로드할 수 없가나 데이터가 없는 경우의 빈 상태 화면 그리고 데이터를 초기에 가져오기가 실패했지만 SQLite 데이터베이스에 저장된 데이터를 다시 가져올 때의 토스트 알람

        // 빈 상태 화면

        // 빈 상태 화면 패턴은 인터넷(온라인) 또는 SQLite데이터베이스(오프라인)에서 데이터를 로드 할 수 없을때 사전 정의된 기본 화면을 표시할 것

        // 예를 들어 사용자가 인터넷에 연결 할 수 없는 상태에서 처음으로 앱을 시작하면 이러한 현상이 발생가능

        // 토스트 알림

        // 중앙 집중식 에러 로깅

        // 중앙 처리기기

        /*Observable.<String>error(new Error("Crash!"))
                .doOnError(ErrorHandler.get())
                .subscribe(item -> {
                    log("subscribe", item);
                }, ErrorHandler.get());*/

        //이 접근법의 장점은 이와 같은 여러 핸들러가 있을 수 있다는 것이다. 개발자는 전체 에외 대신에 경고만 표시하는 데 사용할 수 있는 WarnOnlyHandler나 애플리케이션의 특정 부분에 대해서만 일부 원격 서비스에 오류를 기록하는 핸들러를 사용할 수 있다.

        //RxJava 플러그인 사용
        //이전 방법의 대안(또는 개선)은 다음 호출을 사용하는 것이다.

        //위와 같이 옵저버블 흐름이 제공하는 메커니즘에 잡히지 않은 모든 예외(또는 에러)를 위한 전역 핸들러를 설정할 것이다.

        //RxJavaPlugins.setErrorHandler는 Consumer<Throwable> 인터페이스를 사용하기 때문에 오류를 실제로 처리하기 위해 만든 클래스에 연결할 수 있다.

        //RxJavaPlugins.setErrorHandler(ErrorHandler.get());

        // 이제는 이전에는 잡히지 않던 모든 오류가 최종 핸들러로 전달된다.
    }

    //StorIO로 SQLite 데이터 읽기

    // 리졸버 얻기

    // 이전에는 Putresolver를 구현했고 GetResolver를 위한 스텁 인터페이스를 만들었다 여기서는 빠진 인터페이스를 완전히 구현해 시작한 작업을 마무리해보자.

    // 같은 패키지에 StockUpdataPutResolver와 같이 StockUpdateGetResolver라는 클래스를 생성하고 DefaultGetResolver를 상속한다.


    // 오류의 전역 핸들러로 사용할 것이므로 클래스를 싱글컨으로 만드는 것이 맞다. 이렇게 하려면 다음 줄을 추가



    public static class ErrorHandler implements Consumer<Throwable>{

        @Override
        public void accept(Throwable throwable) /*throws Exception */{ //이쪽을 지우면 되는데 왜 지워야 하는지 이유를 모름 이유 찾기
            Log.e("APP", "Error on" + Thread.currentThread().getName() + ":", throwable);
        }
    }

    private void log(String item) {
    }

    private void log(Throwable throwable){
        Log.e("App", "Error on" + Thread.currentThread().getName() + ":", throwable);
    }


    private void log(String item, Throwable e) {
        //아 여기에 쓰면 되는거네
    }


    private void log2(Throwable throwable){
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