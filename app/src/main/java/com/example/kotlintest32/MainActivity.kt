package com.example.kotlintest32

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
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
