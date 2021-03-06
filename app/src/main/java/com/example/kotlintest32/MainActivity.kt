package com.example.kotlintest32

import android.graphics.Color.YELLOW
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.X
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ClassCastException
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.memberProperties
import com.example.kotlintest32.RxJava
import java.io.File
import java.lang.Character.getName
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.math.BigInteger

import java.util.Random
import kotlin.math.cos


import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty


open class MyClassParent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        clickButton.setOnClickListener {
            textView.text = "버튼을 눌렀습니다."
        }

        Glide.with(this)
            .load("https://www.verdict.co.uk/wp-content/uploads/2017/09/giphy-downsized-large.gif")
            .apply(RequestOptions.centerCropTransform())
            .into(imageView)

        Glide.with(this)
            .load("https://t1.daumcdn.net/cfile/tistory/99555E4B5CAE56670E")
            .apply(RequestOptions.centerCropTransform())
            .into(imageView2)
    }

    /*interface Bag<out T>{
        fun get(): T
    }

    class BagImp1 : Bag<RxJava>{
        override fun get(): RxJava = RxJava()
    }

    val bag: Bag<MyClassParent> = BagImp1()

    interface Bag2<in T>{
        fun use(t: T): Boolean
    }

    class BagImp2 : Bag<MyClassParent>{
        override fun use(t: MyClassParent): Boolean = true
    }

    val bag2: Bag<MyClass> = BagImp2*/



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

inline fun hoFunTest(argFun: (x1: Int, x2: Int) -> Int) {
    argFun(10, 20)
}

fun main(args: Array<String>) {
    hoFunTest { x1, x2 -> x1 + x2 }
}


// 앞서 살펴보았던 고차 함수 정의에 inline만 추가한 예
// hoFunTest() 함수를 정의하고 이 함수는 함수 타입을 매개변수로 전달받는 고차 함수
// 그런데 fun 예약어 앞에 inline을 추가하여 작성

// 이렇게 inline을 추가한 함수는 자바라 변경 때 inline을 추가하지 않았을 때와 다르게 변형


// inline을 일반 함수에도 추가할 수는 있지만 하지만 일반 함수에서는 인라인의 이점이 별로 없다.

//소스 _ 일반 함수의 이용
fun normalFun1(a: Int, b: Int): Int {
    return a + b
}

//위의 소스를 보면 normalFun1() 이라는 함수를 선언하고 이 함수를 main() 함수에서 호출하여 이용합니다. nomalFun1() 함수는 함수 타입이 매개변수로 선언되지 않은 일반 함수다.


// 노인라인

//소스 _ inline 적용 함수

inline fun inlineTest(argFun1: (x: Int) -> Int, argFun2: (x: Int) -> Int) {
    argFun1(10)
    argFun2(10)
}

fun main12(args: Array<String>) {
    inlineTest({ it * 10 }, { it * 20 })
}

// inlineTest() 함수를 선언했는데 함수 타입의 매개변수가 두 개.
// 즉, 람다 함수 두 개가 매개변수로 전달됨. 함수를 inline으로 선언했으므로 컴파일 단계에서 자바로 변형될 때 함수의 모든 내용이 이 함수를 호출한 곳에 정적으로 포함

// 고차 함수에 함수 타입 매개변수가 여러 개일 때 noinline 예약어를 이용

//inline에 적용되는 람다 함수와 적용되지 않는 람다 함수를 구분할 수 있다.

//소스 _ noinline이용

inline fun inlineTest2(argFun1: (x: Int) -> Int, noinline argFun2: (x: Int) -> Int) {
    argFun1(10)
    argFun2(10)
}

fun main3(args: Array<String>) {
    inlineTest({ it * 10 }, { it * 20 })
}

//inlineFunTest() 함수는 함수 타입의 매개변수가 두 개이며 두 번째 매개변수인 argFun2 앞에 noinline 예약어를 추가했습니다. 이렇게 하면 argFun2는 컴파일 단계에서 정적으로 호출한 곳에 포함되지 않고 실행 때 호출되어 이용됩니다.


//논로컬 반환이란?

//논로컬 반환 (Non-local return)이란, 람다 함수 내에서 람다 함수를 포함하는 함수를 벗어나게 하는 기법을 말한다. 그런데 코틀린에서는 이름이 정의된 일반 함수와 익명 함수에서만 사용할 수 있으며 람다 함수에서는 사용할 수 없습니다.

//소스 _ 람다 함수에서 return 사용 에러

//소스_람다 함수에 return 사용 가능 경우

inline fun inlineTest2(argFun: (X: Int, y: Int) -> Int): Int {
    return argFun(10, 0)
}

