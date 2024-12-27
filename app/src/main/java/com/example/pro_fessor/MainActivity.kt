package com.example.pro_fessor

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 하단바 동작 설정 (id값으로 들고옴)
        val homeButton = findViewById<ImageButton>(R.id.home_button)
        val searchButton = findViewById<ImageButton>(R.id.search_button)
        val profileButton = findViewById<ImageButton>(R.id.profile_button)

        homeButton.setOnClickListener {
            // Home 버튼 라우팅
        }

        searchButton.setOnClickListener {
            // Search 버튼 라우팅
        }

        profileButton.setOnClickListener {
            // Profile 버튼 라우팅
        }
    }
}