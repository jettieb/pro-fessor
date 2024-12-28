package com.example.pro_fessor.gallery

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto
import com.example.pro_fessor.sampledata.GalleryGroupData
import com.example.pro_fessor.sampledata.GalleryGroupDto

@Suppress("DEPRECATION")
class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰
        setContentView(R.layout.activity_gallery)

        // 상단바 텍스트 변경
        // val topBarTextView = findViewById<TextView>(R.id.top_bar_text)
        // topBarTextView.text = "오늘 한 것"

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
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val galleryDataList: List<GalleryDto> = GalleryData.getGalleryDataList()

        val recyclerView1: RecyclerView = findViewById(R.id.recycler_view1)
        val galleryDataList1: List<GalleryGroupDto> = GalleryGroupData.getGalleryGroupDataList()

        recyclerView.layoutManager = GridLayoutManager(this, 2)  // 아이템 세로로 나열
        recyclerView.adapter = GalleryAdapter(galleryDataList)

        recyclerView1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView1.adapter = GalleryGroupAdapter(galleryDataList1)
    }
}
