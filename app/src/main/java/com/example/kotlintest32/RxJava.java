package com.example.kotlintest32;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class RxJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
    }
    // 리액티브 프로그래밍은 데이터가 통지될 때마다 관련 프로그램이 반응(reaction)해 데이터를 처리하는 프로그래밍 방식
}
//소스_자바의 싱글톤
public class Test{
    private static Test obj;
    private Test() { }
    static Test genInstance(){
        if(obj == null) obj = new Test();
        return obj;
    }
}
