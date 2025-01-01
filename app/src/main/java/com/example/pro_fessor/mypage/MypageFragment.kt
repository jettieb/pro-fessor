package com.example.pro_fessor.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.gallery.GalleryDetailFragmentNotification
import com.example.pro_fessor.map.MapFragment
import com.example.pro_fessor.mission.MissionCompleteFragment
import com.example.pro_fessor.notification.NotificationAdapter
import com.example.pro_fessor.notification.NotificationType
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.sampledata.NotificationData
import com.example.pro_fessor.sampledata.NotificationDto

@Suppress("DEPRECATION")
class MypageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_mypage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backArrow = view.findViewById<ImageView>(R.id.top_bar_arrow)
        backArrow.visibility = View.VISIBLE

        backArrow.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}