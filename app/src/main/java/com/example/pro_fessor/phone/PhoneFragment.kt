package com.example.pro_fessor.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
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
        val cvDataList : List<CVDto> = CVData.getCVDataList()

        recyclerView.layoutManager = LinearLayoutManager(activity)  // 아이템 세로로 나열
        recyclerView.adapter = PhoneAdapter(phoneDataList, cvDataList) { id ->

            val fragment = PhoneDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", id)
                }
            }

            requireActivity().supportFragmentManager.beginTransaction().
                replace(R.id.content_frame, fragment).
                addToBackStack(null).commit()
        }
    }
}