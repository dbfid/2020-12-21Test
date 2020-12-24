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

}
