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

        fun print() {
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
    class Rect: Shape() {
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
}