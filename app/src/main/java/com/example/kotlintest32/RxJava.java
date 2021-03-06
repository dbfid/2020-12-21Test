package com.example.kotlintest32;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;
import java.util.function.Consumer;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import kotlin.jvm.JvmOverloads;
import rx.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.internal.operators.observable.ObservableError;
import kotlin.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
/*import io.reactivex.Observable;*/ // 이걸하니까 에러남 그래서 rx.Observable로 바꿈
import rx.schedulers.Schedulers;

import static hu.akarnokd.rxjava.interop.RxJavaInterop.*;
/*import rx.Observable;*/ //  위에있는 옵저버플을 이줄처럼 사용하느것도 가능하다.

public class RxJava extends AppCompatActivity {

    private static Object SQLiteTypeMapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);


        //observeOn()은 . subscribeOn()과는 조금 다르게 동작함

        Observable.just("One","Two","Three")
                .doOnNext(i -> log("doOnNext", i))
                .observeOn(Schedulers.newThread())
                .doOnNext(i -> log("doOnNext", i))
                .observeOn(Schedulers.computation())
                .subscribe(i -> log("subscribe", i));

        //경험으로부터 도출한 법칙
        //옵저버블의 흐름에 가능한 한 앞쪽에 .subscribeOn()을 배치하라 그러ㅕ면 ㅇ어떤 스케줄러가 구독을 시작하는지 확인하는 데 도움이 된다.

        //.subscribe() 바로 앞에 .observeOn()을 배치하라. .observeOn()을 하나만 사용하는 경우

        //마지막으로 .subscribeOn() 및 .observeOn() 호출이 필요하지 않으면 생략해야 한다.

        //병렬 처리 달성

        //기본적으로 RxJava의 코드는 동기적으로 실행됨. 즉, 동시성이 없으며 코드 블록은 다음과 같다.

        Observable.just("One", "Two")
                .doOnNext(i -> log("doOnNext", i))
                .subscribe(i -> log("subscribe", i));

        //코트가 다른 스레드에서 실행되면 다른 결과가 발생

        // 두 개의 코드가 동시에 실행될 수 있지만 옵저버블을 작성하는 동안 정의로 흐름의 단계는 항상 정의돈 순서대로 실행된다.

        Observable.just("One", "Two")
                .doOnNext(i -> log("doOnNext", i))
                .observeOn(Schedulers.computation())
                .subscribe(i -> log("subscribe", i));

        Observable.range(1, 1000)
                .map(Object::toString)
                .doOnNext(i -> log("doOnNext", i))
                .observeOn(Schedulers.computation())
                .subscribe(i -> log("subscribe", i));

        // 500이라는 숫자는 499이전에는 절대 보이지 않을 것

        //때로는 여러 값을 한 번에 내보내거나 순서와는 상관없이 완료하고 싶을 때가 있다.

        // 병령 처리를 위한 코드 구조 잡기

        // 옵저브블과 구독이 만들어지면 같은 블록이 동시에 두 개의 코드를 실행하는건 불가능
        // 그러나 이에 대한 해결 방법이 있다. 필요할 때마다 옵저버블을 계속 만들면 된다. 그냥 개노가다잖아 ㅋㅋㅋ


        // StorIO를 이용한 데이터 입력
        // 나중에 사용할 수 있도록 데이터를 로컬에 보관하는 방법은 여러 가지가 있다

        // 안드로이드에서 가장 강력하고 편리한 방법은 번들로 제공되는 SQLite 데이터베이스를 사용하는 것이다.

        // 안드로이드에 기본으로 내장돼 사용할 수 있는 간단한 SQL 데이터베이스다.

        //SQLite에는 기본적으로 RxJava에 대한 인터페이스가 없다 특정 라이브러리를 다시 사용해야 한다.
        //작업을 위한 완벽한 도구는 아르템 지나툴린의 StorIO라이브러리다.
        //풍부한 API로 도메인 클래스를 SQL 호출에 매핑하는 수단을 제공하기 때문에 훌륭한 도구라고 할 수 있다.

        //StorIO에 대해 다뤄야 할 많은 것들이 있다.
        //배웟던 것들을 활용해 메인 스레드에 집중적인 쓰기 작업을 줄여서 애플리케이션이 항상 반응적이고 기민하게 움직일 수 있도록 만들 것이다.
        //StorIO설정

        //가장 먼저 StorIO에 대한 디펜던시를 설정해야 한다. 이전처럼 특별한 것은 없고 또 빌드 그레이드에 줄을 추가해주면 된다.

        //또한 어노테이션 프로세서를 사용해 SQLite 데이터베이스와 인터페이스하는 코드를 더 빠르게 작성할 수 있도록 만든다

        //StorIO구성
        //StorIO에 데이터를 보존하기 위한 일반적인 리액티브 호출은 다음과 같다

        /*StorIOSQLite storIOSQLite = ...;
        Object entitiesToPersit;
        storIOSQLite
                .put()
                .object(entitiesToPersit)
                .prepare()
                .asRxSingle()
                .subscribe();

        */

        //하지만 이것을 사용하기 전에 몇 가지 알아두자

        // 어떻게 SQL 데이터를 도메인 클래스에 매핑하는가?
        // 어떻게 StorIOSQLite 인터페이스를 얻는가?

        // 나중에 StorIOSQLite 인터페이스에 연결될 데이터 매핑부터 시작

        // 상수
        // 저장할 항목(StockUpdate)에 관련된 이름과 쿼리
        class StockUpdateTable{
            static final String TABLE = "stock_updates";

            /*static*/ class Columns{ //static을 왜 쓰면 안되는건지 알아보기
                static final String ID = "_id";
                static final String STOCK_SYMBOL = "stock_symbol";
                static final String PRICE = "price";
                static final String DATE = "date";
            }
            private StockUpdateTable(){
            }

             /*static*/ String createTableQuery(){ //여기도 마찬가지 버전이 업데이트 되면서 바뀐건가? 책이랑 내용이 다름
                return "CREATE TABLE" + TABLE + "("
                        + Columns.ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + Columns.STOCK_SYMBOL + "Text NOT NULL, "
                        + Columns.DATE + "LONG NOT NULL, "
                        + Columns.PRICE + "LONG NOT NULL"
                        + ");";
            }
        }

        //Columns 서브 클래스에는 나중에 데이터를 쿼리하고 업데이트하는 데 사용할 열 이름을 포함
        //createTableQuery()와 같은 메소드는 첫 번째 애플리케이션 실행시 데이터베이스를 설정하는 데 사용된다.

        //쓰기 리졸버 생성

        // 5장에서는 데이터를 저장할 것이므로 항목에 대한 PutResolver 클래스를 작성하는 방법만 다룰 것이다. 자동으로 생성하는 것이 가능

        //PutResolver가 여러 데이터베이스 테이블을 수정하는 경우 종종 필요하다.

        //StockUpdatePutResolver라는 클래스를 만들다.

        //DefaultPutResolver는 PutResolver 인터페이스를 상속하고 도메인 객체의 데이터를 SQL 데이터에 매핑하기 위한 편리한 인터페이스를 제공하는 추상 클래스다.

        //StockUpdate 클래스에는 ID가 있다.

        //편의상 SQLite에서 쉽게 이해할 수 있는 long 데이터 형식을 사용한다.

        // 결국 getPrice()는 다음과 같이 나타난다.


        // 액티비티의 생명 주기와 매우 유사하지만, 다음과 같은 몇 가지 새로운 메소드가 있다.

        // onAttach()

        // onCreateView()

        // onActivityCreated()

        // onDestroyView()

        // onDetach()

        // 프래그먼트의 사용을 깊숙이 다루는 것은 이 책의 범위를 벗어나지만, 액티비티에 적용되는 누수에 대한 같은 규칙이 프래그먼트에도 적용된다는 점을 기억

        // 리소스 누수

        // 메모리 누수



    }

    /*private static StorIOSQLite INSTANCE;

    public synchronized static StorIOSQLite get(Context context){
        if(INSTANCE != null){
            return INSTANCE;
        }
        INSTANCE = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new StorIODbHelper(context))
                .addTypeMapping(RxJava.class, SQLiteTypeMapping.
                        <StockUpdate>builder()
                .putResolver(new StockUpdatePutResolver())
                .getResolver(createGetResolver())
                .deleteResolver(createDeleteResolver))
                .build())
        .build();
    }

    private static Object createGetResolver() {
    }*/







    /*@JvmOverloads
    public int OnCreate2(Bundle savedInstanceState){
        Observable.range(1, 100)
                .map(SandBox::ImportantLongTask)
                .map(Object::toString)
                .subscribe(e -> log("subscribe", e));

        public static int importantLongTask(int i){
            try{
                long minMillis = 10L;
                long maxMillis = 1000L;
                log("Working on " + i);
                final long waitingTime = (long) (minMillis + (Math.random() + maxMillis - minMillis));
                Thread.sleep(waitingTime);
                return i;
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }*/

    private void log(String s) {
    }

    private void log(String doOnNext, String i) {
    }
    // 리액티브 프로그래밍은 데이터가 통지될 때마다 관련 프로그램이 반응(reaction)해 데이터를 처리하는 프로그래밍 방식

    /*public static final void hoFunTest(@NotNull Function2 argFun){
        Intrinsics.checkParameterIsNotNull(argFun, "argFun");
        argFun.invoke(Integer.valueOf(10), Integer.valueOf(20));
    }

    public static final void main(@NotNull String[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        hoFunTest((Function2)null.INSTANCE);
    }*/

    // 소스는 hoFunTest()라는 고차 함수를 선언하고 고차 함수 내에 매개변수로 함수 타입을 선언
    // 람다 함수를 이용하기 위해 Function2가 정의, Intrinsics.checkParameterIsNotNull()을 이용한 null 점검 코드
    // 개발자가 작성한 코드보다 더 많은 코드가 복잡하게 추가되는것을 확인 가능

    // 고차 함수를 호출하고 고차 함수 내에서 매개변수로 전달한 함수를 다시 호출하는 자극히 정상적인 예
    // 하지만 고차 함수의 이용이 빈번하다면 람다 함수 때문에 많은 코드가 실행되어 성능 문제가 발생할 수 있다.

    // 고차 함수를 호출하고 고차 함수 내에서 매개변수로 전달할 함수를 다시 호출하는 자극히 정상적인 예이지만, 고차 함수의 이용이 빈번하다면 람다 하뭇 때문에 많은 코드가 실행되어 성능 문제가 발생 가능

    // 이처럼 고차 함수 호출이 빈번하여 성능에 영향을 미칠 때는 인라인(inlune) 함수가 대안일 수 있습니다.

    // 소스_inline 함수의 자바 변형
    public static final void main(@NotNull String[] args) {
        int x2 = 20;
        int x1 = 10;
        int var10000 = x1 + x2;
    }

    //소스 _ 일반 함수 자바 변형

    public static final int normalFun1(int a, int b) {
        return a + b;
    }

    public static final void main2(@NotNull String[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        normalFun1(10, 20);
    }

    //자바로 변형된 소스를 보면 일반 함수 이용 시 특별하게 추가된 부분은 없다. 람다 함수의 이용이 없다 보니 개발자가 작성한 코드가 거의 그대로 자바로 작성된다. 일반 함수를 선언할 때도 inline을 추가하여 작성 할 수 있고 이렇게 하면 인라인으로 선언된 함수의 내용이 이 함수를 호출하는 곳에 컴파일 단계에서 정적으로 포함되지만, 별로 이득이 없다는 이야기.
    // 이런 이유로 코틀린에서는 일반 함수에 inline을 사용하면 다음과 같은 경고 메시지를 출력

    // inlining works best for functions with lambda parameters

    // 즉, inline은 람다 함수를 매개변수로 이용하는 곳에 사용하라는 이야기

    public static final Function1 closureTest(final int x) {
        String var1 = "argument" + x;
        System.out.println(var1);
        return (Function1) (new Function1() {

            public Object invoke(Object var1) {
                return this.invoke(((Number) var1).intValue());
            }

            public final int invoke(int it) {
                return it * x;
            }
        });
    }

  /*  public class ClosureTest{
        List<Integer> list = Arrays.asList(1,2);

        private void test(){
            int no = 10;
            int sum = 0;
            list.forEach(t ->
                    System.out.println(sum =+ t) // <-- 여기서 에러가 난다.
            );
        }
        public static void main(String[] args){
            new ClosureTest().test();
        }
    }
*/

    //람다 표현식을 t -> System.out.println(sum += t)로 작성했는데 이 람다에서 외부 변수인 sum에 접근하고 있다.

    //그런데 외부 변숫값을 변경하려는 시도는 컴파일 에러다. 람다에서 외부 변수는 fional 입니다. 따라서 데이터를 참조해서 이용할 수는 있어도 변경은 불가능합니다.

    void some() throws Exception {

    }

    void test() {
        try {
            some();
        } catch (Exception e) {

        }
    }


    public final class TestKt {
        public final void some2(@NotNull Sub $receiver, int data) { // static을 없애니까 사라진다.
            Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
            $receiver.setData(data);
            $receiver.superFun();
        }

        public final void main(@NotNull String[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            Sub obj = new Sub();
            obj.some1(10);
            some2(obj, 100);
        }

    }

    public class Super {
        public void superFun() {
            String var1 = "Super...superFun....";
            System.out.println(var1);
        }
    }

    public final class Sub extends Super {
        private int data = 20;

        public final int getData() {
            return this.data;
        }

        public final void setData(int var1) {
            this.data = var1;
        }

        public void superFun() {
            String var1 = "Sub .. superFun.... " + this.data;
            System.out.println(var1);
        }

        public final void some1(int data) {
            this.data = data;
            this.superFun();
            super.superFun();
        }
    }

    private int no;

    public final int getNo() {
        return this.no;
    }

    public final void setNo(int var1) {
        this.no = var1;
    }

    public final class Test {

        //소스 _ 이용 측 대상에 의한 자바 변형 결과
        private int no1;
        private String name1;

        @TestAnnotation4
        private int age;

        private String phone;
        private String email;

        @TestAnnotation4
        @TestAnnotation5
        public final int getNo1() {
            return this.no1;
        }

        public final void setNo1(int var1) {
            this.no1 = var1;
        }

        public final void setName(String var1) {
            this.name1 = var1;
        }

        public final int getAge() {
            return this.age;
        }

        public final void setAge(int var1) {
            this.age = var1;
        }

        public final String getPhone() {
            return this.phone;
        }

        public final void setPhone(@TestAnnotation4 @NotNull String var1) {
            this.phone = var1;
        }

        public final String getEmail() {
            return this.email;
        }

        public final void setEmail(@NotNull String var1) {
            this.email = var1;
        }

        public Test(@TestAnnotation4 @NotNull String email) {
            super();
            this.email = email;
            this.no1 = 10;
            this.name1 = "kkang";
            this.age = 30;
            this.phone = "0100000";
        }

    }

    //소스 _ 자바 코드

    public @interface JavaAnnotation {
        int intValue();
        //thtm _ 자바 이노테이션
        String stringValue();
    }

    // 리졸버 얻기

    // Stock Update 항목 읽기 메커니즘을 통합할 것이다.

    // 마지막으로 우리는 원격 데이터 가져오기에 실패했음을 사용자에게 알리고 데이터를 가져올 수 있는 로컬 수단으로 처리해야 한다.

    // 커서 열 읽기


    // 데이터 변환

    // 데이터베이스에 올바르게 저장하기 위해 Date와 BigDecimal 유형을 인코딩해야 했으므로,이제 디코딩을 해야 한다.

    private Date getDate(long dateLong){
        return new Date(dateLong);
    }
    // 위 코드는 Long 형식의 밀리초를 java.utill.Date 형식으로 간단하게 변환한다.

    private BigDecimal getPrice(long priceLong){
        return new BigDecimal(priceLong).scaleByPowerOfTen(-4);
    }

    // Long 형식을 취해 BigDecimal로 변환한다. 그러나 소수점 네 자리까지 값을 조정했으므로 이제 역순으로 처리해야 한다.

    // StockUpdate 객체 생성

    // 끝으로 모든 데이터를 사용할 준비가 됐으므로, 적절한 값을 사용해 생성자를 호출하고 StockUpdate 객체를 만들 수 있다.

   // 유형 매핑 구성

    // GetResolver를 사용하기 전에 해야 할 마지막 단계는 유형 매핑 구성에 추가하는 것이다.

  // 여기서 스텁 인터페이스를 만드는 데 사용한 다음 줄을 찾아야 한다.

    // StockUpdate 항목에 대한 오프라인 대체

    // 이제 오프라인으로 보기 위해 데이터베이스에서 StockUpdate 항목을

    //StorIO 데이터베이스 쿼리

    // 다음 줄은 반환될 객체 유형을 지정한다


    // 완성된 onExceptionResumeNext() 블록

    // 사용자에게 오류에 대해 알리기

    // 원격의 주식 데이터를 읽을 수 없을 때 알림을 처리하는 방법에 대해서도 간랸하게 알아보자.

    // 누락된 삭제 리졸버

    // StockUpdate 객체용 StorIO 구성을 완료해 CRUD(생성, 읽기, 수정, 삭제)의 네 가지 작업을 모두 지원한다.

    // 생명 주기검토

    // 안드로이드 액티비티 생명 주기는 특별한 메소드가 호출되는 순서와 조건을 보여준다.

    //onCreate()

    //onStart()

    //onResume()

    //onPause()

    //onStop()

    //onDestroy()

    //개발자는 액티비티를 특정 생명 주기에서 호출할 수 있도록 코드를 재정의(override)해야 한다.
}
//위의 소스를 보면 람다 함수 내에서 closureTest() 함수의 변수에 접근하는 경우와 그렇지 않은 경우의 차이가 명확하게 보입니다. 내부적으로 closureTest() 함수의 변수까지 포함된 객체를 만들어 반환하므로 함수가 종료되더라도 closureTest() 함수의 변수를 그대로 이용할 수 있습니다.


//inline이 추가된 hoFunTest() 함수와 이 함수를 호출하는 main() 함수 두 개를 작성했지만 자바로 변형된 결과를 보면 hoFunTest() 함수는 없다. main() 함수 하나가 정의되어 있고hoFunTest()함수의 내용은 컴파일 단계에서 main() 함수에 포함됨

//결국, inline을 추가하여 함수를 정의하는 것과 그렇지 않은 것은 실행 결과는 같지만, 런타임 때 다르게 실행됩니다. 컴파일 단계에서 inline으로 정의한 함수의 내용이 호출되는 곳에 정적으로 포함되므로 런타임 때 함수 호출이 그만큼 줄고 성능에 도움이 된다는 이야기

//inline을 일반 함수에는 사용 불가능??
// 일반 함수 선언에도 inline을 추가할 수 있다. 하지만 일반 함수에서는 인라인의 이점이 별로 없다.

// 코틀린에서는 람다 함수에서 외부 함수의 데이터 접근뿐 아니라 변경도 가능하다

// 클로저에 의해 내장 함수에서 외부 함수의 데이터에 접근할 수 있는 것을 살펴보았습니다. 그런데 외부 함수의 데이터를 이용하는 것뿐 아니라 변경할 수도 있습니다.


// 밀어내기와 끌어오기

// Observable과 이와 간련한 Observer 타입 시그니처는 이벤트 밀어내기(push)를 지원한다.

// 이들은 보통 다음 절에서 다룰 비동기 속성을 수반한다. 한편 Observable 타입은 비동기 시스템의 흐름 제어나 배압(backpressure)에 대한 접근 방식으로서 (보통 비동기 끌어오기(pull) 혹은 리액티브 끌어오기라 일컫는)

// 메모리 내부 데이터

/*final class Person {
    private final String name;
    private final Instant registered;

    public Person(String name, Instant registered) {
        this.name = name;
        this.registered = registered;
    }

    public Person(String name) {
        this(name, Instant.now());
    }

    public String getName() {
        return name;
    }

    public Instant getRegistered() {
        return registered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(registered, person.registered);

    }
}*/

    /*Observable<Integer> source =
            Observable.create((ObservableEmitter<Integer> emitter) -> {
                emitter.onNext(100);
                emitter.onNext(200);
                emitter.onNext(300);
                emitter.onComplete();
            });
    source.subscribe(new Consumer<Integer>(){
       @Override
       public void accept(Integer data) throws Exception{
           System.out.println("Result : " + data);
        }
    });
*/

//여기서 주의해야 할 몇가지
    //.client는 우리가 사용할 실제 HTTP 클라이언트를 설정 함 이 상황에서는 OkHttp다.
    // .addCallAdapterFactory()를 호출하고 RxJava2CallAdapterFactory를 사용
    //GsonConverterFactory와 함께 .addConverterFactory()를 호출하면 JSON 응답 분석을 위한 지원을 자바 객체에 추가한다.

//서비스 생성
    //레트로핏 객체를 초기화한 후 레트로핏 서비스 객체를 만드는게 가능
    /*public YahooService create(){
        return retrofit.create(YahooService.class);
    }*/

    //이 호출은 우리가 만든 인터페이스를 받아들여 자바 리플렉션 마법과 프록시를 사용해 완전히 작동하는 객체로 변환한다.

    //요청 데이터 변환
    //API로부터 반환된 데이터는 지금의 경우처럼 쉽게 사용하기 어려울 수 있다.
    // JSON분석
    // 가장 먼저 JSON 분석부터 시작하자. 대부분은 Gson 라이브러리로 다룰 수 있다. 하지만
    //이를 포함하는 데 사용할 트리(tree) 클래스를 정확히 만들어야 한다.

    //서로 다른 중첩 수준에는 여러 클래스가 필요하다. 이를 위해 packt.reactivestocks.yahoo.json 패키지에 만들어 넣는다.


    // 데이터 불러오기
    // HTTP 요청을 하고 주식 데이터를 받기 위해 지금까지 만들었던 조각들을 합치는과정

    //JSON 값 객체 변환

    //지금의 JSON같은 객체 트리로는 작업하기가 까다로움
    // 반부패 계층
    // 중첩된 객체 벗기기
    // 트리 클래스의 중첩된 객체를 벗겨내려면 간단하게 .map()을 호출한다.

    //List 벗기기

    //객체 변환

    //마지막으로 StockUpdata원본 클래스를 사용해 애플리케이션에서 작업하기로 했기 때문

    //주기적인 업데이트

    //Observable.interval(0, 5. TimeUnit.SECONDS
    //UT에서 여러 레코드 처리

    /*public void add(StockUpdate newStockUpdate){ // StockUpdate이건 새로 클래스를 만들어야 하는듯 함
        for (StockUpdate)
    }*/

    // 현재 심볼에 대응하는 첫 번째(최신의) 주식을 리스트로부터 찾는다. 같은 것이 확인되면 금액을 확인한다. 만약 이전 값과 같으면 업데이트된 값은 무시하고 다른 경우는 업데이트된 값을 적용한다.

    //함수형 인터페이스
    //Action 인터페이스가 필요했다.
    //RxJava의 새 릴리스에서는 개발자가 Java 8의 함수형 인터페이스를 채택하기로 했다.


    //플로어블
    //언급해야 할 더 큰 변화 중 하나는 옵저버플 외에도 플로어블의 도입이다. RxJva 1.0에는 플로어블과 옵저버블의 기능을 결합한 옵저버블 클래스가 하나만 있었다.

    //디펜던시가 있는 프로젝트의 build.gradle 파일에서 이 책을 출판할 때쯤 새로운 버전의 라이브러리가 있을 수 있다.

    //RxJava 1.0옵저버블 변환

    //플로어블로 변환

    //오래된 옵저버블을 플로어블로 변환하는 과정은 매우 유사하다.






