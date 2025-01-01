package com.example.pro_fessor.mission

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.gallery.PhoneDetailFragment
import com.example.pro_fessor.sampledata.MissionCompleteData
import com.example.pro_fessor.sampledata.MissionData
import com.example.pro_fessor.sampledata.MissionDto

class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_mission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topBarTextView = view.findViewById<TextView>(R.id.top_bar_text)
        topBarTextView.text = "도전 과제"

        val progressView: LinearLayout = view.findViewById(R.id.progress_btn)
        val layoutParams = progressView.layoutParams as FrameLayout.LayoutParams
        val inProgressText: TextView = view.findViewById(R.id.btn_in_progress)
        val completeText: TextView = view.findViewById(R.id.btn_completed)
        val uploadButton: Button = view.findViewById(R.id.btn_add)
        val searchButton = view.findViewById<ImageView>(R.id.top_bar_search)
        searchButton.visibility = View.GONE


        inProgressText.setOnClickListener {
            layoutParams.gravity = Gravity.START
            updateButtonColors(inProgressText, completeText)
            progressView.layoutParams = layoutParams
            switchFragment(InProgressMissionFragment())
        }

        completeText.setOnClickListener {
            layoutParams.gravity = Gravity.END
            updateButtonColors(completeText, inProgressText)
            progressView.layoutParams = layoutParams
            switchFragment(CompletedMissionFragment())
        }

        // 미션 업로드 이벤트 설정
        uploadButton.setOnClickListener {
            val fragment = MissionUploadFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit()
        }

        // 초기 화면 설정 (미완료 미션 리스트)
        switchFragment(InProgressMissionFragment())
    }

    private fun updateButtonColors(selectedText: TextView, deselectedText: TextView) {
        selectedText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        deselectedText.setTextColor(ContextCompat.getColor(requireContext(), R.color.main_color2))
    }

    private fun switchFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.inner_content_frame, fragment)
            .commit()
    }
}
