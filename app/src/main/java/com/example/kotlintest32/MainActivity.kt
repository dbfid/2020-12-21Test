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
        final override var name: String = "kim" // Sub 클래스에서 같은 이름으로 프로퍼티를 다시 정의
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

    //
}