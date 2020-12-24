package com.example.kotlintest32

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    open class Shape{
        var x: Int = 0
        set(value){
            if(value < 0) field = 0
            else field = value
        }

        var y: Int = 0
        set(value){
            if(value < 0) field = 0
            else field = value
        }
        lateinit var name: String

        open fun print() {
            println("$name : location : $x, $y")
        }
    }

    //Shape 클래스에 x, y, name이라는 프로퍼티와 print()라는 함수를 정의했습니다. 클래스 내부의 코드는 이미 살펴보았던 프로퍼티와 함수 선언이다.
    //open 예약어는 상속을 허용한다는 의미
    //코틀린에서 클래스의 상속 허용 여부를 결정하는 기본값은 final 개발자가 어떤 클래스를 선언할 때 상속 허용 여부를 명시하지 않으면 기본으로 final로 적용

    // final은 다른 클래스가 상속받는 것을 허용하지 않겟다는 의미
    // 결국, 어떤 클래스를 선언할때 이 클래스를 상속받아 하위 클래스를 작성하게 허용하려면 open 예약어를 추가해 주어야 한다.
    // 이제 Shape 클래스를 상속받아 Rect, Circle 클래스를 정의한다.

    //소스_하위 클래스
    open class Rect: Shape() {
        var width: Int = 0
            set(value){
                if(value < 0) field = 0
                else field = value
            }
        var height: Int = 0
            set(value){
                if(value < 0) field = 0
                else field = value
            }
    }
    class Circle: Shape(){
        var r: Int = 0
            set(value){
                if(value < 0) field = 0
                else field = value
            }
    }
    // 코틀린에서 상속 관계 표현은 콜론( : )을 이용함. 클래스 선언 부분에 상위 클래스를 콜론 오른쪽에 명시해 상속 관계를 표현

    //소스_ 객체 생성 및 이용
    fun main42(args: Array<String>){
        val rect = Rect()
        rect.name = "Rect"
        rect.x = 10
        rect.y = 10
        rect.width = 20
        rect.height = 20
        rect.print()

        val circle = Circle()
        circle.name = "Circle"
        circle.x = 30
        circle.y = 30
        circle.r = 5
        circle.print()
    }
    //즉, 사용하려는 프로퍼티나 함수가 자신의 클래스에 정의되지 않았더라도 상속받은 상위 클래스에 정의됐다면 자신의 클래스에 정의된 것처럼 사용할 수 있다.

    //오버라이드
    //오버라이드(Override)는 상위 클래스에 정의된 프로퍼티나 함수를 하위 클래스에서 재정의하는 것을 말함

    // 함수 오버라이드(override)
    // 상위 클래스에 정의된 함수는 하위 클래스에서 자신의 함수처럼 이용할 수 있다. 그런데 때로는 상위 클래스에 정의된 함수를 그대로 이용하지 않고, 다시 정의해야 할 필요가 있다. 앞에서 설명한 Shape라는 상위 클래스와 이를 상속받은 Rect, Circle이라는 하위 클래스가 있는 상황을 예로 들어보자.


    open class Shape2{
        var name = Rect()
        var x: Int = 0
        var y: Int = 0
        open fun print(){
            println("$name : location : $x, $y")
        }
    }
    // final  클래스: 이 클래스를 상속받아 하위 클래스 작성 금지
    // final 함수: 이 함수를 하위 클래스에서 재정의 금지
    // final 프로퍼티: 프로퍼티 재정의 금지

    //소스_하위 클래스
    class Rect2: Shape(){
        var width: Int = 0
        var height: Int = 0
        override fun print(){
            println("$name : location : $x, $y ... width : $width .... height : $height")
        }
    }

    class Circle2: Shape(){
        var c: Int = 0
        override fun print(){
            println("$name : location : $x, $y ... radius : $c")
        }
    }

    //하위 클래스에서 상위 클래스의 함수를 재정의할 때는 함수 앞에 꼭 override 예약어를 추가해야 함 이로써 이 함수는 상위 클래스에 정의된 함수를 재정의하는 것임을 명시해야 함

    // 하위 클래스에서 상위 클래스의 함수를 재정의하면 하위 클래스의 객체에서는 재정의한 함수를 이용한다.

    //소스_객체 생성 이용
    fun main2(args: Array<String>){
        val rect = Rect()
        rect.name = "Rect"
        rect.x = 10
        rect.y = 10
        rect.width = 20
        rect.height = 20
        rect.print()
    }

    // override 예약어 하위 클래스에서 상속받은 상위 클래스의 함수를 재정의할 때 사용합니다. 그런데 override 예약어로 정의한 함수는 자동으로 open 상태가 된다. 즉, override를 추가한 함수는 open을 추가히자 않아도 다시 하위 클래스에서 재정의할 수 있다는 의미
    // Shape 클래스를 상속받아 Rect 클래스를 작성하고, 다시 Rect를 상속받아 RoundRect 클래스를 작성한다는 가정. 이때 Shape 클래스의 print() 함수를 Rect 클래스에서 재정의하고, 다시 Rect 클래스의 print() 함수를 RoundRect 클래스에서 재정의한다는 가정.

    //소스_override

    open class Shape3{
        open fun print(){

        }
    }

    open class Rect3: Shape(){
        override fun print(){

        }
    }

    class RoundRect: Rect(){
        override fun print(){

        }
    }

    // override 예약어를 추가해 상위 클래스의 함수를 재정의했습니다.
    // Rect 클래스를 상속받아 RoundRect 클래스를 작성
    // 이 클래스에서 Rect 클래스의 print() 함수를 재정의
    // 일반적으로 함수 오버라이드를 허용하려면 함수 앞에 open 예약어로 명시해야 한다.
    // 그런데 위 경우처럼 override 예약어가 하위 클래스에서의 재정의를 암묵적으로 허용하기 때문

    // 그렇다면 하위 클래스에서 재정의하지 못하도록 막는 방법도 있을까요? 즉, 자신은 상위 클래스의 함수를 재정의했지만, 하위 클래스에서는 자신을 재정의하지 못하도록 막는 방법,
    // 앞에서 다룬 final 예약어를 이용하면 가능

    //소스 _ final
    open class Rect6: Shape(){
        final override fun print(){

        }
    }
    // 프로퍼티 오버라이드
    // 프로퍼티의 오버라이드도 함수의 오버라이드와 같은 개념 상위 클래스에 선언된 프로퍼티는 하위 클래스에서 자기 것처럼 이용할 수 있다. 그런데 만약 하위 클래스에서 상위 클래스의 프로퍼티를 재정의하고 싶다면 같은 이름으로 다시 선언해서 사용해야함
    //소스_ 프로퍼티 오버라이드

    open class Super{ // Super라는 클래스
        open val name: String = "kkang"// Super 클래스 안에 open 형식의 프로퍼티를 선언
    }
    open class Sub: Super(){ // 이 클래스를 상속받아 Sub라는 클래스를 정의
        open override var name: String = "kim" // Sub 클래스에서 같은 이름으로 프로퍼티를 다시 정의
    }
    //함수의 오버라이드와 마찬가지로 프로퍼티의 오버라이드도 먼저 상위 클래스의 프로퍼티의 open 예약어로 명시해 재정의를 허용해야 합니다. 그리고 하위 클래스에서는 override 예약어로 명시해 재정의를 허용해야 합니다.
    // 그리고 하위 클래스에서는 override 예약어로 이 프로퍼티는 상위 클래스의 프로퍼티를 재정의한 것이라고 명시해야 함

    //상위 클래스의 프로퍼티와 이름, 타입이 모두 같아야 함

    // 상위 클래스에 val로 선언된 프로퍼티는 하위에서 val, var로 재정의 가능

    //상위에서 var로 선언된 프로퍼티는 하위에서 var로 재정의 가능(val로는 불가)

    // 상위에서 null 허용으로 선언된 경우 하위에서 null 불허로 재정의 가능

    // 상위에서 null 불허로 선언된 경우 하위에서 null 허용으로 재정의 불가

    //소스 프로퍼티 재정의
    open class Super2(){
        open val name: String = "kkang"

        open var age: Int = 10

        open val email: String? = null

        open val address: String = "seoul"
    }

    // val 이면 하위 클래스에서 var나 val로 재정의할 수 있다.
    // 상위 클래스에서 var로 선언된 프로퍼티를 하위 클래스에서 val로 재정의했으므로 컴파일 에러가 발생
    // var로 선언된 프로퍼티는 하위 클래스에서 var로 재정의해야 한다.

    // ? 기호를 이용해 null 허용으로 선언된 상위 클래스의 프로퍼티를 하위 클래스에서 null 불허로 재정의했다.
    // 상위 클래스에서 null 불허로 선언된 프로퍼티를 하위 클래스에서 null 허용으로 재정의했으므로 컴파일 에러가 발생

    // 함수의 오버라이드와 마찬가지로 프로퍼티도 override 예약어는 open을 내장하는 개념
    // 즉, override가 명시된 프로퍼티는 open을 추가하지 않아도 하위에서 재정의할 수 있도록 허용

    //소스_override

    open class Super4{
        open val name: String = "kkang"
    }
    open class Sub2: Super() {
        override var name: String = "kim"
    }
    class Sub3: Sub(){
        override var name: String = "lee"
    }

    //Super 클래스의 name 프로퍼티를 Sub 클래스에서 재정의하려고 05번 줄처럼 override 예약어를 명시했다.

    //만약 상위 클래스의 프로퍼티를 재정의하면서 하위에서는 재정의하지 못하도록 막고 싶으면 final을 명시

    //소스_프로퍼티 오버라이드 금지
    /*open class Sub4: Super(){
        final override var name: String = "kim"
    }
    class Sub5: Sub4(){
        override var name: String ="lee" <--- 여기는 에러가 난다
    }
    */
    // 상위 클래스 멤버 접근
    // 함수의 프로퍼티 오버라이드까지 정의
    // 오버라이드는 상위 클래스에 선언된 멤버를 하위 클래스에서 재정의해서 이용하는 기법

    // 이렇게 하면 하위 클래스에서는 재정의한 멤버를 이용함
    // 그런데 재정의한 멤버가 있어도 때로는 상위 클래스에 정의한 멤버도 함께 이용해야 할 때가 있다.

    // 이럴 때 super를 사용한다.

    //소스_super에 의한 상위 클래스 멤버 접근
}
open class Super {
    open var x: Int = 10
    open fun someFun(){
        println("Suer...someFun()")
    }
}

