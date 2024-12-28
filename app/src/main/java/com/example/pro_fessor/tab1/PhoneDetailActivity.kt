package com.example.pro_fessor.tab1

import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pro_fessor.R

class PhoneDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰
        setContentView(R.layout.activity_phone_detail)

        // 상단바 텍스트 변경
        val topBarTextView = findViewById<TextView>(R.id.top_bar_text)
        topBarTextView.text = "홍길동"
    }
}