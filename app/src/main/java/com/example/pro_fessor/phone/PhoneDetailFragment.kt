package com.example.pro_fessor.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto

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
            val member = memberDataList.find { it.memberId == memberId }

            if(member != null){
                val CVDataList: List<CVDto> = CVData.getCVDataList() //cv data
                val cv = CVDataList.find{it.memberId == memberId}
                if(cv != null){
                    // 상단바 텍스트 변경 및 뒤로가기 버튼 추가
                    view.findViewById<TextView>(R.id.top_bar_text).text = member.name
                    val backArrow = view.findViewById<ImageView>(R.id.top_bar_arrow)
                    backArrow.visibility = View.VISIBLE
                    backArrow.setOnClickListener {
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }

                    //xml 파일 내용
                    view.findViewById<ImageView>(R.id.phone_detail_image).setImageResource(member.imgPath)
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