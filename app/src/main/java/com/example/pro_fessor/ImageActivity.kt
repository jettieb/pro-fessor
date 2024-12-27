package com.example.pro_fessor

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰
        setContentView(R.layout.gallery_layout)


        val phoneButton = findViewById<ImageButton>(R.id.phone_button)
        val imageButton = findViewById<ImageButton>(R.id.image_button)
        // 상단바 텍스트 변경
        val topBarTextView = findViewById<TextView>(R.id.top_bar_text)
        topBarTextView.text = "오늘 한 것"  // 원하는 텍스트로 변경

        phoneButton.setOnClickListener {
            //phone 화면 라우팅
            val intent = Intent(this, PhoneActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        imageButton.setOnClickListener {
            // image 버튼 라우팅
            val intent = Intent(this, ImageActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}
