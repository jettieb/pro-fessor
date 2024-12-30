package com.example.pro_fessor.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.phone.ListItem
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.phone.PhoneAdapter



@Suppress("DEPRECATION")
class PhoneFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.phone_recycler_view)
        val phoneDataList: List<MemberDto> = MemberData.getPhoneDataList()
        val cvDataList: List<CVDto> = CVData.getCVDataList()

        // 섹션화된 데이터 준비
        val sectionedList = prepareSectionedList(phoneDataList, cvDataList)


        // RecyclerView 설정
        recyclerView.layoutManager = LinearLayoutManager(activity)  // 아이템 세로로 나열
        Log.d("hi", sectionedList.toString())
        recyclerView.adapter = PhoneAdapter(sectionedList) { id ->
            // 클릭 이벤트 처리
            val fragment = PhoneDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", id)
                }
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit()
        }
    }


    fun prepareSectionedList(memberList: List<MemberDto>, cvList: List<CVDto>): List<ListItem> {
        val groupedData = memberList.mapNotNull { member ->
            val cv = cvList.find { it.memberId == member.memberId }
            cv?.let { member to it }
        }.groupBy { it.second.qualification } // 그룹화: 박사, 석사, 학사

        val sectionedList = mutableListOf<ListItem>()

        // 그룹별로 정렬 후 헤더와 연락처 추가
        listOf("박사", "석사", "인턴").forEach { qualification ->
            val group = groupedData[qualification]
            if (!group.isNullOrEmpty()) {
                sectionedList.add(ListItem.Header(qualification))
                sectionedList.addAll(group.map { ListItem.Contact(it.first, qualification) })
            }
        }

        return sectionedList
    }
}