package com.example.kotlintest32

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        clickButton.setOnClickListener{
            textView.text = "버튼을 눌렀습니다."
        }

       Glide.with(this).load("https://www.verdict.co.uk/wp-content/uploads/2017/09/giphy-downsized-large.gif")
           .apply(RequestOptions.centerCropTransform())
           .into(imageView)
    }

    //코틀린에서는 일반적으로 데이터 타입을 선언할 때 x1:Int형태로 콜론 ( : )을 기준으로 왼쪽에 변수명과 오른쪽에 타입을 명시

    // argFun: (Int) -> Int 코드는 일반적인 데이터 타입 선언과 같은 형태로 함수 타입을 선언한 것

    // 이처럼 매개변수로 함수를 대입받는 함수를 고차 함수라 한다. 앞의 소스 줄에서 고차 함수를 호출하면서 두 개의 인수를 전달했습니다. 첫 번째 인수는 숫자 데이터이며 두 번째 인수는 함수입니다.
    /*fun hoFun(x1: Int, argFun: (Int) -> Int){
        val result = argFun(10)
        println("x1 : $x1, someFun1 : $result")
    }

    hoFun(10, {x -> x * x })*/

    // 고차 함수와 함수 타입 매개변수

    //함수 타입의 매개변수 대입

    // 일반적으로 함수를 호출할 때는 함수명 뒤에 ()를 붙이고 () 안에 인수를 작성합니다. 그런데 고차 함수의 매개변수가 함수 타입이면 함수 호출 때 ()을 생략할 수 있습니다.

    //소스_매개변수가 하나인 고차 함수 이용


}

//소스 _ 컬렉션 타입의 filter, forEach 함수 이용용

//소스 _ ()을 이용하여 함수 호출

//소스 _함수 타입 매개변수가 여러 개일 때

// 소스_inline 함수

inline fun hoFunTest(argFun: (x1: Int, x2: Int) -> Int){
    argFun(10,20)
}
fun main(args: Array<String>){
    hoFunTest {x1,x2 -> x1 + x2}
}


// 앞서 살펴보았던 고차 함수 정의에 inline만 추가한 예
// hoFunTest() 함수를 정의하고 이 함수는 함수 타입을 매개변수로 전달받는 고차 함수
// 그런데 fun 예약어 앞에 inline을 추가하여 작성

// 이렇게 inline을 추가한 함수는 자바라 변경 때 inline을 추가하지 않았을 때와 다르게 변형



// inline을 일반 함수에도 추가할 수는 있지만 하지만 일반 함수에서는 인라인의 이점이 별로 없다.

//소스 _ 일반 함수의 이용
    fun normalFun1(a:Int, b: Int): Int{
        return a + b
}

//위의 소스를 보면 normalFun1() 이라는 함수를 선언하고 이 함수를 main() 함수에서 호출하여 이용합니다. nomalFun1() 함수는 함수 타입이 매개변수로 선언되지 않은 일반 함수다.


// 노인라인

//소스 _ inline 적용 함수

inline fun inlineTest(argFun1: (x: Int) -> Int, argFun2: (x: Int) -> Int){
    argFun1(10)
    argFun2(10)
}

fun main12(args: Array<String>){
    inlineTest({it * 10},{it * 20})
}

// inlineTest() 함수를 선언했는데 함수 타입의 매개변수가 두 개.
// 즉, 람다 함수 두 개가 매개변수로 전달됨. 함수를 inline으로 선언했으므로 컴파일 단계에서 자바로 변형될 때 함수의 모든 내용이 이 함수를 호출한 곳에 정적으로 포함

// 고차 함수에 함수 타입 매개변수가 여러 개일 때 noinline 예약어를 이용

//inline에 적용되는 람다 함수와 적용되지 않는 람다 함수를 구분할 수 있다.

//소스 _ noinline이용

inline fun inlineTest2(argFun1: (x: Int) -> Int, noinline argFun2: (x: Int) -> Int){
    argFun1(10)
    argFun2(10)
}
fun main3(args: Array<String>){
    inlineTest({it * 10}, {it * 20})
}

//inlineFunTest() 함수는 함수 타입의 매개변수가 두 개이며 두 번째 매개변수인 argFun2 앞에 noinline 예약어를 추가했습니다. 이렇게 하면 argFun2는 컴파일 단계에서 정적으로 호출한 곳에 포함되지 않고 실행 때 호출되어 이용됩니다.


//논로컬 반환이란?

//논로컬 반환 (Non-local return)이란, 람다 함수 내에서 람다 함수를 포함하는 함수를 벗어나게 하는 기법을 말한다. 그런데 코틀린에서는 이름이 정의된 일반 함수와 익명 함수에서만 사용할 수 있으며 람다 함수에서는 사용할 수 없습니다.

