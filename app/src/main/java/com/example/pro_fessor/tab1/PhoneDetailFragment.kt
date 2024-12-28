package com.example.pro_fessor.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.tab1.PhoneAdapter
import com.example.pro_fessor.tab1.PhoneDetailActivity

@Suppress("DEPRECATION")
class PhoneDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_phone_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberId = arguments?.getInt("id") ?: -1
        if (memberId != -1){
            val memberDataList: List<MemberDto> = MemberData.getPhoneDataList() //member data
            val member = memberDataList.find { it.memberId == memberId }    //람다식 내부에서 그 파라미터를 it라는 기본 이름으로 찾기 가능

            if(member != null){
                val CVDataList: List<CVDto> = CVData.getCVDataList() //cv data
                val cv = CVDataList.find{it.memberId == memberId}
                if(cv != null){
                    // 상단바 텍스트 변경
                    val topBarTextView = view.findViewById<TextView>(R.id.top_bar_text)
                    topBarTextView.text = member.name

                    //xml 파일 내용
                    view.findViewById<TextView>(R.id.phone_detail_name).text = member.name
                    view.findViewById<TextView>(R.id.phone_detail_qualification).text = cv.qualification
                    view.findViewById<TextView>(R.id.phone_detail_email).text = member.email
                    view.findViewById<TextView>(R.id.phone_detail_phone).text = member.phone
                    view.findViewById<TextView>(R.id.phone_detail_edu).text = cv.edu
                    view.findViewById<TextView>(R.id.phone_detail_work).text = cv.experience
                }
            }
        }
    }
}