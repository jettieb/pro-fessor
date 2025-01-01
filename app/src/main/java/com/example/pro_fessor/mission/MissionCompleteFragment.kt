package com.example.pro_fessor.mission

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
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
import com.example.pro_fessor.sampledata.MissionData
import com.example.pro_fessor.sampledata.MissionDto
import java.time.format.DateTimeFormatter
import java.util.Locale

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

    @SuppressLint("SetTextI18n", "DiscouragedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topBarTextView = view.findViewById<TextView>(R.id.top_bar_text)
        topBarTextView.text = "도전 과제"
        val searchButton = view.findViewById<ImageView>(R.id.top_bar_search)
        searchButton.visibility = View.GONE

        //component 정보 입력
        val missionId = arguments?.getInt("missionId") ?: -1

        recyclerView = view.findViewById(R.id.complete_recycler_view)
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

            val missionList: List<MissionDto> = MissionData.getMissionDataList()
            val mission = missionList.find { it.id == missionId }
            //날짜 format
            if(mission != null){
                view.findViewById<TextView>(R.id.mission_name).text = mission.name
                val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
                val startDateFormatted = mission.startDate.format(dateFormatter)
                val endDateFormatted = mission.endDate.format(dateFormatter)
                view.findViewById<TextView>(R.id.mission_date).text = "인증 기간: $startDateFormatted ~ $endDateFormatted"
                view.findViewById<TextView>(R.id.mission_percent).text = "달성률\n${mission.percent}%"

                //사진 링크
                val link = "mission_${mission.category}"
                val context = view.findViewById<ImageView>(R.id.mission_category).context
                val resId = context.resources.getIdentifier(link, "drawable", context.packageName)

                if (resId != 0) {
                    view.findViewById<ImageView>(R.id.mission_category).setImageResource(resId)
                } else {
                    view.findViewById<ImageView>(R.id.mission_category).setImageResource(R.drawable.mission_1) // 기본 이미지
                }
            }
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