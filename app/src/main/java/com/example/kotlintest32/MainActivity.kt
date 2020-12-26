package com.example.kotlintest32

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ClassCastException


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

//count(), find()

//소스 _ all(), any() 테스트
fun main16(args: Array<String>){
    class User(val name: String, val age: Int)
    val list = listOf(User("kkang", 33),User("lee",28))

    println("count test : ${ list.count {it.age > 25}}")
    val user = list.find {it.age > 25}
    println("find test : ${user?.name} ${user?.age}")
}

fun main17(args: Array<String>){
    //fole() 함수는 초깃값을 지정할 수 있고
    //reduce() 함수는 초깃값을 지정할 수 없다.

    var result = listOf(1, 2).fold(10, { total, next ->
        println("${total} ... ${next}")
        total + next
    })
    println("fold test : $result")
}

fun main18(args: Array<String>){
    val list2 = listOf<Int>(12,8,9,20)
    val resultList2 = list2.filter {it > 10}
    for(i in resultList2){
        println(i)
    }
}

//소스 _ filterNot()
fun main11(args: Array<String>){
    listOf(21,1,12,5,23).filterNot {it > 10}.forEach{println(it)}
}

//소스 _ drop
fun main13(args: Array<String>){
    listOf(1,2,3,4).drop(2).forEach{println(it)}
}

//소스 _ elementAt
fun main14(args: Array<String>){
    val result=listOf(2,5,10,8).elementAt(2)
    println("elementAt test : $result")
}
//만약 인데스값이 데이터 범위를 벗어나도 IndexOutOfBoundsException이 발생하지 않는다.
// 대신, 지정된 람다 함수가 실행되고 그 람다 함수에서 변환 값을 변환합니다.

//소스 _ elementAtOrElse()
fun main15(args: Array<String>){
    var result1 = 0
    result1 = listOf(2,5,10,8).elementAtOrElse(5, { 0 })
    println("elementAtOrElse test : $result1")
}

//elementAtOrElse(5, { 0 })으로 지정했습니다. 인덱스 5의 데이터를 추출하는 구문이다. 인덱스 5는 데이터 범위를 벗어난 값입니다. 이럴 때는 { 0 } 부분이 실행됩니다. 위에서는 데이터 범위를 벗어난 인덱스캆을 전달하면 단순히 0을 반환하도록 작성했다.

fun main19(args: Array<String>){
    var result1 = 0
    result1=listOf(2,5,10,8).first { it % 5 == 0 }
    println("first test : $result1")
}

//소스 _ lasat()
fun main20(args: Array<String>){
    var result1 = listOf(2, 5, 10, 8).last {it % 5 == 0}
    println("last test : $result1")
}

//last() 함수를 이용할 때 만약 조건에 맞는 데이터가 하나도 없으면 NoSuchElementException이 발생한다. 그리고 lastOrNull() 함수는 first() 함수와 동일한데, 만약 조건에 맞는 데이터가 없으면 null을 반환합니다.

fun main21(args: Array<String>){
    var result1 = 0
    result1 = listOf(2,5,10,2).indexOf(2)
    println("indexOf test : $result1")
}

//소스 _ indexOfFirst()
fun main22(args: Array<String>){
    var result1 = 0
    result1 = listOf(2,5,10,2).indexOfFirst {it % 2 == 0}
    println("indexOfFirst test : $result1")
}

// indexOfFirst { it % 2 == 0 }로 작성했으므로 람다 함수 조건은 2로 나누었을 때 나머지가 0인 데이터입니다. 그 데이터가 위차하는 첫 번째 인덱스값을 반환합니다.

