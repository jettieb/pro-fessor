package com.example.pro_fessor.mission

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.gallery.PhoneDetailFragment
import com.example.pro_fessor.map.MapFragment
import com.example.pro_fessor.phone.ListItem
import com.example.pro_fessor.phone.PhoneAdapter
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.sampledata.MissionCompleteDto
import com.example.pro_fessor.sampledata.MissionCompleteData

class MissionCompleteFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PhoneAdapter
    private lateinit var originalData: List<ListItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_mission_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topBarTextView = view.findViewById<TextView>(R.id.top_bar_text)
        topBarTextView.text = "도전 과제"
        val searchButton = view.findViewById<ImageView>(R.id.top_bar_search)
        searchButton.visibility = View.GONE


        recyclerView = view.findViewById(R.id.complete_recycler_view)

        val missionId = arguments?.getInt("missionId") ?: -1
        if(missionId != -1){
            val missionCompleteList: List<MissionCompleteDto> = MissionCompleteData.getMissionCompleteList()

            // missionId가 동일한 memberId값 리스트화
            val filteredMemberIds = missionCompleteList
                .filter { it.missionId == missionId }
                .map { it.memberId }

            // Load data and set up RecyclerView
            originalData = prepareSectionedList(MemberData.getPhoneDataList(), CVData.getCVDataList(), filteredMemberIds)
//            val initialData = listOf<ListItem>()
            adapter = PhoneAdapter(originalData,
                onItemClick = { id ->
                    // Handle item click (e.g., navigate to detail view)
                    val fragment = PhoneDetailFragment().apply {
                        arguments = Bundle().apply {
                            putInt("id", id)
                        }
                    }
                    requireActivity().supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.slide_in_up,
                            0,
                            0,
                            R.anim.slide_out_down
                        )
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit()
                },
                onLocationClick = { id ->
                    val memberDataList: List<MemberDto> = MemberData.getPhoneDataList()
                    val member = memberDataList.find { it.memberId == id }

                    if(member != null){
                        val fragment = MapFragment().apply {
                            arguments = Bundle().apply {
                                putDouble("lat", member.lat)
                                putDouble("lng", member.lng)
                                putInt("memberId", member.memberId)
                            }
                        }

                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit()
                    }
                }
            )


            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }

    fun prepareSectionedList(memberList: List<MemberDto>, cvList: List<CVDto>, filteredMemberIds: List<Int>): List<ListItem> {
        val filteredData = memberList.mapNotNull { member ->
            // memberId가 filteredMemberIds에 포함된 경우만 처리
            if (filteredMemberIds.contains(member.memberId)) {
                val cv = cvList.find { it.memberId == member.memberId }
                cv?.let { member to it }
            } else {
                null
            }
        }

        val sectionedList = mutableListOf<ListItem>()
        // 필터링된 데이터만 리스트에 추가
        filteredData.forEach { (member, cv) ->
            sectionedList.add(ListItem.Contact(member, cv.qualification))
        }
        return sectionedList
    }
}