package com.example.kotlintest32;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;

import kotlin.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public class RxJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
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
    public static final void main(@NotNull String[] args){
        int x2 = 20;
        int x1 = 10;
        int var10000 = x1 + x2;
    }

    //소스 _ 일반 함수 자바 변형

    public static final int normalFun1(int a, int b){
        return a + b;
    }

    public static final void main2(@NotNull String[] args){
        Intrinsics.checkParameterIsNotNull(args, "args");
        normalFun1(10, 20);
    }

    //자바로 변형된 소스를 보면 일반 함수 이용 시 특별하게 추가된 부분은 없다. 람다 함수의 이용이 없다 보니 개발자가 작성한 코드가 거의 그대로 자바로 작성된다. 일반 함수를 선언할 때도 inline을 추가하여 작성 할 수 있고 이렇게 하면 인라인으로 선언된 함수의 내용이 이 함수를 호출하는 곳에 컴파일 단계에서 정적으로 포함되지만, 별로 이득이 없다는 이야기.
    // 이런 이유로 코틀린에서는 일반 함수에 inline을 사용하면 다음과 같은 경고 메시지를 출력

    // inlining works best for functions with lambda parameters

    // 즉, inline은 람다 함수를 매개변수로 이용하는 곳에 사용하라는 이야기

    public static final Function1 closureTest(final int x){
       String var1 = "argument" + x;
       System.out.println(var1);
       return (Function1)(new Function1() {

           public Object invoke(Object var1) {
               return this.invoke(((Number)var1).intValue());
           }

           public final int invoke(int it){
               return it * x;
           }
       });
    }
}
//위으 소스를 보면 람다 함수 내에서 closureTest() 함수의 변수에 접근하는 경우와 그렇지 않은 경우의 차이가 명확하게 보입니다. 내부적으로 closureTest() 함수의 변수까지 포함된 객체를 만들어 반환하므로 함수가 종료되더라도 closureTest() 함수의 변수를 그대로 이용할 수 있습니다.




    //inline이 추가된 hoFunTest() 함수와 이 함수를 호출하는 main() 함수 두 개를 작성했지만 자바로 변형된 결과를 보면 hoFunTest() 함수는 없다. main() 함수 하나가 정의되어 있고hoFunTest()함수의 내용은 컴파일 단계에서 main() 함수에 포함됨

    //결국, inline을 추가하여 함수를 정의하는 것과 그렇지 않은 것은 실행 결과는 같지만, 런타임 때 다르게 실행됩니다. 컴파일 단계에서 inline으로 정의한 함수의 내용이 호출되는 곳에 정적으로 포함되므로 런타임 때 함수 호출이 그만큼 줄고 성능에 도움이 된다는 이야기

    //inline을 일반 함수에는 사용 불가능??
    // 일반 함수 선언에도 inline을 추가할 수 있다. 하지만 일반 함수에서는 인라인의 이점이 별로 없다.

//소스 _클로저 적용 자바 변형 코드









