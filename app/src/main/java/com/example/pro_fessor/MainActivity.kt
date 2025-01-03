package com.example.pro_fessor

import android.content.Intent
import android.content.UriPermission
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pro_fessor.gallery.GalleryFragment
import com.example.pro_fessor.gallery.PhoneFragment
import android.graphics.Camera
import android.net.Uri
import android.widget.LinearLayout
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pro_fessor.map.MapFragment
import com.example.pro_fessor.mission.MainFragment
import com.example.pro_fessor.mission.MissionFragment


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
        val phoneLayout= findViewById<LinearLayout>(R.id.phoneLayout)
        val imageLayout = findViewById<LinearLayout>(R.id.imageLayout)
        val otherLayout = findViewById<LinearLayout>(R.id.otherLayout)
        val phoneButton = findViewById<ImageButton>(R.id.phone_button)
        val imageButton = findViewById<ImageButton>(R.id.image_button)
        val otherButton = findViewById<ImageButton>(R.id.other_button)
        val missionButton = findViewById<ImageButton>(R.id.mission_button)

        phoneLayout.setOnClickListener {
            Log.d("Button","button clicked")
            phoneButton.setImageResource(R.drawable.bottom_phone)
            imageButton.setImageResource(R.drawable.bottom_image_unselected)
            otherButton.setImageResource(R.drawable.bottom_other_unselected)
            missionButton.setImageResource(R.drawable.bottom_mission_unselected)
            supportFragmentManager.beginTransaction().
                replace(R.id.content_frame, PhoneFragment()).commit()
        }
        phoneButton.setOnClickListener {
            Log.d("Button","button clicked")
            phoneButton.setImageResource(R.drawable.bottom_phone)
            imageButton.setImageResource(R.drawable.bottom_image_unselected)
            otherButton.setImageResource(R.drawable.bottom_other_unselected)
            supportFragmentManager.beginTransaction().
            replace(R.id.content_frame, PhoneFragment()).commit()
        }

        imageLayout.setOnClickListener {
            phoneButton.setImageResource(R.drawable.bottom_phone_unselected)
            imageButton.setImageResource(R.drawable.bottom_image)
            otherButton.setImageResource(R.drawable.bottom_other_unselected)
            supportFragmentManager.beginTransaction().
            replace(R.id.content_frame, GalleryFragment()).commit()
        }
        imageButton.setOnClickListener {
            phoneButton.setImageResource(R.drawable.bottom_phone_unselected)
            imageButton.setImageResource(R.drawable.bottom_image)
            otherButton.setImageResource(R.drawable.bottom_other_unselected)
            missionButton.setImageResource(R.drawable.bottom_mission_unselected)
            supportFragmentManager.beginTransaction().
            replace(R.id.content_frame, GalleryFragment()).commit()
        }

        otherLayout.setOnClickListener {
            phoneButton.setImageResource(R.drawable.bottom_phone_unselected)
            imageButton.setImageResource(R.drawable.bottom_image_unselected)
            otherButton.setImageResource(R.drawable.bottom_other)
            supportFragmentManager.beginTransaction().
            replace(R.id.content_frame, MapFragment()).commit()
        }
        otherButton.setOnClickListener {
            phoneButton.setImageResource(R.drawable.bottom_phone_unselected)
            imageButton.setImageResource(R.drawable.bottom_image_unselected)
            otherButton.setImageResource(R.drawable.bottom_other)
            missionButton.setImageResource(R.drawable.bottom_mission_unselected)
            supportFragmentManager.beginTransaction().
            replace(R.id.content_frame, MapFragment()).commit()
        }

        missionButton.setOnClickListener {
            phoneButton.setImageResource(R.drawable.bottom_phone_unselected)
            imageButton.setImageResource(R.drawable.bottom_image_unselected)
            otherButton.setImageResource(R.drawable.bottom_other_unselected)
            missionButton.setImageResource(R.drawable.bottom_mission)
            supportFragmentManager.beginTransaction().
            replace(R.id.content_frame, MainFragment()).commit()
        }
    }
}