fun callFun() {
    println("callFun.. top")
    val result = inlineTest2 { x, y ->
        if (y <= 0) return
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

inline fun inlineTest5(argFun5: (x: Int, y: Int) -> Int): Int {
    return argFun5(10, 0)
}

fun callFun5() {
    println("callFun.. top")
    val result = inlineTest5 { x, y ->
        if (y <= 0) return
        x / y
    }
    println("$result")
    println("callFun.. bottom")
}

fun main4(args: Array<String>) {
    callFun5()
}

//크로스 인라인
//고차 함수가 inline으로 선언되어 있다면 매개변수로 전달받은 람다 함수에서 retrun을 이용할 수 있다고 정리

//그런데 inline 고차 함수라도 전달받은 람다 함수를 고차 함수 내에서 다른 객체에 대입하면 retrun 사용에 제약이 있습니다.

//소스_매개함수를 다른 객체에 대입해서 이용

open class TestClass6 {
    open fun some() {}
}

fun inlineTest6(argFun: () -> Unit) {
    val obj = object : TestClass6() {
        override fun some() = argFun()
    }
}

//매개변수로 전달받은 함수를 다른 객체에 대입하고 있다. 위의 소스는 문제없이 컴파일되며 실행된다.

// 그런데 위 소스의 고차 함수에 inline을 추가하면 문제가 발생함

//소스_inline 함수에서 매개함수를 다른 객체에 대입 이용

open class TestClass7 {
    open fun some() {
    }

    inline fun inlineTest3(argFun7: () -> Unit) {//고차 함수 선언에 inline을 추가했다는 점 함수에 inline을 명시하기만 했는데  컴파일에서 에러가 발생함
        val obj = object : TestClass7() {
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
inline fun hoTest(argFun: (String) -> Unit) {
    argFun("hello")
    argFun("kim")
    argFun("kkang")
}

fun labelTest() {
    println("test top...")
    hoTest {
        if (it.length < 4) return@hoTest
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
fun closureTest(x: Int) {
    println("argument $x")
}

//clouserTest()라는 함수에 매개변수 X를 Int 타입으로 선언했다. x 변수는 함수 내부에 선언된 지역변수이므로 이 변수의 스코프(변수의 데이터가 유지되는 범위)는 함수 내부입니다. 즉, 함수가 호출되어 이용되다 함수 호출이 끝나면 사라지는 데이터다.

//그런데 함수형 프로그래밍에서는 함수에 선언된 변수가 함수 호출 후에도 유지돼야 할 때가 있다.
//이런 상황이 발생하는 것은 함수형 프로그래밍에서는 함수가 일급 객체라서 함수 내부에 함수를 정의할 수 있기 때문입니다. 그런데 함수 내부에 선언된 함수가 함수 호출 후에도 계속 이용된다면 어떻게 될까요?

//소스_함수 외부 데이터 이용

fun closureTest10(x: Int): (Int) -> Int {
    println("argument $x")
    return { it * x }
}

fun main10(args: Array<String>) {
    val someFun1 = closureTest10(2)
    val someFun2 = closureTest10(3)

    println("${someFun1(10)}")
    println("${someFun2(10)}")
}

//소스 _ 외부 변수 변경

fun closureTest2(): (Int) -> Unit {
    var sum = 0
    return {
        for (i in 1..it) {
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

//count(), find()

//소스 _ all(), any() 테스트
fun main16(args: Array<String>) {
    class User(val name: String, val age: Int)

    val list = listOf(User("kkang", 33), User("lee", 28))

    println("count test : ${list.count { it.age > 25 }}")
    val user = list.find { it.age > 25 }
    println("find test : ${user?.name} ${user?.age}")
}

fun main17(args: Array<String>) {
    //fole() 함수는 초깃값을 지정할 수 있고
    //reduce() 함수는 초깃값을 지정할 수 없다.

    var result = listOf(1, 2).fold(10, { total, next ->
        println("${total} ... ${next}")
        total + next
    })
    println("fold test : $result")
}

fun main18(args: Array<String>) {
    val list2 = listOf<Int>(12, 8, 9, 20)
    val resultList2 = list2.filter { it > 10 }
    for (i in resultList2) {
        println(i)
    }
}

//소스 _ filterNot()
fun main11(args: Array<String>) {
    listOf(21, 1, 12, 5, 23).filterNot { it > 10 }.forEach { println(it) }
}

//소스 _ drop
fun main13(args: Array<String>) {
    listOf(1, 2, 3, 4).drop(2).forEach { println(it) }
}

//소스 _ elementAt
fun main14(args: Array<String>) {
    val result = listOf(2, 5, 10, 8).elementAt(2)
    println("elementAt test : $result")
}
//만약 인데스값이 데이터 범위를 벗어나도 IndexOutOfBoundsException이 발생하지 않는다.
// 대신, 지정된 람다 함수가 실행되고 그 람다 함수에서 변환 값을 변환합니다.

//소스 _ elementAtOrElse()
fun main15(args: Array<String>) {
    var result1 = 0
    result1 = listOf(2, 5, 10, 8).elementAtOrElse(5, { 0 })
    println("elementAtOrElse test : $result1")
}

//elementAtOrElse(5, { 0 })으로 지정했습니다. 인덱스 5의 데이터를 추출하는 구문이다. 인덱스 5는 데이터 범위를 벗어난 값입니다. 이럴 때는 { 0 } 부분이 실행됩니다. 위에서는 데이터 범위를 벗어난 인덱스캆을 전달하면 단순히 0을 반환하도록 작성했다.

fun main19(args: Array<String>) {
    var result1 = 0
    result1 = listOf(2, 5, 10, 8).first { it % 5 == 0 }
    println("first test : $result1")
}

//소스 _ lasat()
fun main20(args: Array<String>) {
    var result1 = listOf(2, 5, 10, 8).last { it % 5 == 0 }
    println("last test : $result1")
}

//last() 함수를 이용할 때 만약 조건에 맞는 데이터가 하나도 없으면 NoSuchElementException이 발생한다. 그리고 lastOrNull() 함수는 first() 함수와 동일한데, 만약 조건에 맞는 데이터가 없으면 null을 반환합니다.

fun main21(args: Array<String>) {
    var result1 = 0
    result1 = listOf(2, 5, 10, 2).indexOf(2)
    println("indexOf test : $result1")
}

//소스 _ indexOfFirst()
fun main22(args: Array<String>) {
    var result1 = 0
    result1 = listOf(2, 5, 10, 2).indexOfFirst { it % 2 == 0 }
    println("indexOfFirst test : $result1")
}

// indexOfFirst { it % 2 == 0 }로 작성했으므로 람다 함수 조건은 2로 나누었을 때 나머지가 0인 데이터입니다. 그 데이터가 위차하는 첫 번째 인덱스값을 반환합니다.

// 소스_indexOfLast()
fun main23(args: Array<String>) {
    var result1 = 0
    result1 = listOf(2, 5, 10, 2).indexOfLast { it % 2 == 0 }
    println("indexOfLast test : $result1")
}

// Null 안전성

// Null 안전성이란?
// Null이란 프로그램에서 아무 값도 대입하지 않은 상태를 말함.
// 프로그램의 데이터는 메모리에 저장합니다. 그런데 데이터가 메모리의 어느 위치에 저장됐는지 알아야 그 메모리에 접근해 데이터를 가져오거나 수정할 수 있습니다.

//특히 객체는 (코틀린에서는 모든 것이 객체입니다) 흔히 참조 변수라고 표현해서 실제 객체에 데이터가 대입된 게 아니라 데이터를 가지는 메모리의 주소 값이 저장된 변수다. 그런데 객체에 대입된 주소 값이 없는 상태, 즉 Null은 객체가 생성되지 않아서 메모리 할당이 이루어지지 않은 상태를 이야기합니다.

// 문제는 Null이 메모리 할당이 이루어지지 않은 상태를 표현하기 위한 용도이지만, 개발 때는 참 귀찮은 존재다.
// 프로그램을 개발할 때 Null 상태는 표현할 수 있지만 Null 상태인 변수에 접근하면 NullPointException(흔히 NPE라고 부른다.)이라는 에러가 발생한다.

//Null 상태의 변수는 할당된 메모리가 없기 때문에 접근을 시도하면 에러가 발새앟ㄹ 수밖에 없다.

// 변수가 Null인지 아닌지 확인하고 Null이면 어떻게 처리하고, Null이 아니면 어떻게 처리한다 등의 코드가 계속 반독될 수 밖에 없다.

//Null 안전성이란 코틀린에서 Null이라는 개념이 없다는 것이 아니라, 이 Null에 다양한 처리를 도와줌으로써 Null에 의한 NPE가 발생하지 안흔ㄴ 프로그램을 작성할 수 있게 해준다는 개녑이다.

//Null 허용과 Null불허

//소스 _ Nullable과 Non-Nullable

fun main24(args: Array<String>) {
    var data1: String = "kkang"
    var data2: String? = null

    fun main(args: Array<String>) {
        /*data1 = null*/ // <--- 이러면 에러다
    }
}

//프로퍼티를 선언할 때 타입 뒤에 물음표(?)를 추가하면 Null을 대입할 수 있고, 물음표를 추가하지 않으면 Null을 대입할 수 없습니다. Null 불허로 선언한 프로퍼티에 null을 대입했으므로 컴파일 에러가 발생합니다.

// 또한, Null 불허로 선언한 프로퍼티를 Null 허용으로 선언한 프로퍼티에 댕비하는 것은 상관없지만, Null 허용으로 선언한 프로퍼티를 Null 불허 프로퍼티에 대입하면 컴파일 에러가 발생합니다. 어떠한 형태로든 Null 불허 프로퍼티에 Null 불허 프로퍼티에 Null을 대입할 수는 없다.

// Null 확인 연산자

fun main25(args: Array<String>) {

    var data1: String? = "kkang"

    val length1: Int? = if (data1 != null) {
        data1.length
    } else {
        null
    }
}

//소스 _ ?.을 이용한 Null 체크

fun main26(args: Array<String>) {
    var data1: String? = "kkang" //data1은 Null 허용으로 선언한 프로퍼티다.

    var length2: Int? =
        data1?.length // 이 프로퍼티의 length에 접근할 때 Null 확인을 해주어야 한다. if-else 문으로 하지 않고 ?.연산자로도 가능하다
    println(length2)

    data1 = null
    length2 = data1?.length
    println(length2)
}

// ?.연산자는 Null을 안전하게 사용하기 위해 제공하는 연산자로 프로퍼티 값이 Null이 아니면 뒤의 length가 실행되고 Null이면 null을 반환합니다. 결국, data1.length 구문은 data1이 Null이 아닐 때만 길이를 반환하고 만약 Null이면 null을 반환한다.

// ?. 연산자로 Null을 확인하는 방법은 객체의 연결 구조에서도 사용할 수 있습니다.

//소스 _ ?.을 이용한 Null 체크 - 객체의 연결 구조

class Address {
    val city: String? = "seoul"
}

class User {
    val address: Address? = Address()
}

fun main27(args: Array<String>) {
    val user: User? = User()

    println(user?.address?.city)
}

fun main28(args: Array<String>) {
    val array = arrayOf("hello", null, "kkang")

    array.forEach {
        if (it != null) {
            println("$it .. ${it.length}")
        }
    }

    array.forEach {
        it?.let {
            println("$it .. ${it.length}")
        }
    }
}

// 엘비스 연산자

// ?: 연산자를 이용해 Null을 처리할 수도 있습니다. 흔히 ?:을 엘비스 연산자(Elvis Operator)라고 부르며 Null 허용 데이터를 처리할 때 Null 처리를 명시할 수 있습니다. ?. 연산자는 Null이면 null

// 때로는 Null일 때 대입해야 하는 값이 있거나 실행해야 하는 구문이 있습니다. 이럴 때 ?: 연산자를 이용합니다. ?: 연산자를 독릭접으로 사용할 수도 있고 ?. 연산자와 함께 사용할 수도 있습니다.

// 소스 _ ?: 연산자

fun main29(args: Array<String>) {
    var data: String? = "kkang"

    var length: Int = if (data != null) {
        data.length
    } else {
        -1
    }

    data = null

    length = data?.length ?: -1

    println(length)

    data ?: println("data is null")
}

// 줄에 Null 허용 프로퍼티가 있습니다. 이 프로퍼티의 데이터가 정상적으로 대입되어 있으면 문자열의 길이를 계산하고, Null이면 -1을 대입한다는 가정입니다. 일반적으로 Null 안전성을 지원하는 연산자를 사용하지 않는다면

// Null이면 -1을 대입한다는 가정입니다. 일반적으로 Null 안전성을 지원하는 연산자를 사용하지 않는다면 줄처럼 직접 if-else 문으로 작성할 수 있습니다.

// 그런데 이 부분을 ?:연산자를 이용하여 쉽게 구현할 수 있습니다.

// data?.length라고 작성했습니다. data가 Null이 아니면 length를 계산하고 Null

// 예외 처리

// 절에서 다루는 예외(Exception)처리는 컴파일 에러(Error)를 이야기하는 것이 아니라, 실행 시 발생하는 예외 처리입니다, 아무리 프로그램을 잘 작성한다고 하더라도 얼마든지 예외가 발생할 수 있습니다.

// 예를 들어 네트워크 서버에 연결하여 데이터를 입출력할 때 아무리 정상적인 알고리즘으로 작성하더라도 갑자기 네트워크 연결이 끊어지는 상황은 언제든지 발생가능

// 예외 처리

// 이번 절에서 다루는 예외(Exception) 처리는 컴파일 에러(Error)를 이야기하는 것이 아니라, 실행 시 발생하는 예외 처리입니다. 아무리 프로그램을 잘 작성한다고 하더라도 얼마든지 예외가 발생할 수 있습니다. 예를 들어 네트워크 서버에 연결하여 데이터를 입출력할 때 아무리 정상적인 알고리즘으로 작성하더라도 갑자기 네트워크 연결이 끊어지는 상황은 언제든지 발생할 수 있습니다.

// 예외 발생 연산자

// !!연산자는 Null이면 예외를 발생시킵니다. Null일 때 ?. 나 ?

// NPE가 발생하지 않게 작성할 수도 있지만, 때로는 NPE를 발생시켜야 할 때도 있습니다. 이때 사용하는 연산자가 !!입니다.

// 소스_!! 연산자

/*
fun main30(args: Array<String>){

    var data: String? = "kkang"

    data!!.length

    data = null

    data!!.length

}*/

// 소스 _ safe cast

fun main30(args: Array<String>) {
    val strData: String = "kkang"

    val intData: Int? = strData as? Int

    println(intData)
}


//try - catch - finally 구문으로 예외 처리

//소스 _ try-catch-finally 구조

fun main31(args: Array<String>) {
    try {
        println("try top...")

        val data: String = "10"
    } catch (e: Exception) {
        println("catch.....")
    } finally {
        println("finally....")
    }
}

//위의 소스는 예외가 발생하지 않았을 때의 수행 흐름을 보여줍니다. try {} 영역이 있고 이어서 catch(){}와 finallly {} 영역이 있습니다. try 영역은 생략할 수 없으며 정상적으로 수행해야 할 구문을 작성합니다. try 영역에 작성한 구문이 실행될 때 예외가 발생하는지에 따라 실행

//흐름이 달라집니다.

// 소스 _ 예외 발생 시의 수행 흐름

fun main32(args: Array<String>) {
    try {
        println("try top...")

        val data: String = "kkang"

        val intData: Int? = data.toInt()

        println("try bottom...")
    } catch (e: Exception) {
        println("catch....${e.toString()}")
    } finally {
        println("finally....")
    }
}

///try 영역이 실행되다가 예외가 발생하면 try의 나머지 부분이 실행되지 않습니다. 그래서 "try bottom"
// 이라는 문자열은 출력되지 않았습니다. try 영역에서 예외가 발생하는 순간 catch 영역이 실행되고, 마지막으로 finally 영역이 실행됩니다. catch 영역에서 예외 메시지를 출력하기 위해 e.toString()을 사용했습니다. 이처럼 catch 문를 여러 개 작성한다는 것은 try 영역에서 발생하는 예외 타입이 여러 가지일 때입니다.

//소스 _ catch 문 여러 개 작성

fun some(array: Array<out Any>) {
    try {
        println("try top...")

        val intData: Int = array[0] as Int

        val data: String = array[0] as String

        val data2: Int = data.toInt()

    } catch (e: ClassCastException) {
        println("catch... ClassCastException")
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("catch... ArrayIndexOutOfBoundsException")
    } catch (e: Exception) {
        println("catch... Exception... ${e.toString()}")
    }
}

fun main33(args: Array<String>) {
    // 캐스팅 예외
    val array = arrayOf("0", 1, "6")
    some(array);

    //배열 데이터 접근 예외
    val array2 = arrayOf(10, "5")
    some(array2)

    //수자 타입 예외
    val array3 = arrayOf(10, 0, "world")
    some(array3)
}

//try 영역이 실행되다가 예외가 발생하면 try의 나머지 부분이 실행되지 않습니다. 그래서 "try bottom"이라는 문자열은 출력되지 않았습니다. try 영역에서 예외가 발생하는 순간 catch 영역이 실행되고, 마지막으로 finally영역이 실행됩니다.

//소스 _ try - catch - finally 문의 다양한 사용 예

//throw를 이용하여 예외를 발생시킵니다.

//Exception은 예외 타입을 표현하는 클래스이고 매개변수는 예외 메시지입니다. 이처럼 Exception 클래스의 객체를 생성하여 throw 뒤에 명시하면 예외가 발생합니다. 만약 줄이 실행된다면 이 함수는 더 이상 실행되지 않고 함수를 호출한 곳으로 반환합니다.

//some() 함수를 호출하면 결국 예외가 발생하여 some() 함수 실행이 중단됩니다. 그리고 발생한 것입니다. 그리고 catch 문이 실행됩니다.

// try - catch - finally 문으로 예외를 처리하면 유용한 것 같은데, 왜 굳이 throw로 예외를 발생시켜야 하나?

// 예외를 강제로 발생시키는 게 이상해 보일 수도 있지만, 실제로 많이 사용하는 기법. 예를 들어 어떤 함수가 정상적으로 실행되지 못하는 상황이 발생할 수도 있다.

// 그 상황을 함수를 호출한 곳에 알려줄 때 예외를 발생시킵니다. 그러면 함수를 호출한 곳에서는 try - catch 문으로 처리하고 호출한 함수에서 무언가 문제가 생겼다는 사실을 알 수 있게 한다.

class MyException(msg: String) :
    Exception(msg) { //MyException이라는 예외 클래스를 정의 Exception을 상속받아 작성하며 예외 메시지는 Exception에서 유지해주므로 상위 클래스의 생성자에 전달. 그런데 필요하다면 예외 클래스 내에 예외와 관련된 프로퍼티나 함수를 작성 가능.
    // 이렇게 만든 예외 클래스를
    val errorData: String = "some error data"
    fun errorFun() {
        println("errorFun call....")
    }
}

fun some1() {
    throw MyException("My Error...") //여기 줄 처럼 throw로 이용할 수도 있음
}

fun main34(args: Array<String>) {
    try {
        some1() //여기서 MyException이 발생하고
    } catch (e: MyException) {// 여기서 catch문이 실행됨
        println("error message : ${e.toString()}")
        println("error data : ${e.errorData}")
        e.errorFun()
    }
}

//코틀린에서 throw는 일종의 표현식. 따라서 throw 구문을 프로퍼티에 대입할 수 있으며 throw만을 반환하는 함수라면 반환 타입을 Nothing으로 지정가능

// 코틀린에는 자바의 throws가 없다.

// 자바에서 제공하는 예외 처리 내용 중 하나가 throws입니다. throws는  함수를 선언할 때 선언 부분에 추가해서 이 함수에서 예외를 반환할 수도 있다고 명시하는 구문

// 확장의 원리

//확장이라는 것은 클래스 내부에 선언한 함수와 프로퍼티 이외에 다른 함수나 프로퍼티를 추가해서 이용 하는 것

// 객체지향 프로그래밍에서 일반적으로 클래스 확장을 위해 사용하는 방법은 상속 기법입니다. 그러나 이 책에서는 용어의 혼동을 피하고자 확장이라는 단어는 상속을 통하지 않고 기능을 추가하는 방법에만 사용하겠습니다.

// 상속은 객체지향 프로그래밍의 핵심이다.

// 상위 클래스를 정의하고 그 클래스를 상속받아 하위 클래스를 정의합니다.

// 그러면 하위 클래스에서는 상위 클래스에 정의된 함수와 프로퍼티를 그대로 상속받아 자신의 것처럼 이용할 수 있습니다. 또한, 하위 클래스에서 자신만을 위한 함수와 프로퍼티를 더 추가할 수도 있습니다.

//소스 _ 상속에 의한 기능 추가

open class Super { //Super 클래스가 있고 이 Super를 상속받아 Sub 클래스를 만들었다. 상속을 통해 Sub 클래스에서는 Super 클래스에 정의된 함수와 프로퍼티를 그대로 이용할 수 있다.
    val superData: Int = 10
    fun superFun() {
        println("superFun....")
    }
}

class Sub :
    Super() { //상속을 통해 Sub 클래스에서는 Super 클래스에 정으된 함수와 프로퍼티를 그대로 이용할 수 있습니다. 그리고 Sub 클래스에 함수와 프로퍼티를 더 추가했습니다. 결국, Sub은 Super 클래스의 내용에 무언가를 더 추가한 클래스가 됩니다.
    val subData: Int = 20
    fun subFun() {
        println("subFun...")
    }
}

fun main36(args: Array<String>) {
    val obj: Sub = Sub()
    println("superData : ${obj.superData}, subData : ${obj.subData}")
    obj.superFun()
    obj.subFun()
}

//상속받지 않고 이미 선언된 클래스에 함수나 프로퍼티를 추가해서 이용하는 방법이 있다

// 이게 코틀린에서 제공하는 확장이라는 개념이다.

// 위의 작성했던 소스를 그대로 상속을 통하지 않고 확장으로 작성해 보겠습니다.

class Super2 {
    val superData: Int = 10
    fun superFun() {
        println("superFun....")
    }
}

val Super.subData: Int
    get() = 20

fun Super.subFun() {
    println("subFun.....")
}

fun main37(args: Array<String>) {
    val obj: Super = Super()
    println("superData : ${obj.superData}, subData : ${obj.subData}")
    obj.superFun()
    obj.subFun()
}

//상속을 이용하지 않고 함수와 프로퍼티를 클래스에 추가해서 사용하는 것을 확장이라고 부릅니다.

//기존 클래스에 함수를 추가하려면 다음의 형식에 따릅니다. 여기서 리시버 타입(Receiver Type)은 확장 대상이 되는 클래스입니다.

//형식

//fun Receiver Type(.)확장 함수

//확장 대상 클래스에 점 (.)으로 확장 함수를 명시하는 방법입니다. 예를 들어 Super라는 클래스에 subFun() 이라는 함수를 추가하겠다면 fun Super.subFun() {}으로 작성합니다.

// 정적 등록에 의한 실행

//기존 클래스에 함수나 프로퍼티를 간단히 추가해서 사용한다는 개념이지만 주의할 점이 있습니다. 확장 함수는 기존 클래스 내에 정적으로 추가되지는 않습니다. 확장 클래스가 아닌 외부에 작성되었다가

// 확장 함수는 클래스 외부에 정적으로 등록됩니다. 따라서 확장한 클래스 타입만 인지할 수 있으며 동적으로 상위 클래스를 판단할 수는 없다. 확장 시 사용한 확장 클래스만 인식한다는 이야기 입니다. 정적 등록이란 무엇일까?

// 2020-12-28 코틀린

// ExtensionClass의 함수 확장 구문을 DispatchClass 내에 작성했습니다.

//제네릭()

//소스 _ 배열의 이용
fun main38(args: Array<String>) {
    val array = arrayOf("kkang", 10, true)
}

//배열에 데이터 타입은 문자열이나 숫자 등 다양할 수 있습니다.
// 그런데 코틀린에서는 대입하는 모든 데이터의 타입이 선언되어 있어야 하는데, 어떻게 위의 예처럼arrayOf() 함수에 문자열과 숫자 등 서로 다른 데이터를 대입할 수 있을까? 그 이유는 arrayOf() 함수가 제네릭으로 선언되었기 때문

// 제네릭에서 <T>로 선언된 것을 Type Parameter라 부른다 이용할 때 <Int>로 지정하는 것을 Type Argument라고 부른다.

// 이해를 직관적으로 하기위해서 필자는 이를 '형식 타입'과'타입 지정'으로 부른다

// 프로퍼티에 형식 타입인 T를 명시하면 될 것처럼 보이지만 위의 코드는 에러입니다. T라는 타입 혹은 클래스가 정의되지 않았다는 에러가 발생. 제네릭으로 형식 타입을 선언하려면 클래스 선언 부분에 <T>를 추가해야 한다.

//소스_제네릭 선언 및 이용

class MyClass<T> {
    var info: T? = null
}

fun main39(args: Array<String>) {
    val obj1 = MyClass<String>()
    obj1.info = "hello"

    val obj2 = MyClass<Int>()
    obj2.info = 10
}

//소스 _ 타입 유추에 의한 제네릭 이용

class MyClass2<T>(no: T) {
    var info: T? = null
}

fun main40(args: Array<String>) {

    val obj3 = MyClass2<Int>(10)
    obj3.info = 20

    val obj4 = MyClass2("hello")
    obj4.info = "world"
}

//소스_형식 타입 문자

class MyClass3<AA> {
    var info: AA? = null
}

//여러 개의 형식 타입 선언

class MyClass4<T, A> {
    var info: T? = null
    var data: A? = null
}

fun main41(args: Array<String>) {
    val obj: MyClass4<String, Int> = MyClass4()
    obj.info = "hello"
    obj.data = 10
}

//함수와 프로퍼티의 제네릭

// 지금까지 살펴본 내용은 클래스를 선언할 때 제네릭을 이용하여 형식 타입을 선언하고 클래스 내부의 프로퍼티에 선언된 형식 타입을 이용하는 방법이었다.

// 그런데 제네릭은 함수를 선언할 때도 이용할 수 있다.

//소스 _ 함수의 형식 타입

class MyClass5<T, A> {
    var info: T? = null
    var data: A? = null

    fun myFun(arg: T): A? {
        return data
    }
}

//소스 _ 최상위 레벨 함수에서의 제네릭

fun <T> someFun(arg: T): T? {
    return null
}

// 하지만 최상위 레벨에 선언하는 프로퍼티에는 형식 ㅏ입을 선언할 수 없으므로 제네릭을 사용할 수 없습니다.

// 제네릭 제약

// 타입 제약

//제네릭 제약(Generic Constraing)이란, 제네릭을 이용할 때 특정 타입만 지정할 수 있도록 제약하는 것을 의미한다. 제네릭으로 선언된 클래스는 실제 이용하는 곳에서 아무 타입이나 지정해서 이용할 수 있다.

// 그런데 때로는 특정 타입만 지정하도록 한정하고 싶을 때가 있다.

//만약 String으로 지정하여 이용하면 클래스에서 수치 계산 작업이 불가능하다.

// 이때는 지정할 수 있는 타입을 Number의 하위 타입으로 제한해야 한다.

// 소스 _ 타입 제약

class MathUtil<T : Number> {
    fun plus(arg1: T, arg2: T): Double {
        return arg1.toDouble() + arg2.toDouble()
    }
}

// Variance

// Variance란?

// 제네릭에서 Variance(가변, 공변 등으로 해석되지만 이 책에서는 Variance로 칭한다.)란 타입과 관련된 이야기

// 상하위 관계에서 타입 변경과 관련 있습니다. 우선 제네릭의 Variance를 이해하기 위해 클래스의 상하위 관계에 대해 살벼보자.

// 소스 _ 상하위 클래스간 캐스팅

open class Super10 {
    open fun sayHello() {
        println("i am super sayHello...")
    }
}

class Sub10 : Super10() {
    override fun sayHello() {
        println("i am sub sayHello....")
    }
}

fun main42(args: Array<String>) {
    val obj: Super10 = Sub10()
    obj.sayHello()

    val obj2: Sub10 = obj as Sub10
    obj2.sayHello()
}

//그렇다면 클래스의 상하위 관계에 의한 캐스팅이 제네릭에도 가능?

open class Super11

class Sub11 : Super11()

class MyClass11<T>

fun main43(args: Array<String>) {
    val obj = MyClass11<Sub>()

    /*val obj2: MyClass<Super> = obj*/ //여기가 에러가 나는 이유 Sub는 Super의 하위 클래스이지만 MyClass<Sub>는 MyClass<Super>의 하위 클래스가 아니기 때문
}


//convariance

//out은 제네릭 형식 타입을 선언할 때 형식 타입명 앞에 추가하는 어노테이션입니다. 어노테이션이라고 해서 @를


class MyClass12<T>(val data: T) {
    fun myFun(): T {
        return data
    }

    fun myFun2(arg: T) {

    }

    fun myFun3(arg: T): T {
        return data;
    }
}

fun some1(arg: MyClass12<in Int>) {
    arg.myFun()
    arg.myFun2(10)
    arg.myFun3(10)
}

fun main44(args: Array<String>) {
    some1(MyClass12<Int>(10))
    some1(MyClass12<Number>(10))
}

fun copy(from: Array<Int>, to: Array<Int>) {
    for (i in from.indices)
        to[i] = from[i]
}

fun main45(args: Array<String>) {
    val array1: Array<Int> = arrayOf(1, 2, 3)
    val array2 = Array<Int>(3) { x -> 0 }
    copy(array1, array2)
    array2.forEach { println(it) }
}

//스타(*) 프로젝션

//스타 프로젝션(Projection)이란 제네릭 타입을 <*>로 표현하는 것을 의미

// 스타 프로젝션은 제네릭 타입을 모른다는 의미. 나중에 정확한 타입으로 이용되기는 하지만 지금은 어떤 제네릭 타입이 지정될지 모른다는 의미로 사용

// 스타 프로젝션은 제네릭의 선언 측에서는 사용할 수 없으며 이요축에서만 사용할 수 있다.

// 소스 _ as와 is

fun some(arg: Any) {
    if (arg is Int) {

    }

    val intVal = arg as Int
    intVal.plus(10)
}

fun main46(args: Array<String>) {
    some(10)
    some("hello")
}

fun some3(arg: List<*>) {
    val intList = arg as List<Int>
    println(intList.sum())
}

fun main47(args: Array<String>) {
    some3(listOf(10, 20))
    some3(listOf("hello", "kkang"))
}

// 인라인 함수와 reified

// 소스 _ inline과 refied 이용

inline fun <reified T> some2(arg: Any) {
    if (arg is T) {
        println("true")
    } else {
        println("false")
    }
}

fun main48(args: Array<String>) {
    some2<String>("hello")
    some2<Int>("hello")
}

//Unit 타입
// 자바의 void와 Unit의 차이

//Unit이 가장 많이 사용되는 곳은 함수의 반환 타입, 의미 있는 반환값이 없다는 의미이므로 자바의 void와 비슷하다고 이야기할 수 있다.

//그래도 void와 Unit은 차이가 있다.

// void는 함수의 반환값이 없다는 일종의 예약어

// Unit은 타입이라는 점

// 소스_타입으로서 Unit

fun myFun1() {}
fun myFun2(): Unit {}

fun myFun3(): Unit {
    return Unit
}

val myVal: Unit = Unit

fun main49(args: Array<String>) {
    println(myFun1())
}

//데이터 타입에는 데이터를 대입할 수 있다.

//소스 _ 제네릭 타입으로 Unit 타입 활용

interface MyInterface<T> {
    fun myFun(): T
}

class Myclass6 : MyInterface<String> {
    override fun myFun(): String {
        return "hello"
    }
}

class MyClass7 : MyInterface<Unit> {
    override fun myFun() {

    }
}

//함수의 반환 타입으로 Nothing 사용

// null만 대입되는 의미 없는 타입을 사용할 일은 별로 많지 않을 것 같지만, 크게 두 가지 상황에 사용할 수 있습니다.

// Unit 타입은 분명 함수의 반환이 있는 것이고 항상 kotlin, Unit을 반환한다. 그런데 Nothing은 함수의 반환값이 없거나 함수가 절대 반환하지 않는다는 것을 명시적으로 표현할 때 사용한다.

//소스_함수의 반환 타입으로 Nothing사용

fun myFun10(): Nothing {
    while (true) {

    }
}

fun myFun11(): Nothing? {
    return null
}

fun myFun12(): Nothing {
    throw Exception()
}

//제네릭에서 Nothing 타입 이용

// 소스 _ Nothing 타입의 대입
val myVal1: Nothing? = null

val myVal2: Int? = myVal1
val myVal3: String? = myVal1

// 소스 _ 제네릭에서 Nothing 타입 사용

class MyClass8<T>

fun someFun(arg: MyClass8<in Nothing>) {}

fun main50(args: Array<String>) {
    someFun(MyClass8<Int>())
    someFun(MyClass8<String>())
}

//리플렉션

//리플렉션이란?

//리플렉션(Reflection)은 사전적 의미로 투영, 반사라는 뜻,

//어떤 함수를 정의하는데 함수의 매개변수로 클래스 타입을 선언하고 런타임 때 매개변수로 전달된 클래스의 이름, 클래스의 함수나 프로퍼티를 동적으로 판단하는 작업을 리플렉션이라고 한다.

// 클래스 타입과 레퍼런스

// 런타임 때 동적으로 클래스를 분석하려면 클래스에 대한 정보가 필요한데 이 클래스에 대한 정보를 클래스 레퍼런스라고 표현한다, 클래스 레퍼런스를 대입받는 곳은 클래스 타입으로 선언해야 한다.

//소스_Kclass(*) 타입


val myVal4: KClass<*> = String::class

fun myFun(arg: KClass<*>) {

}

// 클래스 레퍼런스

// 리플렉션은 클래스, 함수, 프로퍼티의 레퍼런스를 런타임 때 동적으로 분석하는 목적입니다. 따라서 레퍼런스를 분서갛기 위한 다양한 방법을 제공

// 클래스 정보 분석


open class MyClass9

fun someFun1(arg: KClass<*>) {
    println("class info.........")
    println("isAbstreact : ${arg.isAbstract}")
    println("isCompanion : ${arg.isCompanion}")
    println("isData : ${arg.isData}")
    println("isFinal : ${arg.isFinal}")
    println("isInner : ${arg.isInner}")
    println("isOpen : ${arg.isOpen}")
    println("isSealed : ${arg.isSealed}")
}

fun main55(args: Array<String>) {
    someFun1(MyClass9::class)
}

//생성자 분석

//클래스 분석 중 클래스가 생성자를 포함하고 있는지, 생성자의 매개변수는 어떻게 선언되어 있는지도 중요한 분석 중 하나입니다. 클래스 레퍼런스에서 생성자 정보 분석은 다음의 프로퍼티를 이용

// val constructors: Collection<KFunction<T>> 모든 생성자 정보
// val <T : Any> KClass<T>.primaryConstructor: KFunction<T>? 주 생성자 정보


open class MyClass10(no: Int) {
    constructor(no: Int, name: String) : this(10) {}
    constructor(no: Int, name: String, email: String) : this(10) {}
}

fun someFun(arg: KClass<*>) {
    val constructors = arg.constructors
    for (constructor in constructors) {
        print("constructor.....")
        val parameters = constructor.parameters
        for (parameter in parameters) {
            print("${parameter.name}: ${parameter.type} ..")
        }
        println()
    }
    print("primary constructor.....")
    val primaryConstructor = arg.primaryConstructor
    if (primaryConstructor != null) {
        val parameters = primaryConstructor.parameters
        for (parameter in parameters) {
            print("${parameter.name}: ${parameter.type}")
        }
    }
}

fun main56(args: Array<String>) {
    someFun(MyClass::class)
}

//클래스 프로퍼티 분석

open class SuperClass {
    val superVal: Int = 10
}

class MyClass13(val no: Int) : SuperClass() {
    val myVal: String = "hello"

    val String.someVal: String
        get() = "world"
}

fun someFun13(arg: KClass<*>) {
    val properties = arg.declaredMemberProperties
    println("declaredMemberProperties")
    for (property in properties) {
        println("${property.name}: ${property.returnType} ..")
    }
    println("memberProperties.....")
    val properties2 = arg.memberProperties
    for (property in properties2) {
        println("${property.name}: ${property.returnType} ..")
    }

    println("declaredMembetExtensionProperties")
    val properties3 = arg.declaredMemberExtensionProperties
    for (property in properties3) {
        println("${property.name}: ${property.returnType} ..")
    }
}

fun main57(args: Array<String>) {
    someFun(MyClass::class)
}


val myVal9: Int = 3

var myVar9: Int = 5

class MyClass52 {
    val objVal: Int = 10

    var objVar: Int = 20
}

fun reflectionProperty(obj: Any?, arg: KProperty<*>) {
    println("property name: ${arg.name}, property type: ${arg.returnType}")
    if (obj != null) {
        println(arg.getter.call(obj))
    } else {
        println(arg.getter.call())
    }
}

fun reflectionMutableProperty(obj: Any?, arg: KMutableProperty<*>) {
    println("property name: ${arg.name}, property type: ${arg.returnType}")
    if (obj != null) {
        arg.setter.call(obj, 40)
        println(arg.getter.call(obj))
    } else {
        arg.setter.call(40)
        println(arg.getter.call())
    }
}

fun main52(args: Array<String>) {
    reflectionProperty(null, ::myVal9)
    reflectionMutableProperty(null, ::myVar9)

    val obj: MyClass52 = MyClass52()
    reflectionProperty(obj, MyClass52::objVal)
    reflectionMutableProperty(obj, MyClass52::objVar)
}


// 소스_어노테이션 데이터 타입

annotation class TestAnnotation1(val count: Int)

annotation class TestAnnotation2(val otherAnn: TestAnnotation1, val arg1: KClass<*>)

class User10
/*
annotation class TestAnnotation3(val user: User10) // <----- 에러*/

@TestAnnotation1(10)
@TestAnnotation2(TestAnnotation1(20), String::class)
class Test {}

const val myData: Int = 10

@TestAnnotation1(myData)
class Test2 {}

annotation class TestAnnotation

class Test3 {
    @get:TestAnnotation
    var no: Int = 10
}

// 이용 측 대상 적용

annotation class TestAnnotation4

annotation class TestAnnotation5

class Test4 constructor(@param: TestAnnotation4 var email: String) {
    @get:[TestAnnotation4 TestAnnotation5]
    var no: Int = 10

    @property: TestAnnotation4
    var name: String = "kkang"

    @field: TestAnnotation
    var age: Int = 30

    @setparam: TestAnnotation5
    var phone: String = "0100000"
}

fun @receiver: TestAnnotation4 Test.myFun() {}

/*
@RxJava.JavaAnnotation @JavaAnnotation 이라고만 써져있다.{
                                class Test { }
class Test1 { }
*/
// 소스 _ 델리게이션 패턴

class MyDelegatee {
    fun print(str: String) {
        println("i am delegate : $str")
    }
}

class MyDelegator {
    val delegatee: MyDelegatee = MyDelegatee()

    fun print(str: String) {
        delegatee.print(str)
    }
}

fun main58(args: Array<String>) {
    val obj: MyDelegator = MyDelegator()
    obj.print("hello kkang")
}
//소스 _ by에 의한 위임 패턴

interface Print {
    fun print(arg: String)
}

class MyDelegatee2 : Print {
    override fun print(arg: String) {
        println("i am delegatee : $arg")
    }
}

class MyDelegator2(obj2: MyDelegatee2) : Print by obj2

fun main59(args: Array<String>) {
    val obj2: MyDelegatee2 = MyDelegatee2()
    MyDelegator2(obj2).print("hello kkang")
}

class Test7 {
    var sum: Int = 0
        get() = field
        set(value) {
            field = 0
            for (i in 1 .. value) {
                field = +i
            }
        }
}

fun main60(args: Array<String>){
    val obj: Test7 = Test7()
    obj.sum = 10
    println(obj.sum)
    obj.sum=5
    println(obj.sum)
}

class MySumDelegate {
    var result: Int = 0

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        println("getValue call...ref : $thisRef, property : '${property.name}'")
        return result;
    }

    operator fun setValue(thisRef: Any?,property: KProperty<*>, value: Int){
        result = 0
        println("setValue call...value : $value, '${property.name}'")
        for(i in 1..value) {
            result += i
        }
    }
}

class Test8 {
    var sum: Int by MySumDelegate()
}

fun main61(args: Array<String>){
    val obj: Test8 = Test8()
    obj.sum=10
    println(obj.sum)
    obj.sum = 5
    println(obj.sum)
}

// 타입 에일리어스

// 타입 에일리어스(type alias)란 타입의 이름을 변경하는 방법을 이야기한다. 여기서 타입은 클래스명 혹은 인터페이스명을 가리킴 프로퍼티의 타입으로 지정할 수 있는 것에 대한 이름 변경을 제공한다는 의미이다.

/*
typealias MyInt = Int
typealias MList<T> = MutableList<T>
typealias MC = MyClass
typealias MI = MyInterface

interface MyInterFace
class MyClass: MI

fun main62(args: Array<String>){
    val no: MyInt = 10
    val list: MList<String> = mutableListOf("hello", "kkang")
    val obj: MC = MC()
}
*/

typealias MyType = (Int) -> Boolean
val myFun: MyType = { it > 10 }

//소스_inner 클래스 타입 재정의

class Super12{
    inner class Sub

    fun getSubInsance(): MySub{
        return Sub()
    }
}

typealias MySub = Super12.Sub

// lateinit 키워드로 늦은 초기화

// 초기화를 나중에 할 경우가 있다.

// lazy로 늦은 초기화

// lateinit이 var로 선언한 변수의 늦은 초기화라면 lazy는 값을 변경할 수 없는 val을 사용할 수 있습니다. val 선언 뒤에 by lazy 블록에 초기화에 필요한 코드를 작성한다.

// 마지막 줄에는 초기화할 값을 작성한다. str이 처음 호출될 때 초기화 블록의 코드가 실행된다. println()메서드로 두 번 호출하면 처음에만 "초기화"가 출력된다.

fun main63(args: Array<String>){
    val str: String by lazy{
        println("초기호")
        "hello"
    }

    println(str)   // 초기화; hello
    println(str) // hello
}

// lazy로 늦은 초기화를 하면 앱이 시작될 때 연산을 분산시킬 수 있어 빠른 실행에 도움이 된다.

// lazy는 다음 조건에서만 사용가능

// val에서만 사용가능하다

// 조건이 적기 때문에 상대적으로 lateinit보다 편하게 사용할 수 있다.

// null값이 아님을 보증(!!)

// 변수 뒤에 !!를 추가하면 null값이 아님을 보증하게 된다. 다음과 같이 null값이 허용되는 name 변수의 경우 String? 타입이기 때문에 String 타입으로 변환하려면 !!를 붙여서 null값이 아님을 보증해야 합니다.

val name: String = "키다리"

val name2: String = name //에러
val name3: String? = name // ok

val name4: String = name!! // ok

// 안전한 호출(?.)
// 메서드 호출 시 점 연산자 대신 ?. 연산자를 사용하면 null값이 아닌 경우에만 호출된다. 다음 코드는 str 변수의 값이 null 값이 아니라면 대문자로 변경하고, null값이라면 null을 반환합니다.


fun main64(args: Array<String>){

    val str: String? = null

    var upperCase = if (str != null) str else null // null
    upperCase = str?.toUpperCase() // null
}

// 엘비스 연산자(?:)
// 안전한 호출 시 null이 아닌 기본값을 반환하고 싶을 때는 엘비스 연산자를 함께 사용

fun main65(args: Array<String>){
    val str: String? = null

    var upperCase = if (str != null) str else null // null
    upperCase = str?.toUpperCase() ?: "초기화하시오" // 초기화하시오
}

//컬렉션
//컬렉션은 개발에 유용한 자료구조를 말합니다. 안드로이드 개발에서도 리스트나 맵은 자주 사용되는 자료구조입니다.

//리스트
//리스트는 배열처럼 같은 자료형의 데이터들을 순서대로 가지고 있는 자료구조입니다. 중복된 아이템을 가질 수 있고 추가, 삭제, 교체 등이 쉽습니다.

//맵

// 맵은 키key와 값value의 쌍으로 이루어진 키가 중복될 수 없는 자료구조입니다.

//읽기 전용 앱

fun main66(args: Array<String>){
   // 읽기 전용 맵
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)

    // 변경 가능한 맵
    val citiesMap = mutableMapOf("한국" to "서울", "일본" to "동경", "중국" to "북경")

    // 요소에 덮어쓰기
    citiesMap["한국"] = "서울특별시"
    // 추가
    citiesMap["미국"] = "워싱턴"

    for((k, v) in map){
        println("$k -> $v")
    }
}

//맵 전체의 키와 값을 탐색할 때는 다음과 같이 간단히 탐색할 수 있다.

// 맵의 키와 값을 탐색
fun main67(args: Array<String>){
    // 읽기 전용 집합
    val cityset = setOf("서울", "수원", "부산")

    // 수정 가능한 집합
    val citySet2 = mutableSetOf("서울", "수원", "부산")
    citySet2.add("안양") // [서울, 수언, 부산, 안양]
    citySet2.remove("수원") // [서울, 부산, 안양]

    // 집합의 크기
    println(citySet2.size) //3
    // '서울'이 집합에 포함되었는지
    println(citySet2.contains("서울")) // true
}

// 람다식

// 람다식은 하나의 함수를 표현하는 방법 익명 클래스나 익명 함수를 간결하게 포현할 수 있어서 매우 유용하다

// 람다식은 코드를 간결하게 해주는 장점이 있지만 디버깅이 어렵고 남발할 경우 오히려 코드 가독성이 떨어진다.

// 먼저 두 수를 인수로 받아서 더해주는 add() 메서드입니다.

fun add(x: Int, y: Int): Int{
    return x + y
}

fun add2(x: Int, y: Int) = x + y

//람다식을 변수에 저장할 수 있고 이러한 변수는 일반 함수처럼 사용할 수 있다.

// { 인수1: 타입1, 인수2: 타입2 -> 본문}
fun main68(args: Array<String>){
    var add = {x: Int, y: Int -> x + y}

    println(add(2,5)) //?
}

// SAM 변환

// 코틀린에서는 추상 메서드 하나를 인수로 사용할 때는 함수를 인수로 전달하면 편합니다. 자바로 작성된 메서드가 하나인 인터페이스를 구현할 때는 대신 함수를 작성할 수 있다.

// 이를 SAM(Single Abstract Method)변환이라고 한다.

// SAM 변환의 예를 안드로이드에서 들어보겠다. 안드로이드에서는 버튼의 클릭 이벤트를 구현할 때 onClick() 추상 메서드만을 가지는 View.OnClickListener 인터페이스를 구현합니다.

// 기타기능

// 다음과 같은 기타 유용한 기능에 대해 간단히 살펴보겠다.

// 확장 함수 : 원래 있던 클래스에 기능을 추가하는 함수

// 형변환 : 숫자형 자료형끼리 쉽게 형변환 기능

// 형 체크 : 변수의 형이 무엇인지 검사하는 기능

// 고차 함수 : 인자로 함수를 전달하는 기능

// 동반 객체 : 클래스의 인스턴스 생성 없이 사용할 수 있는 객체

// let() 함수 : 블록에 자기 자신을 전달하고 수행된 결과를 반환하는 함수

// with() 함수 : 인자로 객체를 받고 블록에서 수행된 결과를 반환하는 함수

// apply() 함수 : 블록에 자기 자신을 전달하고 이 객체를 반환하는 함수

// run() 함수 : 익명함수처럼 사용하거나, 블록에 자기 자신을 전달하고 수행된 결과를 반환하는 함수

// 확장 함수

// 코틀린은 확장 함수 기능을 사용하여 쉽게 기존 클래스에 함수를 추가할 수 있다. 확장 함수를 추가할 클래스에 점을

// 찍고 함수 이름을 작성합니다. 확장 함수 내부에서는 이 객체를 this로 접근할 수 있고 이러한 객체를 리시버 객체라고 한다.

// 다음은 Int 자료형에 짝수인지 아닌지를 알 수 있도록 isEven() 확장 함수를 추가한 예입니다.

fun main70(args: Array<String>){
    fun Int.isEven() = this % 2 == 0

    val a = 5
    val b = 6

    println(a.isEven()) // false
    println(b.isEven()) // true
}

//형변환

// 숫자형 자료형끼리는 to자료형() 메서드를 사용하여 형변환이 가능하다.

fun main71(args: Array<String>){
    val a = 10L
    val b = 20

    val c = a.toInt() // Long을 Int로
    val d = b.toDouble() // Int를 Double로
    val e = a.toString() // Long을 String으로
}

// 숫자 형태의 문자열을 숫자로 바꿀 대는 자바와 마찬가지로 Integer.parseInt() 메서드를 사용합니다.

fun main72(args: Array<String>){
    val intStr = "10"
    val str = Integer.parseInt(intStr)
}

// 일반 클래스 간에 형변환을 하려면 as 키워드를 사용합니다.

fun main73(args: Array<String>){
    open class Animal

    class Dog: Animal()

    val dog = Dog()

    val animal = dog as Animal
}

// 형 체크

// is 키워드를 사용하여 형을 체크할 수 있다. 자바의 instanceOf에 대응합니다.

fun main74(args: Array<String>){
    val str = "hello"

    if (str is String){ // str이 String형이라면
        println(str.toUpperCase())
    }
}

//고차 함수

//코틀린에서는 함수의 인수로 함수를 전달하거나 함수를 변환할 수 있다. 이렇게 다른 함수를 인수로 받거나 반환하는 함수를 고차 함수(higher-order function, 고계 함수)라고 한다.

// 인수 : 숫자, 숫자, 하나의 숫자를 인수로 하는 반환값이 없는 함수
fun main75(args: Array<String>){
    fun add(x: Int, y: Int, callback: (sum: Int) -> Unit){
        callback(x + y)
    }

//함수는 {}로 감싸고 내부에서는 반환값을 it로 접근할 수 있음
    add(5, 3, {println(it)}) // 8
}

// 동반 객체

// 안드로이드 프로그먼트 컴포넌트를 다룰 때 자동으로 동반 객체 코드가 생성된다. 그대 코드를 이해할 수 있도록 동반 객체를 간단히 알아보겠다.

class Fragment{
    companion object{
        fun newInstance() {
            println("생성됨")
        }
    }
}

val fragement = Fragment.newInstance()

//let() 함수

//코틀린 기본 라이브러리는 몇 가지 유용한 함수를 제공합니다. let() 함수는 블록에 자기 자신을 인수로 전달하고 수행된 결과를 반환한다. 인수로 전달한 객체는 it으로 참조. let()함수는 안전한 호출 연산자?.와 함께 사용하면 null값이 아닐 때만 실행하는 코드를 다음과 같이 나타낼 수 있다.

// fun <T, R> T.let(block: (T) -> R): R
/*
fun main76(args: Array<String>){
    val result = str?.let{
        Integer.parseInt(it)
    }
}
*/

//str이 null이 아닐 때만 정수로 변경하여 출력하는 코드입니다. 복잡한 if문을 대체 할 수 있습니다.

// with() 함수

// with() 함수는 인수로 객체를 받고 블록에 리시버 객체로 전달합니다. 그리고 수행된 결과를 반환한다. 리시버 객체는 this로 접근할 수 있다. this는 생략이 가능하므로 다음과 같이 작성 가능 안전한 호출이 불가능하여 str이 null값이 아닌 경우에만 사용해야 한다.

// fun <T, R> with(receiver: T, block T.() -> R): R

// apply()함수

// 참조 투명성

// 상태 변이 공유의 캡슐화

// 제어 프름과 제어 구조 추상화

// 올바른 타입의 사용

// 제어 흐름과 제어 구조 추상화

// 올바른 타입의 사용

//버그를 자주 일으키는 다른 근원으로 널(null) 참조가 있다.

// 코틀린을 사용하면 널 참조를 허용하는 코드와 널 참조를 금지하는 코드를 명확하게 분리할 수 있다.

// 하지만 궁극적으로 프로그램에서 널 참조를 아예 사용하지 못하게 막는 일은 개발자 몫이다.

// 외부 세계에 의존하는 프로그램을 제대로 실행하려고 노력하는 과정에서 많은 버그가 발생

// 코틀린은 두 값을 표현하는 Pair 외에 세 값을 표현하는 Triple이라는 클래스도 제공한다.

// 자바 같은 언어에 Pair나 Triple 같은 클래스가 있다면 유용할 것이다. 왜냐하면 Purchase와 같은 클래스를 정의하려면 생성자(constructor)

// 끝까지 추상화하기


// 지금까지 본 것처럼 부수 효과가 없는 순수 함수(pure function)를 합성하면 테스트하기 쉬운 더 안전한 프로그램을 작성할 수 있다.

// 클래스와 인터페이스

// 코틀린 클래스는 자바와 상당히 다른 구문을 사용한다

// 데이터 객체 구조 분해하기

// 프로퍼티가 N개 있는 데이터 클래스에는 component1부터

// 유틸리티 클래스 인스턴스화 방지하기

// 중위(infix) 확장 함수를 호출한다. 코틀린에서는 List를 확장하는 Collection 인터페이스 안에 plus 정의가 들어 있다. 확장 함수는 인자를 받아 새 리스트를 만드는 정적 함수로 컴파일된다. + 연산자를 가변 리스트에 사용할 수도 있지만, +를 사용하면 불변 리스트와 같은 결과를 돌려받게 되며, 원래의 가변 리스트는 변하지 않고 그대로 남는다.

// 가시성

// 코틀린의 가시성(visibility)은 자바와 약간 다르다. 함수와 프로퍼티를 클래스 내부는 물론 패키지 수준에서도 정의할 수 있다. 패키지 수준에서 정의한 모든 대상은 기본적으로 공개(public) 가시성이다. 어떤 대상이 비공개(private)라면 같은 파일 안에서만 이 대상을 볼 수 있다.

// 대상을 내부(internal) 가시성으로 선언할 수도 있다. 내부 가시성은 같은 모듈 안에서만 볼 수 있다.
//는 뜻이다.

//모듈(module)은 한꺼번에 컴파일되는 파일 묶음을 뜻한다. 모듈 종류는 다음과 같다.

//메이븐(Maven) 프로젝트

//그레이들(Gradle) 소스 세트(source set)

// 인텔리j(Intellij IDEA) 프로젝트

//가시성 변경자 private, protected, internal, public

// 함수 선언하기

// 함수 오버라이드하기

// 확장 함수 사용하기 자바와 달리 오버라이드할 때는 override 키워드를 반드시 붙여야 한다.

// 이것 때문에 자바 코드보다 코틀린 코드가 더 길어지는 희귀한 경우

// 하지만 이렇게 하면 실수로 함수를 오버라이딩하는 것을 방지할 수 있어서 프로그램이 훨씬 더 안전해진다(자바에서 @Override 에너테이션을 활용하면 똑같은 효과를 얻을 수 있다.)

// 확장 함수 사용하기

// 길이를 돌려주는 length라는 함수를 다음과 같이 정의하고 싶다고 해보자.


fun <T> length1(list: List<T>) = list.size

fun <T> List<T>.length() = this.size

fun List<Int>.product(): Int = this.fold(1) {a, b -> a * b}

val ints = listOf(1,2,3,4,5,6,7)

val product = ints.product()

//람다 사용하기

// 널이 될 수 있는 타입 다루기

// 코틀린에서는 안전한 호출(safe call) 연산자 ?.로 이를 짧게 처리할 수 있다.

// 엘비스 연선자와 기본 값
// null이 아닌 기본 값을 사용하고 싶을 때가 있다. 이는 자바의 Optional.getOrElse()에 해당한다. 이때 엘비스(Elvis) 연산자를 사용하면 된다.

// 프로그램 흐름과 제어 구조




// 조건 선택 사용하기

// 자바에서는 if ... else 구문은 제어 구조를 만들어낸다. if ... else는 조건을 검사해 조건이 성립하는지 여부에 따라 프로그램이 두 명령 블록 중 한쪽으로 흘러가게 한다. 다음은 간단한 자바 예제다.

// 다중 조건 선택 사용하기

// 조건 분기가 3개 이상 있으면 자바에서는 switch ... case 구조를 사용한다. 정숫값, 이넘(enum), 문자열 값에 이를 사용할 수 있다.

// 코틀린에서는 when 구문을 사용한다 when 구문은 제어 구문이 아니라 식이다.
/*
fun main80(args: Array<String>){
    val country = ...

    val capital = when (country){
        "Australia" -> "Canberra"
        "Bolivia" -> "Sucre"
        "Brazil" -> "Brasilia"
        else -> "Unknown"
    }
}
*/

fun main81(args: Array<String>){
    val num: Int = try{
        args[0].toInt()
    }catch(e: Exception){
        0
    }finally{
        // 이 블록 안에 있는 코드는 항상 실행됨
    }
}

// 이미 if ... else에서 본 것처럼 각 블록이 반환하는 값은 블록의 마지막 줄을 평가한 값이다. if ... else와의 차이는 try ... catch ... finally에서는 중괄화({})가 필수라는 점에 있다. try ... catch ...finally가 반환하는 값은 예외가 발생하지않으면 try 블록의 마지막 줄을 평가한 값이고, 예외가 발생하면 catch 블록의 마지막 줄을 평가한 값이다.

// 사용자 자원 자동으로 닫기

// 자바에서 자원을 사용하는 try로 할 수 있는 것처럼 코틀린도 자원을 자동으로 닫을 수 있다. 이때 자원은 Closable이나 AutoClosable 중 하나를 구현해야 한다. 자바와 코틀린의 가장 큰 차이는 코틀린에서는 use라는 함수를 사용한다는 점이다.

fun main82(args: Array<String>){
    File("myFile.txt").inputStream().use{
    it.bufferedReader()
        .lineSequence()
        .forEach (::println)
    }
}


//이 코드는 단지 자동으로 닫을 수 있는 자원을 어떻게 처리하는지 보여주는 예제일 뿐이다. 자원 처리르 제외하면 코드 자체는 그리 흥미로운 부분이 없다.

//lineSequence 함수는 Sequence를 반환 하는데 이는 지연 계산 컬렉션이다. 지연(lazy) 계산이란 말은 계산(파일에서 매 줄을 읽어오기)이 나중에 실제 각 줄을 사용할 때 이뤄진다는 뜻이다. 여기서는 시퀀스 안의 각 줄이 실제 사용될 때 (forEach에 의해 각 줄을 출력할 때) 계산이 이뤄진다.

//use 함수 블록을 벗어나면 입력 스트림이 자동으로 닫혀야 하기 때문에 더 이상 스트림을 쓸 수 없다. 따라서 다음 예제와 같이 lineSequence로 시퀀스를 만들고 use 블록의 밖에서 이 시퀀스를 사용하는 코드는 컴파일은 되지만 실행 시점에 IOExceptionL Stream Closed 오류가 발생한다.
fun main83(args: Array<String>){
    val lines: Sequence<String> = File("myFile.txt")
        .inputStream()
        .use {
            it.bufferedReader()
                .lineSequence()
        }
    lines.forEach (::println)
}

fun main84(args: Array<String>){
    val lines: List<String> = File("myFile.txt")
        .inputStream()
        .use {
            it.bufferedReader()
                .lineSequence()
                .toList()
        }
    lines.forEach(::println)
}

// 이렇게 처리하면 파일 전체를 메모리에 저장한다는 문제가 있다. 파일을 한 줄씩 처리하고 싶다면 좀 더 단순한 방법으로 forEachLine 함수를 사용하면 된다.

// File("myFile.txt").forEachLine{println(it)}

// Sequence를 반환하는 useLines를 쓰는 방법도 있다. useLines를 써서 앞의 예제와 동일한 코드를 만들면

// File("myFile.txt").useLines{it.forEach(::println)}

// 이런 느낌으롬 만드는게 가능하다.

// 스마트 캐스트

// 자바에서는 참조를 다른 타입으로 강제 타입 변환(cast)해야 할 때가 가끔 있다 .참조 대상 객체가 지정한 타입으로 변환이 불가능한 타입의 객체라면 ClassCastException이 발생한다. 따라서 항상 instanceof 연산자로 먼저 타입 변환이 가능한지 검사해야 한다.


// 스마트 캐스트
/*fun main85(args: Array<String>){
    val payload: Any = message.payload

    val length: Int = if (payload is String)
        payload.length
    else
        -1
}

fun main86(args: Array<String>){
    val result: Int = when (payload){
        is String -> payload.length
        is Int -> payload
        else -> -1
    }
}*/

// 동등성과 동일성

//자바에서 빠지기 쉬운 함정은 하나는 동등성(equality)과 동일성(identity)을 혼동하는 것이다.

//자바 원시 타입이나 문자열 인터닝(interning), Integer를 자바에서 처리하는 방식 때문에 이런 문제가 더 복잡해진다.

// 문자열 인터폴레이션

// 코틀린은 자바보다 훨씬 간단한 구문으로 문자열과 값을 혼용할 수 있게 한다.


//자바 String.format() 메서드를 사용했을때

//System.out.println(String.format("$s"s registration date: %s", name, date));

//코틀린에서

//println("$name's registration date: $date")

//문자열 안에 식을 넣을 수도 있다. 다만, 중괄화({})로 식을 둘러싸야 한다.
//println("$name's registration date: ${date.atZone(ZoneId.of("America/Los_Angeles"))}")

//이런 기법을 문자열 인터폴레이션(string interpolation)이라고 부른다. 문자열 인터폴레이션을 사용하면 문자열을 더 쉽게 처리할 수 있고 코트를 더 읽기 좋게 만들 수 있다.

// 여러 줄 문자열

// 코틀린에서는 3중 따움표(""")를 사용해 여러 줄로 이뤄진 문자열도 쉽게 사용할 수 있다. 이때 trimMargin 함수를 활용하면 좀 더 편리하다.

//println("""This is the first line |and this is the second one.""".trimMargin())

// 서비스 팩토리

// 인터셉터Interceptor를 준비


//동등성과 동일성

//자바에서 빠지기 쉬운 함정의 하나는 동등성(equality)와 동일성(identity)을 혼동하는 것이다.
//자바 원시 타입이나 문자열 인터닝(interning), Integer를 자바에서 처리하는 방식 때문에 이런 문제가 더 복잡해진다.

//객체에서는 동일성을 계산한다. 동등성, 즉 구조 동등성은 ==로 검사하며, equal와 ==는 같다. 동등성 검사(==)가 동일성 검사(===)보다 더 짧기 때문에 많은 실수를 방지할 수 있다. ==와 ===를 각각 !=와 !==로 부정할 수 있다.

//문자열 인터폴레이션
fun main85(args: Array<String>){
    println("""This is the first line | and this is the second one.""".trimMargin())
}

// trimMargin 함수는 파라미터로 문자열을 받는다. 이 문자열을 정렬에 사용할 위치를 지정할 때 사용한다. 앞의 코드를 보면 알 수 있듯이 기본 값은 '|'다

// 변성: 파라미터화한 타입과 하위 타입

// 변성(variance)은 파라미터화한 타입이 서로 어떤 하위 타입 관계에 있는지 결정하는 방식을 뜻함

// val ls = listOf("A String")
// val la = ls + 42 // <--- 코틀린은 'la'의 타입을 'List<Any>로 추론함'

//자바에서는 파라미터화한 타입이 파라미터 타입에 대해 무공변성이다. 즉, A가 B의 부모 타입이라 하더라도

//String 타입의 elem이 List<Any>에 추가될 수 있다, 그렇게 해도 아무 문제가 없다.

/*fun <T> addAll(list1: MutableList<T>,
               list2: MutableList<out T>){ // <--- T를 공변성으로 만듦
    for(elem in list2) list1.add(elem)
}

val ls = mutableListOf("A String")
val la: MutableList<Any> = mutableListOf()
addAll(la, ls)
        */

// out키워드는 list2 파라미터가 T 타입에 대해 공변성적임을 표시한다.

// 사용 지점 변성과 선언 지점 변성성

// 앞에서 본 선언 지점 변성()

//여기서 컴파일러 오류가 발생하는 이유는 useBag이 Bag

// 코틀린 역함수

// 함수에 역함수

// 정의역에 있는 원소 중에서 공역에 대응하는 원소가 없는 원소는 있을 수 없다.

// 공역에 있는 원소 중에서 어느 두(또는 그 이상의) 원소가 정의역에 있는 한 원소에 대응할수는 없다.

// 공역에 있는 원소 중에는 정의역에 있는 원소에 대응하지 않는 원소가 있을 수 있다.

// 공역에 있는 원소 중에는 하나 이상의 정의역 원소에 대응하는 원소가 있을 수 있다.

// 공역에 있는 원소 중에서 대응하는 정의역 원소가 있는 원소들만으로 이루어진 집합을 치역(image)이라고 부른다.

// f(x) = X + 1

// 여기서 X는 양의 정수다. 이 함수는 각 정수와 그 자신의 다음 값(successor)을 대응시킨다. 이 함수에 어떤이름이든 붙일 수 있다. 특히 다음처럼 함수가 하는 일을 기억하기 좋게 이름을 붙일 수 있다.

// 여기서 함수가 어떤 동작을 하는지가 아닌 어떤 것인지(정의)를 이야기하고 있음에 유의

// successor 함수는 인자로 받은 x에 1을 더하지 않는다.여러분은 다음값을 계산하기 위해 정숫값에 1을 더할 수 있다. 하지만 successor 함수는 이런 계산을 수행하지 않는다.

// 부분 함수 다루기

// 합성 합수 이해하기

// 함수는 서로 합성해서 다른 함수를 만들어낼 수 있는 빌딩 블록이다.

// 인자를 여럿 받는 함수

// 함수를 데이터로 이해하기

// 함수는 데이터와 비슷하다. String이나 Int 등의 타입이 있는 다른 데이터와 마찬가지로 함수도 타입이 있다. 그리고 다른 데이터와 마찬가지로 함수도 참조에 대입할 수 있다. 조금 뒤에 보겠지만 함수를 다른 함수에 인자로 넘길 수 있고, 함수가 함수를 반환할 수 있다.

// 다만(자바 메서드와 마찬가지로) fun으로 정의한 함수는 이런 방식으로는 조작할 수 없다. 하지만 코틀린은 이런 메서드를 진짜 함수로 변환할 때 필요한 모든 메커니즘을 제공한다.

// 데이터를 함수로 이해하기

/*fun append(i: Int, list: MutableList<Int>): List<Int>{
    list.add(i)
    return list
}

fun append2(i: Int, list: List<Int>) = list + i*/

//하지만 실제로는 그렇지 않다. list + i라는 식은 list의 모든 원소가 같은 순서로 있고 맨 뒤에 i가 더 들어 있는 새로운 (불변)리스트를 만들어낸다. 아무것도 변이된 것이 없다. 따라서 append2는 순수 함수다.

// 객체 표기법과 함수 표기법 비교

// (동반 객체에 들어가는 함수를 자바의 정적 메서드와 같다고 봐도 된다.)

// 아예 어느 클래스에도 속하지 않도록 패키지 수준에 넣어도 된다. 그리고 클래스 인스턴스를 명시적으로 파라미터로 받게 만들면 클래스 인스턴스의 프로퍼티에 접근하는 함수를 동반 객체나 패키지 수준에 넣을 수 있다.
/*open class Payment{
    var creditCard = 0

    fun combine(payment1 : Payment, payment2: Payment): Payment=
        if(payment1.creditCard, patment1.amount + payment2.amount){
           Payment(payment1.creditCard, payment1.amout)
        }else{
            throw IllegalStateException("Cards don't match")
        }


}*/

// 함수를 사용하려면 this 참조를 넘겨야 한다.



// 함수 값 사용하기
// 함수를 데이터처럼 다룰 수 있지만 fun으로 정의된 함수는 그렇지 않다.

// 코틀린은 함수를 데이터처럼 다룰 수 있어야 한다.

// 코틀린에는 함수 타입이 있으며, 일반적인 데이터를 그 데이터의 타입과 일치하는 참조에 대입할 수 있는 것처럼 함수를 그 함수와 일치하는 타입의 참조에 대입할 수 있다.

fun double(x: Int): Int = x * 2

val double: (Int) -> Int = {x -> x * 2}

// 함수 값 사용하기

// 앞에서 함수를 데이터처럼 다룰 수 있지만 fun으로 정의된 함수는 그렇지 않다고 말했다.

// 코틀린은 함수를 데이터처럼 다룰 수 있게 한다. 코틀린에는 함수 타입이 있으며, 일반적인 데이터를 그 데이터의 타입과 일치하는 참조에 대입할 수 있는 것처럼 함수를 그 함수와 일치하는 타입의 참조에 대입할 수 있다.

// 여기서 double 함수의 타입은 (Int) -> Int다. 화살표의 왼쪽에는 파라미터 타입을 괄호로 둘러싸서 표시한다. 반환 타입은 화살표 오른쪽에 위치한다. 함수 정의는 등호 뒤에 온다. 이때 함수 정의를 중괄화({})로 둘러싼 람다 식 형태를 사용한다.

// 이 예제에서 람다는 파라미터 해당하는 이름(x)

// 함수를 사용하려면 this 참조를 넘겨야 한다.

// 함수 타입에서 인자는 괄호 안에 들어 있다. 반면 람다 식에서 파라미터 주변에는 괄호를 쓸 수 없다.

// 파라미터가 튜플이 아니라면(더 정확히 말해 파라미터가 원소가 1개뿐인 튜플인 경우), it이라는 특별한 이름을 사용해 그 파라미터를 가리킬 수 있다.

fun main90(args: Array<String>){
    val double: (Int) -> Int = {it * 2}
}

// it을 사용하면 더 간결하게 람다 식을 쓸 수 있지만, 때로 코드 읽기가 더 어려줘일 수 있다. 특히 여러 함수 구현의 내포 깊이가 깊어지면 더 그렇다.

// 이 예제에서 double은 함수 이름이 아니다.

// val number: Int = 5

// 라고 쓸 때, 이는 5의 이름이 number이라고 쓴 것은 아니다. 함수에서도 마찬가지

// 코틀린에 함수가 두 종류나 있는 이유가 궁금한 독자도 있을 것이다. 함수가값이라면 굳이 fun을 사용해 함수를 정희해야 하는 이유가 무엇일까?

// 메서드, 하위 프로그램, 프러시저 등으로 부를 수 있다. fun으로 정의한 함수로 (항상 인자에 의해서만 값이 정해지고 외부에서 관찰할 수 있는 효과가 없는) 순수 함수를 표현할 수 있지만, 이를 데이터처럼 취급할 수는 없다.

// fun함수가 더 효율적이기 때문. 이를 최적화로 볼 수 있다. 인자를 넘기고 그 인자에 따른 반환 값을 얻는 일만 하는 경우에는 fun으로 정의한 함수를 사용하라.

// 꼭 fun으로 정의한 함수를 사용할 필요는 없지만 사용하는 편이 더 낫다.

// 한편 함수를 데이터처럼 취급해야 하거나(예를 들어 곧 보게 되겠지만 다른 함수에 함수를 인자로 넘겨야 한다면) 함수에서 함수를 돌려줘야 하는 경우이거나 변수, 맵 그 밖의 데이터 구조에 함수를 저장해야 하는 경우라면 함수 타입의 식을 사용하라.

// 한 유형의 함수를 다른 유형의 함수로 변환하는 방법이 궁금할 것이다. 변환은 아주 간단하다. 다만 실행 시점에 fun 함수를 만들 수는 없기 때문에 fun 함수를 함수식 유형의 값으로 변환할 수만있다.

// 함수 참조 사용하기

// 코틀린은 자바와 같은 방식으로 메서드 참조를 사용할 수 있다. 그런데 코틀린에서는 메서드를 함수라고 부르기 때문에 메서드 참조도 함수 참조라고 부른다. 다음은 fun 함수를 람다 안에서 사용하는 코드를 보여준다.
fun main91(args: Array<String>){
    fun double(n: Int): Int = n * 2
    val mutliplyBy2: (Int) -> Int = {n -> double(n)} // -> 이렇게 쓸수도 있고
    // 혹은 val mutliplyBy2: (Int) -> Int = {double(it)}

    // 함수 참조를 사용하면 같은 구문을 더 간단히 쓸 수 있다.

    // val mutliplyBy2: (Int) -> Int = ::double

    // 여기서 multiplyBy2 함수가 들어 있는 객체나 클래스, 패키지에 들어 있는 double 함수가 호출된다.
}

class MyClassStudy{
    fun double(n: Int): Int = n * 2
}

val foo = MyClassStudy()
val mutliplyBy2: (Int) -> Int = foo::double

// double이 다른 패키지에 들어 있다면 이를 임포트해야한다.

// import other.package.double 이걸 임포트 해줘야 하는데 import 걸어줄려고 했더니 import 쪽에서 에러가 난다.

// val mutliplyBy2: (Int) -> Int = ::double

// 반면에 클래스 이름을 붙이면 전혀 다른 타입의 함수가 생긴다.

val mutliplyBy3: (MyClassStudy, Int) -> Int = MyClassStudy::double // { obj: MyClass, n: Int -> (obj::double)(n)}과 같다.

// 이런 경우 multiplyBy2의 타입은 (Int) -> Int가 아니고 (MyclassStudy, Int) -> Int다. 클래스의 동반 객체에 정의된 함수라면(자바 정적 메서드와 비슷하다) 이를 임포트하거나 다음과 같은 구문으로 사용할 수 있다.

class MyClassStudy2{
    companion object{
        fun double(n: Int): Int = n * 2
    }
}

val mutliplyBy4: (Int) -> Int = (MyClassStudy2)::double

// 여기서 (MyClassStudy2)::double 이 부분은 val mutliplyBy2: (Int) -> Int = MyClassStudy2.Companion::double 이걸 줄여 쓴 것

// .Companion이나 괄호를 잊지 말기. 이걸 빼먹으면 전혀 다른 결과를 얻게 된다.

// 컴파일러가 인스턴스 메서드 double을 찾을 수 없다.

// 함수 합성

// fun 함수는 쉽게 합성할 수 있다.



data class Person(val name: String, // <- '데이터' 클래스
                    val age: Int?=null) // <---- 널이 될 수 있는 타입(Int?)과 파라미터 디폴트 값

fun main92(arge: Array<String>){ // <---- 최상위 함수
    val persons = listOf(Person("영희"), Person("철수", age = 29)) //<---- 이름 붙은 파라미터

    val oldset = persons.maxBy{it.age ?: 0}
    println("나이가 가장 많은사람: $oldset") // <--- 문자열 템플릿
}

//결과: 나이가 가장 많은 사람: Person(name=철수, age=29) <---- toString 자동 생성

// name과 age라는 프로퍼티property가 들어간 간단한 데이터 클래스data class를 정의한다 age 프로퍼티의 디폴트 값은 (따로 지정하지 않은 경우) null이다. 사람 리스트를 만들면서 영희의 나이를 지정하지 않았기 때문에 null이 대신 쓰인다. 리스트에서 가장 나이가 많은 사람을 찾기 위해 maxBy 함수를 사용한다. maxBy 함수에 전달한 람다 식(lambda expression)은 파라미터를 하나 받는다


//상호운용성

//상호운용성(interoperability)과 관련해 자바 프로그래머들이 던지는 첫 번째 질문은 아마도 "기존 라이브러리를 그대로 사용할 수 있나?"일 것이다.

// 코틀린의 클래스나 메소드를 일반적인 자바 클래스나 메소드와 똑같이 사용할 수 있다. 이에 따라 자바와 코틀린 코드를 프로젝트에서 원하는 대로 섞어 쓸 수 있는 궁극적인 유연성을 발휘할 수 있다.

// 기존 자바 프로젝트에 코틀린을 도입하는 경우 자바를 코틀린으로 변환하는 도구를 코드베이스 안에 있는 자바 클래스에 대해 실행해서 그 클래스를 코틀린 클래스로 변환할 수 있다.

//기본 요소: 함수와 변수

// 이번 절에서는 모든 프로그램을 구성하는 기본 단위인 함수와 변수를 살펴본다. 코틀린에서 타입 선언을 생략해도 된다는 사실을 보고, 코틀린이 어떻게 변경 가능한 데이터보다 변경할 수 없는 불변 데이터 사용을 장려하는지 배운다.

// Hello.World!

fun main94(args: Array<String>){
    println("Hello, world!")
}


// 함수를 선언할 때 fun 키워드를 사용한다. 실제로도 코틀린 프로그래밍은 수많은 fun을 만드는 재미있는 fun 일이다!

// 파라미터 이름 뒤에 그 파라미터의 타입을 쓴다. 나중에 보겠지만 변수를 선언할 때도 마찬가지 방식으로 타입을 지정한다.

// 함수를 최상위 수준에 정의할 수 있다. (자바와 달리) 꼭 클래스 안에 함수를 넣어야 할 필요가 없다.

// 배열도 일반적인 클래스와 마찬가지다. 코틀린에서는 자바와 달리 배열 처리를 위한 문법이 따로 존재하지 않는다.

// System.out.pritln 대신에 println이라고 쓴다. 코틀린 표준 라이브러리는 여러 가지 표준 자바 라이브러리 함수를 간결하게 사용할 수 있게 감싼 래퍼wrapper를 제공한다. println도 그런 함수 중 하나다.

// 최신 프로그래밍 언어 경향과 마찬가지로 줄 끝에 세미콜론(;)을 붙이지 않아도 좋다.

// 함수

// 아무런 값도 반환하지 않는 함수를 어떻게 선언하는지 방금 살펴봤다. 하지만 의미 있는 결과를 반환하는 함수의 경우 반환 값의 타입을 어디에 지정해야 할까? 파라미터 목록 뒤의 어디쯤에 반환 타입을 추가하면 되리라 추측할 수 있다.

// 코틀린 REPL에서 한번 시험해보자

fun max(a: Int, b: Int): Int{
    return if(a > b) a else b
    println(max(1, 2))
}


fun max2(a: Int, b: Int): Int{  // max2는 함수 이름
    return if (a > b) a else b
}

// 식이 본문인 함수

// 조금 전에 살펴본 함수를 더 간결하게 표현할 수도 있다. 핲의 함수 본문은 if 식 하나로만 이뤄져 있다. 이런 경우 다음과 같이 중괄호를 없애고 return을 제거하면서 등호(~)를 식 앞에 붙이면 더 간결하게 함수를 표현할 수 있다.

fun max3(a: Int, b: Int) = if (a>b) a else b
//여기서 반환 타입을 생략할 수 있는 이유는 무엇일까? 코틀린은 정적 타입 지정 언어이므로 컴파일 시점에 모든 식의 타입을 지정해야 하지 않는가? 실제로 모든 변수나 모든 식에는 타입이 있으며, 모든 함수는 반환 타입이 정해져야 한다. 하지만 식이 본문인 함수의 경우 굳이 사용자가 반환 타입을 적지 않아도 컴파일러가 함수 본문 식을 본석해서 식의 결과 타입을 함수 반환 타입으로 정해준다.

// 이렇게 컴파일러가 타입을 분석해 프로그래머 대신 프로그램 구성 요소의 타입을 정해주는 기능을 타입 추론(type inference)이라 부른다.

// 식이 본문인 함수의 반환 타입만 생략 가능하다는 점에 유의하라. 블록이 본문인 함수가 값을 반환한다면 반드시 반환 타입을 지정하고 return문을 사용해 반환 값을 명시해야 한다.

// 변수

// 자바에서는 변수를 선언할 때 타입이 맨 앞에 온다.

// 코틀린에서는 타입 지정을 생략하는 경우가 흔하다. 타입으로 변수 선언을 시작하면 타입을 생략할 경우 식과 변수 선언을 구별할 수 없다.

// 그런 이유로 코틀린에서는 키워드로 변수 선언을 시작하는 대신 변수 이름 뒤에 타입을 명시하거나 생략하게 허용한다. 변수를 몇 개 선언해보자.

fun main95(args: Array<String>){
    val question = "삶, 우주, 그리고 모든 것에 대한 궁극적인 질문"

    val answer = 42

    //이 예제에서는 타입 표기를 생략했지만 원한다면 타입을 명시해도 된다.

    val answer2: Int = 42 // 이런 느낌으로
}


// 이 책의 두 저자는 젯브레인스의 코틀린 개발 팁에서 일한다. - 옮긴이

// 식이 본문인 함수에서와 마찬가지로 여러분이 타입을 지정하지 않으면 컴파일러가 초기화 식을 분석해서 초기화 식의 타입을 변수 타입을 변수 타입으로 지정한다. 여기서 초기화 식은 42로 Int 타입이다. 따라서 변수도 Int 타입이 된다.

// 부동소수점(floating point) 상수를 사용한다면 변수 타입은 Double이 된다.

fun main96(args: Array<String>){
    val answer: Int
    answer = 42
}

// 초기화 식이 없다면 변수에 저장될 값에 대해 아무 정보가 없기 때문에 컴파일러가 타입을 추론할 수 없다. 따라서 그런 경우 타입을 반드시 지정해야 한다.

// 변경 가능한 변수와 변경 불가능한 변수

// 변수 선언 시 사용하는 키워드는 다음과 같은 2가지가 있다.

// 더 쉽게 문자열 형식 지정: 문자열 템플릿

// 이 절의 서두에 있는 'Hello, World!' 예제로 다시 돌아가자. 다음은 그 예제의 다음 단계로, 사람 이름을 사용해 환영 인사를 출력하는 코틀린 프로그램

// 문자열 템플릿 사용

fun main97(args: Array<String>){
    val name = if (args.size > 0) args[0] else "Korlin"
    println("Hello, $name!") // <---- "Bob"을 인자로 넘기면 "Hello, Bob!"을 출력하고 아무 인자도 없으면
}

// 이 예제는 문자열 템플릿(string template)이라는 기능을 보여준다. 이 코드는 name이라는 변수를 선언하고 그 다음 줄에 있는 문자열 리터럴 안에서 그 변수를 사용했다. 여러 스크립트 언어와 비슷하게 코틀린에서도 변수를 문자열 안에 사용할 수 있다. 문자열 리터럴의 필요한 곳에 변수를 넣되 변수 앞에 $를 추가해야 한다.

// 이 문자열 템플릿은 자바의 문자열 접합 연산("Hello, " + name + "!")과 동일한 기능을 하지만 좀 더 간결하며, 자바 문자열 접합 연산을 사용한 식과 마찬가지로 효율적이다.

// 한글을 문자열 템플릿에서 사용할 경우 주의할 점

// 코틀린에서는 자바와 마찬가지로 한글(사실은 한글 뿐 아니라 '글자(letter)'로 분류할 수 있는 모든 유니코드 문자)을 식별자에 사용할 수 있으므로 변수 이름에 한글이 들어갈 수 있다. 그런 유니코드 변수 이름으로 인해 문자열 템플릿을 볼 때 오해가 생길 수 있다. 문자열 템플릿 안에$로 변수를 지정할 때 변수명 바로 뒤에 한글을 붙여서 사용하면 코틀린 컴파일러는


// 커스텀 접근자

// 프로퍼티의 접근자를 직접 장성하는 방법

// 직사각형 클래스인 Rectangle을 정의하면서 자신이 정사각형인지 알려주는 기능을 만들어보자

// 직사각형이 정사각형인지를 별도의 필드에 저장할 필요가 없다.

// 사각형의 너비와 높이가 같은지 검사하면 정사각형 여부를 그때그때 알 수 있다.

class Rectangle(val height: Int, val width: Int){
    val isSquare: Boolean
    get(){ //<----- 프로퍼티 게터 선언
        return height == width
    }
}

//isSquare 프로퍼티에는 자체 값을 저장하는 필드가 필요 없다. 이 프로퍼티에는 자체 구현을 제공하는 게터만 존재한다.

//코틀린 소스코드 구조: 디렉터리와 패키지

//자바의 경우 모든 클래스를 패키지 단위로 관리한다.

//코틀린에도 자바와 비슷한 개념의 패키지가 있다. 모든 코틀린 파일의 맨 앞에 package문을 넣을 수 있다. 그러면 그 파일 안에 있는 모든 선언(클래스, 함수, 프로퍼티 등)이 해당 패키지에 들어간다. 같은 패키지에 속해 있다면 다른 파일에서 정의한 선언일지라도.

//직접 사용할 수 있다. 반면 다른 패키지에 정의한 선언을 사용하려면 임포트를 통해 선언을 불러와야 한다. 자바와 마찬가지로 임포트문은 파일의 맨 앞에 와야 하며 import키워드를 사용한다.

class Rectangle2(val height: Int, val width: Int){
    val isSquare3: Boolean
    get() = height == width
}
fun createRandomRectangle() : Rectangle2{
    val random = Random()
    return Rectangle2(random.nextInt(), random.nextInt())
}

//코틀린에서는 클래스 임포트와 함수 임포트에 차이가 없다, 모든 선언을 import 키워드로 가져올 수 있다. 최상위 함수는 그 이름을 써서 임포트할 수 있다.

// 다른 패키지에 있는 함수 임포트하기

//간단한 enum 클래스 정의하기

enum class Color{
    RED, ORANGE, TELLOW, GREEN, BLUE, INDIGO, VIOLET
}

// enum은 자바 선언보다 코틀린 선언에 더 많은 키워드를 써야 하는 흔치 않은 예다. 코틀린에서는 enum class를 사용하지만 자바에서는 enum을 사용한다. 코틀린에서 enum은 소프트 키워드(soft keyword)라 부르는 존재다. enum은 class 앞에 있을 때는 특별한 의미를 지니지만 다른 곳에서는 이름에 사용할 수 있다.

// 반면 class는 키워드다. 따라서 class라는 이름을 사용할 수 없으므로 클래스를 표현하는 변수 등을 정의할 때는 clazz나 aClass와 같은 이름을 사용해야 한다.

// 자바와 마찬가지로 enum은 단순히 값만 열겨하는 존재가 아니다. enum클래스 안에도 프로퍼티나 메소드를 정의할 수 있다. 다음은 프로퍼티와 메소드를 enum안에 선언하는 방법을 보여준다.

enum class Color2(val r: Int, val g: Int, val b: Int){
    RED(255,0,0), ORANGE(255,165,0),
    YELLOW(255,255,0),GREEN(0,255,0),BLUE(0,0,255),
    INDIGO(75,0,130),VIOLET(238,130,238);

    fun rgb() = (r * 256 + g) * 256 + b
}

// 각 enum상수를 정의할 때는 그 상수에 해당하는 프로퍼티 값을 지정해야만 한다. 이 예제에서는 코틀린에서 유일하게 세미콜론(;)이 필수인 부분을 볼 수 있다. enum 클래스 안에 메소드를 정의하는 경우 반드시 enum 상수 목록과 메소드 정의 사이에

// 세미콜론을 넣어야한다. 이제 enum 상수로 할 수 있는 멋진 일을 살펴보자.

// when으로 enum 클래스 다루기

//(그리고 그 연상 단어 정보를 enum안에 저장하지는 않는다고 하면). 자바라면 switch문으로 그런 함수를 작성할 수 있다.

// switch에 해당하는 코틀린 구성 요소는 when이다.

// if와 마찬가지로 when도 값을 만들어내는 식이다. 따라서 식이 본문인 함수에 when을 바로 사용할 수 있다.

/*fun getMnemonic(color: Color) = when (color){
    Color.RED -> "Richard"
    Color2.ORANGE -> "Of"
    Color.YELLOW -> "York"
}*/

// when을 사용해 올바른 enum 값 찾기

// 한 whne 분기 안에 여러 값 사용하기

// when과 임의의 객체를 함께 사용

// 코틀린에서 when은 자바의 switch보다 훨씬 더 강력하다. 분기 조건에 상수(enum 상수나 숫자 리터럴)만을 사용할 수 잇는 자바

// 인자가 없는 when

// 식을 표현하는 클래스 계층

interface Expt

class Num(val value: Int) : Expt // <---- value라는 프로퍼티만 존재하는 단순한 클래스로 Expr 인터페이스를 구현한다.
class Sum(val left: Expt, val right: Expt) : Expt // <---- Expr 타입의 객체라면 어떤 것이나 Sum 연산의 인자가 될 수 있다. 따라서 Num이나 다른 Sum이 인자로 올 수 있다.

// if 연쇄를 사용해 식을 계산하기

fun eval(e: Expt): Int{
    if(e is Num){
        val n = e as Num
        return n.value
    }
    if(e is Sum){
        return eval(e.right) + eval(e.left)
    }
    throw IllegalArgumentException("Unknown expression")
}

// if와 when의 분기에서 블록 사용

// if나 when 모두 분기에 블록을 사용할 수 있다. 그런 경우 블록의 마지막 문장이 블록 전체의 결과가 된다. 예제로 봤던 eval 함수에 로그를 추가하고 싶다면 각 분기를 블록으로 만들고 블록의 맨 마지막에 그 분기의 결과 값을 위치시키면 된다.

// 분기에 복잡한 동작이 들어가 있는 when 사용하기

fun evalWithLogging(e: Expt): Int =
    when (e){
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }

// when을 사용해 피즈버즈 게임 구현하기
fun fizzBuzz(i: Int) = when{
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

// 증가 값을 갖고 범위 이터레이션하기
fun main99(args: Array<String>){
    for (i in 100 downTo 1 step 2){
        print(fizzBuzz(i))
    }
}

// compos 함수는 (Int) -> Int  타입의 함수 두 개를 파라미터로 밭아 똑같은 타입의 함수를 반환한다. compose 함수를 fun으로 정의할 수 있지만 함수의 파라미터는 값이어야만 한다.

// fun으로 정의한 myFunc라는 함수가 있으면 앞에 ::를 붙여서 함수 값으로 변환할 수 있다.

// 함수 재사용하기

// 함수를 쉽게 재사용하려면 타입 파라미터를 사용하는 다형적(polymorphic) 함수로 만들면 된다.

// Int를 인자로 받고 Int에서 Int로 가는 함수를 반환한다. 따라서 이 함수의 타입은 (Int) -> (Int) -> Int다.

fun main100(args: Array<String>){
    val add: (Int) -> (Int) -> Int = {a -> {b -> a + b}}

    //타입 이름을 짧게 하고 싶으면 타입 별명(type alias)을 사용하라.

    val add2: IntBinOp = { a -> { b -> a + b}}
    val mult: IntBinOp = { a -> { b -> a * b }}
}

typealias IntBinOp = (Int) -> (Int) -> Int

//커리한 함수 적용하기

//커리한 함수의 타입과 구현 방법

// 일반적인 다른 함수와 똑같이 적용 가능

// 커리한 함수에 첫 번째 인자를 적용한 다음.

// 다시 두 번째 인자를 적용용

// 코틀린이 다형적 프로퍼티를 제공하지 않기 때문에 문제가 생긴다.

// 코틀린에서는 다형적 클래스, 인터페이스, 함수를 정의할 수 있다. 하지만 다형적 프로퍼티는 정의할 수 없다. 따라서 프로퍼티가 아니라 함수, 클래스, 인터페이스, fun으로 정의한 함수에만 타입 파라미터를 정의할 수 있다.

// 따라서 다형적 프로퍼티를 정의하려면 요소 안에 정의를 넣어야 한다. 가장 실용적인 요소는 fun이다.

fun <T, U, V> higherCompose(): ((U) -> V) -> ((T) -> U) -> (T) -> V =
        { f ->
            { g ->
                {
                    x -> f(g(x))
                }
            }
        }

// higherCompose()라는 fun 함수는 아무 파라미터도 받지 않고 항상 같은 값을 반환한다. 따라서 이 함수는 상수다. 이 함수를 fun으로 정의했다는 사실은 이 함수가 상수라는 관점과는 무관하다. 이 함수는 함수를 합성하는 함수가 아니다.

// 단지 함수를 합성하는 함수 값을 반환하는 fun 함수일 뿐 이다. 반환 타입을 생략하는 쪽을 선호한다면 파라미터의 타입을 지정해야 한다.

fun <T,U,V> higherCompose2() =
        { f: (U) -> V ->
            { g: (T) -> U ->
                {
                    x: T -> f(g(x))
                }
            }
        }

// 함수를 합성하되 적용 순서가 반대인

// HOF(고차 함수)를 사용하는 경우에도 똑같이 이런 방식을 적용할 수 있다.

/*
fun main101(args: Array<String>){
    val cos = higherCompose<Double, Double, Double>()
    ({ x:Double -> Math.PI / 2 - x})(Math::sin)

    val cosValue = cos(2.0)
}*/

// 타입 추론하기

// 익명 함수를 사용할 때 문제가 될 수 있는 다른 요소로 타입 추론이 있다. 앞 예제에서는 compose함수가 두 익명 함수를 인자로 받는다는 사실을 알고 컴파일러가 두 익명 함수의 타입을 추론할 수 있었다.

/*fun cos(arg: Double)=
        compose({x: Double -> Math.PI / 2 - x},
                {x: Double -> Math.sin(x)})(arg)
        */

//로컬 함수 정의하기

// 클로저 구현하기

// 코틀린 람다에는 자바 람다와 같은 제약이 없다.

// 오직 동작상 파이널(effectively final)인 참조에만 접근할 수 있는 자바 람다와 달리 코틀린 람다는 자신을 둘러싸고 있는 영역에 있는 가변 변수에 자유롭게 접근할 수 있다.

fun main101(args: Array<String>){
    val taxRate = 0.09
    fun addTax(price: Double) = price + price * taxRate
}

fun main102(args: Array<String>){
    val taxRate = 0.09
    fun addTax(taxRate: Double, price: Double) = price + price * taxRate

    println(addTax(taxRate, 12.0))
}

fun main103(args: Array<String>){
    val taxRate = 0.09

    val addTax = {taxRate: Double, price: Double -> price + price * taxRate }

    println(addTax(taxRate, 12.0))
}

// 자바와 달리 코틀린에서는 세 개 이상의 인자를 받는 함수도 만들 수 있다. 자바에서는 인자가 하나인 경우 Function 인터페이스를,

// 커리한 함수는 인자가 하나뿐이며 인자가 하나뿐인 다른 함수를 반환하는 함수다.

fun main104(args: Array<String>){
    val taxRate = 0.09

    val addTax = { taxRate: Double ->
        { price: Double ->
            price + price * taxRate
        }
    }
    println(addTax(taxRate)(12.0))
}

// fun 함수를 커리한 버전은 의미가 거의 없다. addTax에서 첫 번째 함수는 fun을 사용해 정의할 수 있지만. 반환하는 함수는 반드시 함수 값을 사용해야 한다. fun 함수는 값이 아니기 때문에 반환 할 수가 없다.

// 함수 부분 적용과 자동 커링

// 클로저 버전과 커리한 함수 버전은 같은 결과를 내놓기 때문에 동등한 것으로 보인다. 하지만 실제로 이 둘은 의미상 다르다. 앞에서 이야기했지만 addTax 함수의 두 파라미터는 환전히 다른 역할을 한다.

// 세율인 taxRate는 자주 바뀌지 않지만. 가격인 price는 매번 함수를 호출 할 때마다 달라진다.

class TaxComputer(private val rate: Double){
    fun compute(price: Double): Double = price * rate + price
}

fun<A,B,C> parialB(b: B, f: (A) -> (B) -> C): (A) -> (B) -> C =
        {a:A ->
            f(a)
        }

// f(a)는 B에서 C로 가는 함수를 만든다. C 타입이 필요하고, f(a)와 B 타입의 값을 가지고 있다. 따라서 다시 이 함수에 B 타입의 값을 적용해야 한다.

fun<A,B,C> parialB2(b: B, f: (A) -> (B) -> C): (A) -> C ={
    a : A ->
    f(a)(b)
}

fun<T,U,V>swapArgs(f: (T) -> (U) -> V): (U) -> (T) -> (V) ={
    u-> { t -> f(t)(u)}
}

// 이 함수가 있으면 두 인자 중 아무것이나 부분 적용할 수 있다. 예를 들어 이자율과 원금을 받아서 매달 납입해야 하는 금액을 계산하는 함수가 있다고 하면

// 항등 함수 정의하기

// 지금까지 함수를데이터처럼 취급할 수 있음을 살펴봤다. 함수를 다른 함수의 인자로 넘기거나 함수가 함수를 반환하거나, 정수나 문자열에 대해 연산을 적용하는 것처럼 함수에도 연산을 적용 할 수 있다.

// 중립 원소(neutral element)는 덧셈의 0이나 곱셈의 1, 문자열 연결 연산의 빈 문자 열과 같은 역할을 하는 원소를 뜻한다.

// 중립 원소는 주어진 연산에 대해서만 정의된다. 정수 덧셈에서 1은 중립 원소가 아니다.

// 곱셈에서 0은 더더욱 중립 원소가 아니다. 여기서는 함수 합섬의 중립 원소에 대해 이야기하려 한다. 이 특별한 함수는 인자를 그대로 돌려준다.

// 항등(identity)함수라고 한다.

// 더 나아가 정수 곱셈이나 문자열 연결 등의 연산에서는 항등원(identity element)이라는 용어를 중립 원소 대신 사용한다. 코틀린에서는

// 올바른 타입 사용하기

// 앞에서 Int, Double, String과 같은 표준 타입을 사용해 가격이나 세율과 같은 비즈니스 요소를 표현했다. 프로그래밍에서 이런 식의 접근이 흔한 일이지만, 이런 식으로 너무 흔히 쓰는 타입을 사용하면 문제가 생길 수 있다. 앞에서 말한 것처럼 이름보다는 타입을 신뢰해야 한다. 어떤 Double값을 'price'라고 부른다고 해서 그 값이 가격이 되는 것은 아니다.

data class Product(val name: String, val price: Double, val weight: Double)

data class OrderLine(val product: Product, val count: Int){
    fun weight() = product.weight  * count
    fun amount() = product.price * count
}

object Store { // ------ 스토어는 싱글턴 객체다.
    @JvmStatic // ----- @JvmStatic 애너테이션을 사용하면 자바에서 이 함수를 마치 정적 메서드처럼 호출할 수 있다.
    fun main(args: Array<String>){
        val toothPaste = Product("Tooth paste", 1.5, 0.5)
        val toothBrush = Product("Tooth brush", 3.5, 0.3)
        val orderLines = listOf(
                OrderLine(toothPaste, 2),
                OrderLine(toothBrush, 3)
        )
        val weight = orderLines.sumByDouble { it.amount() }
        val price = orderLines.sumByDouble { it.weight() }
        println("Total price: $price")
        println("Total weight: $weight")
    }
}

// 모델링을 배운 독자라면 '클래스 하나에는타입이 같은 프로퍼티가 여럿 들어가서는 안 된다'라는 오래된 규칙을 기억할 것이다.

// 클래스 안에는 특정 타입의 프로퍼티가 하나만 있고 관계 차수(cardinality)만 달라야 한다. 여기서 이 말은 Product 안에는 Double 타입의 프로퍼티가 있고 그 프로퍼티의 관계 차수가 2여야 한다는 뜻이다 이런 식의 접근이 본문에서 다뤘던 문제를 해결하는 올바른 방법은 아니지만 규칙 자체는 기억해둘 만한 가치가 있다.

// 무게 단위의 수량이다 절대로 킬로그램과 원을 서로 더하는 상황이 생겨서는 안 된다.

// 값 타입 정의하기

// 이런 문제를 피하려면 값 타입을 사용해야만 한다.

data class Price(val value: Double){
    operator fun plus(price: Price) = Price(this. value + price.value)

    operator fun times(num: Int) = Price(this.value * num)
}

// OrderLine은 바꿀 필요가 없다.

data class Price2 private constructor (private val value: Double){
    override fun toString() = value.toString()
    operator fun plus(price: Price2) = Price2(this.value + price.value)
    operator fun times(num: Int) = Price(this.value * num)

    companion object{
        val identity = Price2(0.0)
        operator fun invoke(value: Double)=
                if(value > 0)
                    Price2(value)
                else
                    throw IllegalArgumentException("Price must be positive or null")
    }
}

/*fun toString(list: List<Char>, s: String): String =
        if (list.isEmpty())
            s
        else
            toString(list.subList(1, list.size), append(s, list[0]))*/


// list[0]이 리스트의 첫 번째 원소를 반환한다는 점을 기억하라. 이런 연산을 일반적으로 head라는 이름으로 알려져 있다. 그리고 list.subList(1, list.size)는 리스트의 첫 원소를 제외한 나머지 부분을 돌려주는 tail이라는 이름의 함수에 해당한다. 이 두 연산을 추상화해 별도 함수로 만들 수 있다. 하지만 이런 함수를 만들 때는 빈 리스트를 처리해야 한다. 여기서는 if ... else 식에 의해 걸러지기 때문에 빈 리스트인 경우가 발생하지 않는다.

// 재귀 단계를 저장하는 데 필요한 메모리는 제약이 아주 심한 경우가 흔하고, 이 때문에 메모리가 부족하기 쉽다. 이 문제를 피하려면 재귀 대신 공재귀를 사용하는 것이 최선

// 재귀와 공재귀의 차이를

// 스택에 얼마나 많은 계산 단계를 넣을 수 있느냐는 언어나 설정에 따라 달라진다.

// 코틀린에서는 약 20,000단계를 스택에 넣을 수 있고, 자바에서는 3,000단계를 넣을 수 있다. 모든 스레드가 같은 크기의 스택을 사용하므로 (다만, 각 스레드가 사용하는 스택 영역은 서로 다르다) 스택 크기를 더 크게 설정하는 것은 좋은 생각이 아니다. 재귀적이지 않은 처리 과정은 스택을 아주 적게 사용해서 스택 크기를 크게 잡으면 메모리가 낭비되는 경우가 자주 있다.

// 꼬리 호출 제거

// 공재귀도 아주 느리기는 하지만 스택을 고갈시킬 것처럼 보인다. 하지만 이 문제를 완전히 없애는 방법이 있다.

/*fun toString(list2: List<Char>): String{
    fun toString(list2: List<Char>, s: String): String =
            if(list2.isEmpty())
                s
            else
                toString(list2.subList(1, list2.size), append(s, list2[0]))
    return toString(list2, "")
}*/

// 단지 이를 다음과 같이 명령형 루프와 가변 참조로 바꿔 쓰면 된다.

// 꼬리 호출 제거 사용하기

/*
fun toString(list: List<Char>): String{
    fun append(s: String, c: Char): String {

    }

    tailrec fun toString(list: List<Char>, s: String): String =
        if (list.isEmpty())
            s
        else
            toString(list.subList(1, list.size), append(s, list[0]))
    return toString(list, "")

}*/

// 루프를 공재귀로 변환하기

// 루프 대신 공재귀를 사용하는 것은 패러다임의 전환(paradigm shift)이다. 먼저 원래의 패러다임 아래서 생각하고, 그 결과를 새로운 패러다입으로 옮긴다. 모든 배움에는 이런 과정이 필요하며 루프 대신 공재귀를 사용하는 법을 배우는것도 다르지 않다.

fun sum(n: Int): Int = if (n < 1) 0 else n + sum(n - 1) // -> 재귀 버전인 sum(n)이 1부터 n까지의 합계를 뜻한다고 가정하면 다음과 같이 작성 가능

// 명령형 프로그래밍에서 for나 while 루프라고 부르는 제어 구조를 사용해 언어와 관계없이 구현할 수 있다.

// 코틀린에는 for 루프가 없으므로 (적어도 이 흐름도에 해당하는 전통적인 for 루프는 없다)while루프를 사용한다.

fun sum2(n: Int): Int{
    var s = 0
    var i = 0
    while (i <= n){
        s += i
        i += 1
    }
    return s
}

// 첫 번째 파라미터 n은 절대 바뀌지 않는다. 코틀린 로컬 함수를 사용해 도우미 함수를 주 함수에 포함시키면 도우미 함수가 이 파라미터에 대해 닫혀 있게 만들어서 파라미터를 하나 줄일 수 있다.

/*fun sum10(n: Int): Int{
    fun sum10(s: Int, i: Int): Int=
            return sum10(0, n)
}*/

// 아무튼 이런 느낌으로 쓰면 된다.

fun sum11(n: Int): Int{
    fun sum11(s: Int, i: Int): Int = if(i>n) s else sum11(s + i, i + 1)
    return sum11(0, n)
}

//여기서 코틀린에게 도우미 함수에 TCE를 적용한다고 알리는 것뿐이다. tailrec키워드를 함수 앞에 붙이면 된다.

fun sum12(n: Int): Int{
    tailrec fun sum12(s: Int, i: Int): Int =
        if(i>n) s else sum12(s + i, i + 1)
        return sum12(0,n)
}

//TCE를 적용할 수 없으면 코틀린이 다음과 비슷한 경고를 표시한다.

// 공재귀 버전을 바로 작성할 수 있어야 한다. 그렇지 않다면 루프 구현을 작성한 다음에 이를 sum 함수에서 했던 방식을 사용해 공재귀 함수로 바꿔라

// 두 수 x, y를 더하려면 다음과 같이 한다.

// y = 0이면 X를 반환한다.

// 그렇지 않다면 X에 1을 더하고 y에서 1을 뺀 다음, 처음부터 다시 시작한다.

// 루프로 이 과정을 작성하면 다음과 같다.

/*fun add13(a: Int, b: Int): Int{
    var x = a
    var y = b
    while(y != 0){
        x = inc(x)
        y = dec(y)
    }
    return x
}

fun dec(y: Int): Int {

}

fun inc(x: Int): Int {

}*/

// 자바와 달리 코틀린에서는 함수파라미터가 val 변수이기 때문에 파라미터 x, y를 바로 사용할 수가 없다.

// 그러므로 할 수 없이 복사본을 만들어야 한다. 이제 여러분은 파라미터와 복사본 변수를

// 모든 add 함수에 대한 호출로 바꾸기만 하면 된다.

/*
tailrec fun add(x: Int, y: Int): Int = if (y == 0) x else add(inc(x), dec(y))*/

// 꼬리 재귀 함수에 어떤 값을 넣어도 StackOverflowException이 발생하지 않는다. 앞으로 보겠지만 안전한 프로그램을 작성하는 일은 종종 (꼬리 호출이 아닌) 재귀 함수 구현을 꼬리 재귀 구현으로 바꾸는 일을 포함한다.

// 재귀 함수 값 사용하기

// 앞 절에서 본 것처럼 재귀 함수를 정의하기는 쉽다. 때로는 가장 단순한 구현이 꼬리 재귀인 경우도 있다. 하지만 이 예제는 실제 예제가 아니다. 아무도 덧셈을 수행하려고 이런 함수를 만들지는 않는다. 좀 더 유용한 예제를 시도해 보자. 계승 함수인

// 계승 함수인 factoral(n: Int)는 인자가 0이면 1을

// 그렇지 않으면 n * factorial(n-1)을 반환한다.

fun factorial(n: Int): Int = if (n == 0) 1 else n * factorial(n - 1)

// 재귀적 계승 함수 값 함수 값은 val 키워드로 정의된 함수라는 점을 기억

// 함수 참조는 하면 안된다.


// 함수가 자기 자신을 호출할 때 호출이 가능하게 하려면 함수가 이미 정의되어 있어야 한다. 따라서 이는 재귀 함수를 정의하기도 전에 이미 그 함수가 정의되어 있어야 한다는 뜻이다!

// 인자가 하나밖에 없는 fun 함수를 함수 값으로 바꾸는 일은 아주 단순하다. fun 함수와 같은 구현의 람다를 사용하면 된다.

/*fun main105(args: Array<String>){
    val factorial: (Int) -> Int =
            {
                n -> if (n <= 1) n else n * factorial(n - 1)
            }
    val x: Int = X + 1
}

// 이 문제는 변수를 먼저 정의하고 그 값을 나중에 변경하는 식으로 해결할 수 있다. 다음과 같은 초기화를 사용하면 된다.
fun main106(args: Array<String>){
    lateinit var x: Int
    init{
        x = x + 1
    }
}*/

object Factorial {
    private lateinit var fact: (Int) -> Int
    init{
        fact = {n -> if (n <= 1) n else n * fact(n - 1)}
    }
    val factorial = fact
}

// by lazy를 사용하는 방법도 있다.

object Factorial2{
    val factorial: (Int) -> Int by lazy {{
        n: Int ->
        if (n <= 1) n else n * factorial(n - 1)
    }}
}

// 중괄호를 두 번 연속으로 사용해야 한다! 지연 초기화가 다음과 같이 이뤄지기 때문

/*
object Factorial3{
    val factorial3: (Int) -> Int by lazy {n: Int -> if (n <= 1) n else n * factorial3(n - 1)}
} 일단 책에서 하라는데로 한건데 잘 안되는거 보면 내가 잘못했거나 책에서 하는 방법이 잘못되었거나 안드로이드 jvm 업그레이드 진행을 한다던가 여라기지 이이유로 이제 적용이 안되는 경우일 수도 있다.
*/

fun factorial(n: BigInteger): BigInteger =
        if(n == BigInteger.ZERO)
            BigInteger.valueOf(1) // valueOf를 보여주고자 일부러 사용
        else
            n* factorial(n - BigInteger.ONE)


// 재귀 함수와 리스트

// 리스트를 처리할 때 재귀와 꼬리 재귀를 사용하는 경우가 많다. 이런 처리 과정에서 일반저긍로 리스트를 첫 번째 원소와 첫 번째 원소를 제외한 리스트의 나머지 두 부분으로 나눠서 처리한다.

fun sum(list: List<Int>): Int =
        if (list.isEmpty()) 0 else list [0] + sum(list.drop(1))

// 리스트가 비어 있으면 이 함수는 0을 반환한다. 비어 있지 않다면 첫 번째 원소를 제외한 리스트(꼬리)에 sum을 적용한 결과를 첫 번째 원소(머리)와 더한다. 리스트의 머리와 꼬리르 돌려주는 도우미 함수를 정의하면 이 논리를 더 명확히 볼 수 있다.

fun <T> head(list: List<T>): T =
        if (list.isEmpty())
            throw IllegalArgumentException("head called on empty list")
        else
            list [0]
fun <T> tail(list: List<T>): List<T> =
        if (list.isEmpty())
            throw IllegalArgumentException("tail called on empty list")
        else
            list.drop(1)

fun sum7(list: List<Int>): Int =
        if (list.isEmpty())
            0
        else
            head(list) + sum7(tail(list))

fun <T> List<T>.head2(): T =
        if(this.isEmpty())
            throw IllegalArgumentException("head called on empty list")
        else
            this[0]

fun <T> List<T>.tail2(): List<T> =
        if (this.isEmpty())
            throw IllegalArgumentException("tail called on empty list")
        else
            this.drop(1)

fun sum8(list: List<Int>): Int =
        if(list.isEmpty())
            0
        else
            list.head2() + sum(list.tail2())

// 이 코드에서 sum 함수에 대한 재귀 호출은 sum 함수가 처리하는 마지막 연산이 아니다. 이 함수가 마지막에 하는 일은 네 가지다.

// head 함수를 호출한다.

// tail 함수를 호출한다

// tail의 결과를 인자로 sum 함수를 호출한다.

// head와 sum의 결과를 더한다.

// 이 함수는 꼬리 재귀가 아니다. 따라서 tailrec 키워드를 붙일 수 없고, 리스트 인자가 몇천개 이상이면 이 함수를 사용할 수 없다.

// 하지만 이 함수를 고쳐 써서 sum에 대한 재귀 호출을 꼬리 재귀 위치에서 하게 만들 수 있다.

fun sum9(list: List<Int>): Int{
    tailrec fun sumTail(list: List<Int>, acc: Int): Int =
            if (list.isEmpty())
                acc
            else
                sumTail(list.tail2(), acc + list.head2())
    return sumTail(list, 0)
}

//여기서 sumTail 도우미 함수는 꼬리 재귀 함수다. TCE를 통해 최적화가 가능하다. 이 도우미 함수를 외부에서 사용하는 일은 절대 없으므로 sum 함수 내부에 정의했다.

// 도우미 함수와 주 함수를 나란히 정의할 수도 있다. 하지만 그렇게 하면 도우미 함수를 private나 internal로 제한하고 주 함수를 공개해야 한다. 이런 경우 주 함수에서 도우미 함수를 호출하는 부분이 클로저가 된다. 도우미 함수를 주 함수에 내포시키는 쪽을 더 선호하는 이유는 이름 충돌을 피하고 주 함수의 파라미터 중 일부를 클로저를 사용해 공유하기 때문


// 이중 재귀 함수

