package com.example.pro_fessor.mission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.MissionData
import com.example.pro_fessor.sampledata.MissionDto

@Suppress("DEPRECATION")
class MissionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_mission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.mission_recycler_view)
        val missionList: List<MissionDto> = MissionData.getMissionDataList()

        // RecyclerView 설정
        recyclerView.layoutManager = LinearLayoutManager(activity)  // 아이템 세로로 나열
        recyclerView.adapter = MissionAdapter(missionList) { id ->
        }
    }

}