// 소스_indexOfLast()
fun main23(args: Array<String>){
    var result1 = 0
    result1 = listOf(2, 5, 10, 2).indexOfLast{it % 2 == 0}
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

fun main24(args: Array<String>){
    var data1: String = "kkang"
    var data2: String? = null

    fun main(args: Array<String>){
        /*data1 = null*/ // <--- 이러면 에러다
    }
}

//프로퍼티를 선언할 때 타입 뒤에 물음표(?)를 추가하면 Null을 대입할 수 있고, 물음표를 추가하지 않으면 Null을 대입할 수 없습니다. Null 불허로 선언한 프로퍼티에 null을 대입했으므로 컴파일 에러가 발생합니다.

// 또한, Null 불허로 선언한 프로퍼티를 Null 허용으로 선언한 프로퍼티에 댕비하는 것은 상관없지만, Null 허용으로 선언한 프로퍼티를 Null 불허 프로퍼티에 대입하면 컴파일 에러가 발생합니다. 어떠한 형태로든 Null 불허 프로퍼티에 Null 불허 프로퍼티에 Null을 대입할 수는 없다.

// Null 확인 연산자

fun main25(args: Array<String>){

    var data1 :String? = "kkang"

    val length1: Int? = if(data1 != null){
        data1.length
    }else {
        null
    }
}

//소스 _ ?.을 이용한 Null 체크

fun main26(args: Array<String>){
    var data1: String? = "kkang" //data1은 Null 허용으로 선언한 프로퍼티다.

    var length2: Int? = data1?.length // 이 프로퍼티의 length에 접근할 때 Null 확인을 해주어야 한다. if-else 문으로 하지 않고 ?.연산자로도 가능하다
    println(length2)

    data1 = null
    length2 = data1?.length
    println(length2)
}

// ?.연산자는 Null을 안전하게 사용하기 위해 제공하는 연산자로 프로퍼티 값이 Null이 아니면 뒤의 length가 실행되고 Null이면 null을 반환합니다. 결국, data1.length 구문은 data1이 Null이 아닐 때만 길이를 반환하고 만약 Null이면 null을 반환한다.

// ?. 연산자로 Null을 확인하는 방법은 객체의 연결 구조에서도 사용할 수 있습니다.

//소스 _ ?.을 이용한 Null 체크 - 객체의 연결 구조

class Address {
    val city: String?="seoul"
}

class User{
    val address: Address? = Address()
}

fun main27(args: Array<String>){
    val user: User? = User()

    println(user?.address?.city)
}

fun main28(args: Array<String>){
    val array = arrayOf("hello", null, "kkang")

    array.forEach {
        if(it != null){
            println("$it .. ${it.length}")
        }
    }

    array.forEach {
        it?.let{
            println("$it .. ${it.length}")
        }
    }
}

// 엘비스 연산자

// ?: 연산자를 이용해 Null을 처리할 수도 있습니다. 흔히 ?:을 엘비스 연산자(Elvis Operator)라고 부르며 Null 허용 데이터를 처리할 때 Null 처리를 명시할 수 있습니다. ?. 연산자는 Null이면 null

// 때로는 Null일 때 대입해야 하는 값이 있거나 실행해야 하는 구문이 있습니다. 이럴 때 ?: 연산자를 이용합니다. ?: 연산자를 독릭접으로 사용할 수도 있고 ?. 연산자와 함께 사용할 수도 있습니다.

// 소스 _ ?: 연산자

fun main29(args: Array<String>){
    var data: String? = "kkang"

    var length: Int = if(data != null){
        data.length
    }else{
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

fun main30(args: Array<String>){
    val strData : String = "kkang"

    val intData: Int? = strData as? Int

    println(intData)
}


//try - catch - finally 구문으로 예외 처리

//소스 _ try-catch-finally 구조

fun main31(args: Array<String>){
    try{
        println("try top...")

        val data: String = "10"
    }catch (e: Exception){
        println("catch.....")
    }finally{
        println("finally....")
    }
}

//위의 소스는 예외가 발생하지 않았을 때의 수행 흐름을 보여줍니다. try {} 영역이 있고 이어서 catch(){}와 finallly {} 영역이 있습니다. try 영역은 생략할 수 없으며 정상적으로 수행해야 할 구문을 작성합니다. try 영역에 작성한 구문이 실행될 때 예외가 발생하는지에 따라 실행

//흐름이 달라집니다.

// 소스 _ 예외 발생 시의 수행 흐름

fun main32(args: Array<String>){
    try{
        println("try top...")

        val data: String = "kkang"

        val intData: Int? = data.toInt()

        println("try bottom...")
    }catch (e: Exception){
        println("catch....${e.toString()}")
    }finally{
        println("finally....")
    }
}

///try 영역이 실행되다가 예외가 발생하면 try의 나머지 부분이 실행되지 않습니다. 그래서 "try bottom"
// 이라는 문자열은 출력되지 않았습니다. try 영역에서 예외가 발생하는 순간 catch 영역이 실행되고, 마지막으로 finally 영역이 실행됩니다. catch 영역에서 예외 메시지를 출력하기 위해 e.toString()을 사용했습니다. 이처럼 catch 문를 여러 개 작성한다는 것은 try 영역에서 발생하는 예외 타입이 여러 가지일 때입니다.

//소스 _ catch 문 여러 개 작성

fun some(array: Array<out Any>){
    try{
        println("try top...")

        val intData: Int = array[0] as Int

        val data: String = array[0] as String

        val data2: Int = data.toInt()

    }catch(e: ClassCastException){
        println("catch... ClassCastException")
    }catch (e: ArrayIndexOutOfBoundsException){
        println("catch... ArrayIndexOutOfBoundsException")
    }catch (e: Exception){
        println("catch... Exception... ${e.toString()}")
    }
}

fun main33(args: Array<String>){
    // 캐스팅 예외
    val array = arrayOf("0", 1, "6")
    some(array);

    //배열 데이터 접근 예외
    val array2 = arrayOf(10, "5")
    some(array2)

    //수자 타입 예외
    val array3 = arrayOf(10,0,"world")
    some(array3)
}

//try 영역이 실행되다가 예외가 발생하면 try의 나머지 부분이 실행되지 않습니다. 그래서 "try bottom"이라는 문자열은 출력되지 않았습니다. try 영역에서 예외가 발생하는 순간 catch 영역이 실행되고, 마지막으로 finally영역이 실행됩니다.

//소스 _ try - catch - finally 문의 다양한 사용 예

//