//소스 _ 람다 함수에서 return 사용 에러

//소스_람다 함수에 return 사용 가능 경우

inline fun inlineTest2(argFun: (X: Int, y: Int) -> Int): Int{
    return argFun(10,0)
}

fun callFun(){
    println("callFun.. top")
    val result = inlineTest2{x, y ->
        if(y <= 0) return
        x / y
    }
    println("%result")
    println("callFun..bottom")
}
// 위으 소스를 보면 줄에 inlineTest2()라는 고차 함수를 선언했고 이 고차 함수를 호출할 때 람다 함수를 전달 그런데 람다 함수 내부에 return을 사용

// 의도는 전달받은 y 매개변수 값이 0 이하면 return 구문을 통해 callFun() 함수를 벗어나게 하고 싶은 것이지만 컴파일 에러가 발생

// 람다 함수니까 return 구문을 사용하지 못하게 하는 것은 당연해 보입니다. 위의 소스처럼 람다 함수가 callFun() 함수 내에 작성되어 있더라도 런타임 시 람다 함수의 내용이 callFun() 내에 포함된 것은 아니므로 여기서 retrun은 callFun()함수를 벗어나게 하지 못합니다.

// 그런데 만약 위의 소스에서 inlineTest2() 함수를 inline으로 선언하면 이 고차 함수에 전달되는 람다 함수 내에는 return 구문을 사용할 수 있다.

// 소스 _ 람다 함수에 return 사용 가능 경우

inline fun inlineTest5(argFun5:(x: Int, y: Int) -> Int): Int{
    return argFun5(10,0)
}
fun callFun5(){
    println("callFun.. top")
    val result = inlineTest5{ x, y ->
        if( y <= 0)return
        x / y
    }
    println("$result")
    println("callFun.. bottom")
}
fun main4(args: Array<String>){
    callFun5()
}

//크로스 인라인
//고차 함수가 inline으로 선언되어 있다면 매개변수로 전달받은 람다 함수에서 retrun을 이용할 수 있다고 정리

//그런데 inline 고차 함수라도 전달받은 람다 함수를 고차 함수 내에서 다른 객체에 대입하면 retrun 사용에 제약이 있습니다.

//소스_매개함수를 다른 객체에 대입해서 이용

open class TestClass6{
    open fun some(){}
}
fun inlineTest6(argFun: () -> Unit){
    val obj = object : TestClass6(){
        override fun some() = argFun()
    }
}

//매개변수로 전달받은 함수를 다른 객체에 대입하고 있다. 위의 소스는 문제없이 컴파일되며 실행된다.

// 그런데 위 소스의 고차 함수에 inline을 추가하면 문제가 발생함

//소스_inline 함수에서 매개함수를 다른 객체에 대입 이용

open class TestClass7{
    open fun some(){
    }
    inline fun inlineTest3(argFun7: () -> Unit){//고차 함수 선언에 inline을 추가했다는 점 함수에 inline을 명시하기만 했는데  컴파일에서 에러가 발생함
        val obj = object : TestClass7(){
            /*override fun some() = argFun7()*/ // <--- 여기서 에러가 난다.
        }
    }
}

// 함수를 inline으로 선언하면 해당 함수에 매개변수로 전달하는 람다 함수에 return을 명시 할 수 있ㅅ브니다. 그런데 return을 명시할 수 있는 람다 함수를 inline 함수 내부에서만 사용하지 않고 또 다른 객체에 대입하면 그 객체의 코드가 inline되지 않아서 발생하는 에러

// 이처럼 다른 객체에 대입해 이용하는 매개함수를 선언할 때 crossinline 예약어를 추가하여 작성할수 있습니다.

// crossinline은 함수를 inline으로 선언했더라도 람다 함수에 return을 사용하지 못하게 하는 예약어 입니다. 위에서 살펴보았듯이 이 람다 함수는 inline 함수 내에서 다른 객체에 대입해 이용하므로 아예 람다 함수에 return을 사용할 수 없게 하고 싶습니다.

// 이럴 때 crossinline을 사용. crossinline으로 선언한 매개변수에 전달되는 람다 함수에 return을 사용하면 에러가 발생

// 소스 _ crossinline 이용

