package com.example.pro_fessor.tab1

import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.PhoneDto

class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰
        setContentView(R.layout.activity_phone)

        // 상단바 텍스트 변경
        val topBarTextView = findViewById<TextView>(R.id.top_bar_text)
        topBarTextView.text = "연락처"

        initRecycler()  //더미데이터

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
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val phoneDataList = listOf(
            PhoneDto("홍길동", "010-0000-0000", "red_road_dong@gmail.com"),
            PhoneDto("김철수", "010-1234-5678", "cheolsoo_kim@gmail.com"),
            PhoneDto("이영희", "010-9876-5432", "younghee_lee@gmail.com"),
            PhoneDto("박지훈", "010-5555-1111", "jihoon_park@gmail.com"),
            PhoneDto("최수민", "010-2222-3333", "sumin_choi@gmail.com"),
            PhoneDto("정유진", "010-4444-5555", "yujin_jung@gmail.com"),
            PhoneDto("오세현", "010-6666-7777", "sehyeon_oh@gmail.com"),
            PhoneDto("한예진", "010-8888-9999", "yejin_han@gmail.com"),
            PhoneDto("유다인", "010-1212-3434", "dain_yoo@gmail.com"),
            PhoneDto("강준혁", "010-5656-7878", "junhyuk_kang@gmail.com"),
            PhoneDto("이소현", "010-7878-9090", "sohyun_lee@gmail.com"),
            PhoneDto("김태연", "010-1414-5252", "taeyeon_kim@gmail.com"),
            PhoneDto("노경민", "010-8585-9696", "kyungmin_no@gmail.com")
        )


        recyclerView.layoutManager = LinearLayoutManager(this)  // 아이템 세로로 나열
        recyclerView.adapter = PhoneAdapter(phoneDataList)
    }
}