class Sub : Super(){
    override var x: Int = 20
    override fun someFun(){
        super.someFun()
        println("Sub8...${super.x}.... $x")
    }
}
fun main(args: Array<String>){
    var sub = Sub()
    sub.someFun()
}

// 상위 멤버를 하위에 재정의해서 사용하면 되는 것이지, 다시 상위 멤버를 super로 이용할 필요가 있는가?

//일반적으로 상위 클래스의 원본 함수에서는 여러 클래스에서 공통으로 처리할 로직을 작성하고, 하위 클래스의 오버라이드 함수에서는 해당 클래스만의 특정직언 로직을 작성. 그런데 하위 클래스에서 사우이 클래스의 공통 로직을 사용해야 하는 사례는 많다.

// 앞에서 예로든 Shape와 React. Circle 클래스에서 Shape 클래스의 print() 함수에서는 공통적인 속성을 출력

//상속과 생성자

// 상위 클래스 생성자 호출

//생성자는 클래스의 중요한 구성요소

//모든 클래스에는 생성자가 최소 하나 이상 선언되어 있어야 하고고

//소스 _ 클래스 상속 정의

open class Super9{

}

class Sub10: Super(){

}

val sub = Sub()

// 클래스를 선언할 때 개발자가 생성자를 추가하지 않으면 컴파일러가 매개변수 없는 주 생성자를 자동으로 추가합니다. 위의 그림에서 개발자가 왼쪽처럼 작성했더라고 컴파일러에 의한 생성자 자동 생성으로 오른쪽 코드처럼 작성한 것과 같습니다. 객체를 생성할 때 Sub 클래스의 생성자가 호출되며 Sub 클래스의 생성자에서 다시 상위 클래스의 생성자를 호출하는 구조입니다.