/*
inline fun inlineTest8(crossinline argFun8: () -> Unit){ //inline으로 선언한 고차 함수가 있고, 그 매개변수로 함수 타입을 선언하고. 이 함수 타입에 전달한 람다 함수에서 return을 사용할 수 있습니다. 하지만 함수 타입 앞에 crossinline을 선언 람다 함수에 return을 사용하면 컴파일 에러가 발생
    val obj = object : TestClass(){ // TestClass도 에러가 나는데 왜 나는지 모르겠다.
        override fun som() = argFun8()
    }
}

fun crossInlineTest(){
    println("aaa")
    inlineTest8{
        return //<---- 여기가 에러가난다.
    }
}*/

// 이 함수 타입에 전달한 람다 함수에는 return을 사용할 수 있다.하지만 함수 타입 앞에 crossinline을 선언했으므로 람다 함수에 return을 사용하면 컴파일 에러가 발생함

// 라벨로 반환

// 람다 함수를 작성하다 보면 람다 함수 내에서 return 구문을 사용해야 할 때가 있다.

// 앞에서 정리했듯이 기본으로 람다 함수 내에서는 return 구문을 사용할 수 없지만, 람다 함수를 전달받는 고차 함수를 inline으로 선언하면 return을 사용할 수 있습니다.
// 위의 소스를 보면 inlineTest3()이라는 고차 함수를 선언했고 이 함수에 매개변수로 함수 타입을 선언 했으므로 argFun()은 함수입니다.

// crossinline은 함수를 inline으로 선언했더라도 람다 함수에 return을 사용하지 못하게 하는 예약어

//람다 함수는 inline 함수 내에서 다른 객체에 대입해 이용하므로 아예 람다 함수에 retrun을 사용할 수 없게 하고 싶습니다. 이럴 때 crossinline을 사용.

// crossinline으로 선언한 매개변수에 전달되는 람다 함수에 return을 사용하면 에러가 발생

//소스_crossinline이용
/*open class TestClass9{
    inline fun inlineTest9(crossinline argFun: () -> Unit){
        val obj = object : TestClass9(){
            override fun some() = argFun()
        }
    }

    fun crossInlineTest(){
        println("aaa")
        inlineTest9{
            *//*return*//* //<---- 이 부분이 에러가 난다.
        }
    }
}*/

//위의 소스에서는 inline으로 선언한 고차 함수가 있고, 그 매개변수로 함수 타입을 선언했다. 이 함수 타입에 전달한 람다 함수에는 return을 사용할 수 있다. 하지만 함수 타입 앞에 crossinline을 선언했으므로 람다 함수에 return을 사용하면 컴파일 에러가 발생한다.

//라벨로 반환

/*
val array = arrayOf(1, -1, 2)
fun arrayLoop(){
    println("loop top..")
    array.forEach{
        println(it)
    }
    println("loop bottom")
}
arrayLoop()*/

//위의 소스는 arrayLoop() 함수 내에서 배열 데이터를 출력하는 프로그램입니다. forEach() 함수에 람다 함수를 대입하여 출력합니다. forEach()는 고차 함수이며 inline으로 선언된 함수입니다.

//람다 함수에서 배열 데이터를 출력할 때 만약 0보다 작은 값이 대입되며 배열 출력을 멈추고 싶습니다. 이를 위해 람다 함수에 return 구문을 추가해볼 수 있다.

// 그런데 만약 배열 요수 중 음수만 출력하지 않고 싶을 때는 어떻게 할까요? 즉, 데이터를 출력하는 람다 함수만 벗어나게 하고 싶습니다. 이때 이용되는 것이 라벨(label)입니다. 람다 함수 앞에 라벨을 지정하고 return에 해당 라벨을 명시해 함수를 종료할 수 있다.

//소스_라벨 이용

/*
val array = arrayOf(1, -1, 2)
fun arrayLoop(){
    println("loop top..")
    array.forEach aaa@{
        if(it < 0) return@aaa
        println(it)
    }
    println("loop bottom..")
}
arrayLoop()*/ // -> 여기서 에러가 남

//람다 함수 앞에 aaa@라는 구문으로 라벨을 추가했습니다. aaa는 개발자가 정한 문자열이며 이 문자열 뒤에 @을 추가하여 라벨을 만든다. 그리고 람다 함수 내에서 return 문에 @aaa를 명시하여 라벨이 붙은 부분을 벗어나게 지정

// 이렇게 하면 람다 함수 내에서 return 구문을 사용했을 때 람다 함수를 벗어나게 할 수 있다

//그런데 위의 예에서는 aaa라는 라벨을 개발자가 직접 지정한 것, 코틀린에서는 개발자가 별도로 라벨을 지정하지 않아도 고차 함수에 자동으로 붙은 라벨이 있다.

