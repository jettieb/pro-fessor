package com.example.pro_fessor.tab1

import CVData
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
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto

@Suppress("DEPRECATION")
class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰
        setContentView(R.layout.activity_phone)

        // 상단바 텍스트 변경
        // val topBarTextView = findViewById<TextView>(R.id.top_bar_text)
        // topBarTextView.text = "연락처"

        initRecycler()  //더미데이터 추가

//        val phoneComponent = findViewById<androidx.cardview.widget.CardView>(R.id.phone)
//        //phone detail 화면 라우팅
//        phoneComponent.setOnClickListener {
//            val intent = Intent(this, PhoneDetailActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(0, 0)
//        }

    }

    //dummy data
    private fun initRecycler(){
        val recyclerView: RecyclerView = findViewById(R.id.phone_recycler_view)
        val phoneDataList: List<MemberDto> = MemberData.getPhoneDataList()
        val cvDataList : List<CVDto> = CVData.getCVDataList()

        recyclerView.layoutManager = LinearLayoutManager(this)  // 아이템 세로로 나열
        recyclerView.adapter = PhoneAdapter(phoneDataList, cvDataList) { id ->
            val intent = Intent(this, PhoneDetailActivity::class.java).apply {
                putExtra("id", id)
                //전달된 데이터 getIntent().getIntExtra("id", defaultValue)로 꺼낼 수 있음
            }
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}