// 상위 클래스에 명시적으로 생성자가 선언된 경우

//소스 _생성자 연결 에러

open class Super10(name: String){

}

class Sub11: Super(){ // --- 에러 책에는 에러라고 뜨는데 여기선 딱히 뜨지 않는다.

}

//위의 소스를 보면 Super 클래스에 문자열 하나를 매개변수로 받는 주 생성자를 정의했습니다. 그리고 04번 줄에서 Super를 상속받아 Sub 클래스를 정의했다. 그런데 위의 소스처럼 작성하면 04번 줄에서 컴파일 에러 발생

// 소스_상위 클래스 생성자 연결

//어떠한 형태로든 하위 클래스 생성자에는 상위 클래스의 생성자를 매개변수 정보에 맞게 호출하는 구문이 있어야 한다.
//상위 클래스의 생성자를 매개변수 정보에 맞게 호출하는 구문이 있어야 한다. 이 원칙을 두 가지 경우로 정리해 볼 수 있을 것 같다.

//하나는  하위 클래스에 주생성자가 선언된 경우이고. 다른 하나는 주 생성자 없이 보조 생성자만 선언된 경우

//하위 클래스에 보조 생성자만 선언된 경우

// 상하위 생성자의 수행 흐름

//클래스를 상속 관계로 정의할 때 생성자가 연결되어
// 상위 클래스의 생성자가 호출되는데, 이때 생성자의 수행 순서를 고려해야 합니다. 또한 상하위 클래스에 init 블록이 작성되어 있다면 init 블록의 수행 순서도 고려해야 합니다. 상속 관계에 있는 클래스의 생성자들이 어떤 순서로 실행되는지를 정리해보자