/*
val array = arrayOf(1, -1, 2)
fun arrayLoop(){
    println("loop top...")
    array.forEach{
        if(if < 0) return@forEach
        println(it)
    }
    println("loop bottom..")
}
arrayLoop()*/

// 위의 소스를 보면 return 구문과 라벨을 이용해 람다 함수의 실행을 종료함

// 이때 사용한 라벨을 @forEach인데, 소스에는 이 이름이 라벨을 선언하지 않음

// 이처럼 개발자가 라벨을 선언하지 않아도 코틀린에서는 자동으로 함수명으로 된 라벨이 추가됩니다.

// 개발자 고차 함수에서의 return
inline fun hoTest(argFun: (String) -> Unit){
    argFun("hello")
    argFun("kim")
    argFun("kkang")
}
fun labelTest(){
    println("test top...")
    hoTest{
        if(it.length < 4) return@hoTest
        println(it)
    }
    println("test bottom....")
}

// 고차 함수를 이용할 때 람다 함수 이외에 익명 함수도 이용할 수 있는데 익명 함수에서의 retrun도 람다와 동일한가요? 함수를 벗어나려면 라벨을 이용해야 하나요?

// 소스 _ 익명 함수 이용
/*
val array =arrayOf(1, -1, 2)
fun arrayLoop2(){
    println("loop before..")
    array.forEach(fun(value: Int){
        if (value < 0) return
        println(value)
    })
    println("loop after...")
}
arrayLoop2()*/


// 클로저(closure)는 프로그래밍 언어에서 흔히 함수의 스코프(scope)에 사용된 변수를 바인딩하기 위한 기법으로 소개한다. 함수가 호출될 때 발생하는 데이터를 함수 호출 후에도 그대로 유지해 이용하는 기법

//어찌 보면 함수의 호출이 끝났는데 어떻게 함수의 데이터를 유지할 수 있는지 의문이 들고, 함수를 호출한 곳으로 데이터를 반환해 유지하면 되지 않을까 생각할 수 있지만, 함수형 프로그래밍 에서는 조금 복잡한 상황이 발생하기도 한다.

//소스_함수 스코프
fun closureTest(x: Int){
    println("argument $x")
}

//clouserTest()라는 함수에 매개변수 X를 Int 타입으로 선언했다. x 변수는 함수 내부에 선언된 지역변수이므로 이 변수의 스코프(변수의 데이터가 유지되는 범위)는 함수 내부입니다. 즉, 함수가 호출되어 이용되다 함수 호출이 끝나면 사라지는 데이터다.

//그런데 함수형 프로그래밍에서는 함수에 선언된 변수가 함수 호출 후에도 유지돼야 할 때가 있다.
//이런 상황이 발생하는 것은 함수형 프로그래밍에서는 함수가 일급 객체라서 함수 내부에 함수를 정의할 수 있기 때문입니다. 그런데 함수 내부에 선언된 함수가 함수 호출 후에도 계속 이용된다면 어떻게 될까요?

//소스_함수 외부 데이터 이용

fun closureTest10(x: Int): (Int) -> Int{
    println("argument $x")
    return{ it * x }
}

fun main10(args: Array<String>){
    val someFun1 = closureTest10(2)
    val someFun2 = closureTest10(3)
    
    println("${someFun1(10)}")
    println("${someFun2(10)}")
}

//소스 _ 외부 변수 변경

fun closureTest2(): (Int) -> Unit{
    var sum = 0
    return{
        for(i in 1..it){
            sum += i
        }
    }
}

// 하지만 자바에서는 람다 함수에서 외부 함수의 데이터 변경은 허용하지 않습니다. 다음은 자바 코드입니다.

//집합 연산 함수

// forEach(), forEachIndexed()

//컬렉션 타입의 데이터를 이용할 때 forEach() 함수도 자주 이용한다.

//forEach() 함수는 단순히 컬렉션 타입의 데이터 개수만큼 특정 구문을 반복 실행할 때 유용합니다.

//소스_컬랙션 타입 데이터 반복 획득

//filter() 함수로 추출된 데이터가 2개라면 ForEach() 함수가 두 번 호출되며 호출 때마다 데이터를 차례로 forEach() gkatndml aoroqustnfh wlwjdehls {}에 전달해 줍니다.

//forEachIndexed() 함수는 forEach() 함수와 동일하며 단지 람다 함수에 인덱스갑ㅆ까지 전달해 줍니다.


// all(), any()
// all() 함수는 컬렉션 타입의 데이터가 특정 조건에 모두 만족하는지 판단할 때 사용하며, any() 함수는

// 특정 조건에 만족하는 데이터가 잇는지 판단할 때 사용합니다.

//소스_all(), any() 함수 테스트

