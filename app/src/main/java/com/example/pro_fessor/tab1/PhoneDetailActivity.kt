package com.example.pro_fessor.tab1

import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto

class PhoneDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)    //자동 생성 상단바 없앰
        setContentView(R.layout.activity_phone_detail)

        val memberId = intent.getIntExtra("id", -1)
        if (memberId != -1){
            val memberDataList: List<MemberDto> = MemberData.getPhoneDataList() //member data
            val member = memberDataList.find { it.memberId == memberId }    //람다식 내부에서 그 파라미터를 it라는 기본 이름으로 찾기 가능

            if(member != null){
                val CVDataList: List<CVDto> = CVData.getCVDataList() //cv data
                val cv = CVDataList.find{it.memberId == memberId}
                if(cv != null){
                    // 상단바 텍스트 변경 및 뒤로가기 버튼 추가
                    val topBarTextView = findViewById<TextView>(R.id.top_bar_text)
                    topBarTextView.text = member.name
                    findViewById<ImageView>(R.id.top_bar_arrow).visibility = View.VISIBLE

                    //xml 파일 내용
                    findViewById<TextView>(R.id.phone_detail_name).text = member.name
                    findViewById<TextView>(R.id.phone_detail_qualification).text = cv.qualification
                    findViewById<TextView>(R.id.phone_detail_email).text = member.email
                    findViewById<TextView>(R.id.phone_detail_phone).text = member.phone
                    findViewById<TextView>(R.id.phone_detail_edu).text = cv.edu
                    findViewById<TextView>(R.id.phone_detail_work).text = cv.experience
                }
            }
        }
    }
}