open class Super11{
    constructor(name: String, no:Int){
        println("Super ... constructor(name, no)")
    }
    init{
        println("Super ... init call....")
    }
}
class Sub13(name: String): Super11(name, 10){
    constructor(name: String, no: Int): this(name){
        println("sub ... constructor(name, no) call")
    }
    init{
        println("sub ... init call....")
    }
}

fun main3(args: Array<String>){

    Sub13("kkang")
    println("..........")
    Sub13("kkang", 10)
}

// 생성자의 흐름을 쉽게 파악하려면 생성자가 하는 역할을 수행 흐름 관점에서 살펴보면 된다.

// 생성자가 호출되면 다음 순서로 실행된다.

// this() 혹은 super()에 의한 다른 생성자 호출
// init 블록 호출
// 생성자의()영역 실행
//객체가 생성되면 Sub의 A생성자가 호출된다.

// C 생성자의 2번. Syper의 init 블록을 실행한다.

// 상속과 캐스팅

// 캐스팅이란 형 변환을 의미. Int 타입의 객체가 Double 타입으로. 또는 Double 타입의 객체가 Int 타입으로 변환되는 것을 의미 기초 데이터 타입의 캐스팅은 이전에 살펴보았듯이

//자동 형 변환이 안 되고 함수를 이용해야 함

//소스_기초 타입 변환

val data1: Int = 10
val data2: Double = data1.toDouble()

// 그런데 상속 관계에서도 캐스팅이 필요하다. 하위 객체가 상위 타입으로 표현해야 하거나 반대로 상위 타입의 객체가 하위 타입으로 표현해야 하는 경우가 발생

// 스마트 캐스팅

// 스마트 캐스팅(Smart Casting)개발자가 코드에서 명시적으로 캐스팅을 선언하지 않아도 자동으로 캐스팅되는 것을 의미

//is 예약어 이용

//코틀린에서는 is라는 연산자를 제공하는데 이 연산자로 타입을 확인할 수 있다.

//소스_ 기초 타입의 스마트 캐스팅

fun smartCast(data: Any): Int{
    if(data is Int) return data * data
    else return 0
}
fun main5(args: Array<String>){
    println("result : ${smartCast(10)}")
    println("result : ${smartCast(10.0)}")
}

//data is Int 구문에 의해 매개변수 data의 타입이 Int 타입의 데이터면 자동으로 형 변환이 발생. 결국, 개발자가 함수 등을 이용해 형 변환을 하지 않아도 자동으로 형 변환이 되는 스마트 캐스팅이 발생한 예

// 그런데 is 연산자에 의한 스마트 캐스팅은 기초 데이터 타입에만 적용 X

//소스_객체의 is에 의한 스마트 캐스팅

class MyClass1{
    fun fun1(){
        println("fun1()...")
    }
}
class MyClass2{
    fun fun2(){
        println("fun2()...")
    }
}
fun smartCast2(obj: Any){
    if(obj is MyClass1) obj.fun1()
    else if(obj is MyClass2) obj.fun2()
}

fun main6(args: Array<String>){
    smartCast2(MyClass1())
    smartCast2(MyClass2())
}

//소스 _ 외부 파일에서의 접근

// 같은 패키지에 있는 다른 파일에서 접근하려는 테스트

