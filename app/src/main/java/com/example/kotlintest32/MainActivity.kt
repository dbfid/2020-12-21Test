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
}