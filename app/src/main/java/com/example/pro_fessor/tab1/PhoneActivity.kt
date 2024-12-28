package com.example.pro_fessor.tab1

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.ImageActivity
import com.example.pro_fessor.R
import com.example.pro_fessor.gallery.GalleryActivity
import com.example.pro_fessor.sampledata.PhoneData
import com.example.pro_fessor.sampledata.PhoneDto

@Suppress("DEPRECATION")
class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰
        setContentView(R.layout.activity_phone)

        // 상단바 텍스트 변경
        val topBarTextView = findViewById<TextView>(R.id.top_bar_text)
        topBarTextView.text = "연락처"

        initRecycler()  //더미데이터 추가

//        val phoneComponent = findViewById<androidx.cardview.widget.CardView>(R.id.phone)
//        //phone detail 화면 라우팅
//        phoneComponent.setOnClickListener {
//            val intent = Intent(this, PhoneDetailActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(0, 0)
//        }

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
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    //dummy data
    private fun initRecycler(){
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val phoneDataList: List<PhoneDto> = PhoneData.getPhoneDataList()

        recyclerView.layoutManager = LinearLayoutManager(this)  // 아이템 세로로 나열
        recyclerView.adapter = PhoneAdapter(phoneDataList)
    }
}
