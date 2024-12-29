package com.example.pro_fessor

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pro_fessor.gallery.GalleryFragment
import com.example.pro_fessor.gallery.PhoneFragment


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().
            replace(R.id.content_frame, PhoneFragment()).commit()

        // 하단바 동작 설정 (id값으로 들고옴)
        val phoneButton = findViewById<ImageButton>(R.id.phone_button)
        val imageButton = findViewById<ImageButton>(R.id.image_button)
        val otherButton = findViewById<ImageButton>(R.id.other_button)

        phoneButton.setOnClickListener {
            Log.d("Button","button clicked")

            //phone 화면 라우팅
//            val intent = Intent(this, PhoneActivity::class.java)
//            startActivity(intent)
            supportFragmentManager.beginTransaction().
                replace(R.id.content_frame, PhoneFragment()).commit()
            //overridePendingTransition(0, 0)
        }

        imageButton.setOnClickListener {
            // image 버튼 라우팅
//            val intent = Intent(this, GalleryActivity::class.java)
//            startActivity(intent)
            supportFragmentManager.beginTransaction().
            replace(R.id.content_frame, GalleryFragment()).commit()
           // overridePendingTransition(0, 0)
        }

        otherButton.setOnClickListener {
            // other 버튼 라우팅
        }
    }
}