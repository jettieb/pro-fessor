package com.example.pro_fessor.phone

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto

@Suppress("DEPRECATION")
class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰
        setContentView(R.layout.activity_phone)
        initRecycler()  //더미데이터 추가
    }

    //dummy data
    private fun initRecycler(){
        val recyclerView: RecyclerView = findViewById(R.id.phone_recycler_view)
        val phoneDataList: List<MemberDto> = MemberData.getPhoneDataList()

        recyclerView.layoutManager = LinearLayoutManager(this)  // 아이템 세로로 나열
        recyclerView.adapter = PhoneAdapter(phoneDataList) { id ->
            val intent = Intent(this, PhoneDetailActivity::class.java).apply {
                putExtra("id", id)
                //전달된 데이터 getIntent().getIntExtra("id", defaultValue)로 꺼낼 수 있음
            }
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}