//소스_프로퍼티 getter/setter
private var data: Int = 10
    get() = field
    set(value){
        field = value
    }

//프로퍼티의 get() 함수는 값을 가져오고 set() 함수는 값을 변경하는 목적으로 사용합니다.

// 이러한 특성을 이용해 목적에 따라 접근 제한을 다르게 설정 가능

// 다음 소스는 앞서 보았던 PropertyVisibilityTest 클래스에서 set()/get() 함수의 접근 제한자를 변형한 것

//소스 _ set()/get() 을 이용한 접근제한

class PropertyVisibilityTest2{ //여기에 data라는 이름의 프로퍼티를 선언
    // 프로퍼티에 별도의 접근 제한자를 명시하지 않고
    var data: Int = 10
        private set(value){
            field = value
        }

    fun main(args: Array<String>){
        val obj2 = PropertyVisibilityTest2()
        println("${obj2.data}")
        obj2.data =20 // <---에러가 난다.
    }
}

// 프로퍼티의 get(), set()을 직접 작성해 접근 제한자를 다르게 설정할 때 다음의 규칙에 따라야 한다.

// get() 함수의 접근 제한자는 프로퍼티의 접근 제한자의 항상 같다.
// get() 함수의 접근 제한자는 프로퍼티의 접근 제한자의 다르게 설정할 수 있지만 접근 범위를 넓혀서 설정할 수는 없다.

// 만약 프로퍼티가 protected val data: Int = 10 으로 선언했다면 get() 함수의 접근 제한자를 protected가 아닌 public, private 등으로 설정할 수 없다. 반면에 set() 함수는 프로퍼티의 접근 제한자가 private일 때 public으로 설정할 수 없습니다.
// public 프로퍼티 -> set(): public, prtected, internal, private
// protected 프로퍼티 -> set(): rpotected, private
// internal 프로퍼티 -> set(); Internal, private
// private 프로퍼티 -> set(): private

// 생성자와 접근 제한자

// 코틀린의 생성자는 주 생성자와 보조 생성자로 구분되며 주 생성자와 보조 생성자 모두 접근 제한자를 지정할 수 있다.

// 상속 관계와 접근 제한자

// 클래스를 정의할 때 상속 관계에 의한 접근 제한자 지정에 두 가지 규칙이 있다.

// open과 private는 함께 사용할 수 없다.
// 하위 클래스에서 상위 멤버를 오버라이트할 때 접근 범위를 줄일 수 없다.

// 우선 첫 번째 규칙을 보면 open은 하위 클래스에서 오버라이드를 허용하는 예약어다.

// 그런데 private로 선언한 함수와 프로퍼티는 해당 클래스 내에서만 접근 가능

// 오버라이드는 허용하는데 접근할 수 없다는 건 이치에 맞지 않다. 따라서 open으로 선언한 함수와 프로퍼티에 private접근 제한자를 지정할 수 없습니다.

// 코틀린에서 상속은 자바와 다른 부분이 있다.

// 코틀린의 기본 상위 클래스는 Object가 아니라 Any이다.

// 상속 관계 선언 시 extends 예약어를 사용하지 않고 콜론(:)을 이용해 타입 표현으로 상속 관계를 표현한다.

class Product(val name: String, val price:Int)

data class User(val name: String, val age: Int)

fun main2(args: Array<String>){
    var product = Product("prod1",100)
    var product1 = Product("prod1", 100)
    println(product.equals(product1))

    var user = User("kkang", 30)
    var user1 = User("kkang", 30)
    println(user.equals(user1))
}

//추상 클래스도 클래스 자체로 객체 생성이 불가능하다. 서브 클래스로 만들어 사용해야 한다. 그리고 sealed 예약어는 내부적으로 abstract를 내장하고 있으므로 추상 클래스와 차이가 없어 보인다.

// Nested 클래스
// 어떤 클래스 내부에 정의한 클래스를 프로그래밍 언어에서 흔히 inner 클래스라 부르지만, 코틀린에서는 inner라는 예약어가 있으므로 예약어와 용어 차이를 위해 여기서는 Nested 클래스로 통칭하겠습니다(공식 메뉴얼에서도 Nested 클래스로 소개합니다.)

class Outer {
    private var no: Int = 10
    fun outerFun(){
        println("outerFun()")
    }
    inner class Nested{
        val name: String = "kkang"
        fun myFun(){
            println("Nested..myFun...")
            no = 20
            outerFun()
        }
    }
}

