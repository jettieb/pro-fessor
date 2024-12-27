package com.example.pro_fessor

import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰
        setContentView(R.layout.activity_phone)

        // 상단바 텍스트 변경
        val topBarTextView = findViewById<TextView>(R.id.top_bar_text)
        topBarTextView.text = "전화번호"  // 원하는 텍스트로 변경
    }
}
