package com.example.pro_fessor

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 하단바 동작 설정 (id값으로 들고옴)
        val phoneButton = findViewById<ImageButton>(R.id.phone_button)
        val imageButton = findViewById<ImageButton>(R.id.image_button)
        val otherButton = findViewById<ImageButton>(R.id.other_button)

        phoneButton.setOnClickListener {
            //phone 화면 라우팅
            val intent = Intent(this, PhoneActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        imageButton.setOnClickListener {
            // image 버튼 라우팅
        }

        otherButton.setOnClickListener {
            // other 버튼 라우팅
        }
    }
}