/*
fun main10(args: Array<String>){
    val obj: Outer.Nested = Outer.Nested() // <---- 여기서 에러가 난다.
    println("${obj.name}")
    obj.myFun()
}*/

//소스_inner 클래스의 객체 생성

class Outer2 {
    private var no: Int = 10
    fun outerFun() {
        println("outerFun()...")
    }
    inner class Nested{
        val name: String = "kkang"
        fun myFun(){
            println("Nested.. myFun...")
            no = 20
            outerFun()
        }
    }
    fun createNested(): Nested{
        return Nested()
    }
}

fun main11(args: Array<String>){
    val obj1: Outer2.Nested = Outer2().Nested()
    val obj2: Outer2.Nested = Outer2().createNested()
}

// Object 클래스

// 익명 클래스는 이름 없는 클래스라는 의미
// 클래스를 선언하기는 하는데 클래스명이 없이 선언하겠다는 의도.
// 이름이 없는 클래스를 왜 굳이 정의하는 것일까? 하지만 실제로 개발을 하다 보면 많이 사용됨

// 어떤 클래스를 선언하기는 해야 하는데 이 클래스의 객체를 여러 개 생성 하지 않고. 딱 한 번만 생성한다면 굳이 정성 이름으로 클래스를 선언하는 게 귀찮거나 프로그램 코드를 복잡하게 만들 수 있다.
// 이럴 때 이름 없는 클래스로 선언하고 바로 생성해서 이용한다.

// 코틀린에서는 익명 클래스를 특정 클래스의 내부 클래스로 정의할 수도 있고 최상위에 정의할 수도 있다.

// object를 이용한 익명 클래스를 정의할 때 사용합니다. 클래스 선언 때 class 예약어를 작성하지 않고 object {} 형태로 선언합니다. 이렇게 선언한 클래스는 클래스명이 없지만. 선언과 동시에 객체가 생성 됩니다.

// 반복해서 객체 생성은 불가능하며, 생성과 동시에 생성된 객체를 이용한다.

//소스_object로 클래스를 작성

val obj1 = object{
    var no1: Int = 10
    fun myFun(){

    }
}

class Outer4 {
    val obj2 = object{
        var no2: Int = 0
        fun myFun(){

        }
    }
}

//소스_object 이용 시 멤버 접근 문제

//소스 _ object 멤버 이용

class Outer5 {

    private var no: Int = 0

    private val myInner = object {
        val name: String = "kkang"
        fun innerFun(){
            println("innerFun...")
            no++
        }
    }

    fun outerFun(){
        myInner.name
        myInner.innerFun()
    }
}

/*
fun main12(args: Array<String>){
    val obj = Outer()
    obj.myInner.name
}*/

//object 선언

//지금까지 사용한 object는 이름 없는 클래스를 정의하고 바로 생성하는 방법이었지만. object 예약어를 이용해 이름이 있는 클래스도 선언할 수 있었다.

//형식
// val obj = object{ }

//위의 사용예에서는 object 뒤에 클래스 영역인 {}만 추가됐지만, object와 {} 사이에 클래스명을 명시해 선언할 수도 있습니다.

//형식
// object 클래스명 {}

// 위와 같이 선언하면 클래스 선언과 동시에 클래스명과 같은 이름의 객체까지 생성됩니다. 결국, "object 클래스명 {}"는 객체 생성 구문 입니다.

//소스_object로 이름 있는 클래스 선언

class NormalClass{
    fun myFun(){ }
}

object ObjectClass{
    fun myFun() {}
}

fun main13(args: Array<String>){
    val obj1: NormalClass = NormalClass()
    val obj2: NormalClass = NormalClass()
    obj1.myFun()

    /*val obj3: ObjectClass = ObjectClass() */// < --- 에러


    ObjectClass.myFun()
}

//소스 _ 코틀린의 싱글톤

object Text { }

// companion 에약어

// 이름이 있는 object 클래스를 최상위에 작성하지 않고 특정 클래스 안에 작성할 수도 있습니다.

// 소스 _ Nested 클래스로서 object 클래스

class Outer6{
    object NestedClass{
        val no: Int = 0
        fun myFun(){ }
    }
}
fun main20(args: Array<String>){
    val obj = Outer()

}
