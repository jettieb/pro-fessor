package com.example.pro_fessor.mission

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.MissionDto
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class MissionAdapter (private val missionDataList: List<MissionDto>,
                      private val onItemClick: (Int) -> Unit) //람다식으로 인자값 받음
    : RecyclerView.Adapter<MissionAdapter.MissionViewHolder>(){

    class MissionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.mission_name)
        val dateTextView: TextView = view.findViewById(R.id.mission_date)
        val percentTextView: TextView = view.findViewById(R.id.mission_percent)
        val categoryView: ImageView = view.findViewById(R.id.mission_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mission_component, parent, false)
        return MissionViewHolder(view)
    }

    @SuppressLint("DiscouragedApi", "SetTextI18n")
    override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
        val data = missionDataList[position]

        //날짜 format
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
        val startDateFormatted = data.startDate.format(dateFormatter)
        val endDateFormatted = data.endDate.format(dateFormatter)
        //사진 링크
        val link = "mission_${data.category}"
        val context = holder.categoryView.context
        val resId = context.resources.getIdentifier(link, "drawable", context.packageName)

        if (resId != 0) {
            holder.categoryView.setImageResource(resId)
        } else {
            holder.categoryView.setImageResource(R.drawable.mission_1) // 기본 이미지
        }

        holder.nameTextView.text = data.name
        holder.dateTextView.text = "인증 기간: $startDateFormatted ~ $endDateFormatted"
        holder.percentTextView.text = "달성률\n${data.percent}%"

//        // 클릭 이벤트 설정
//        holder.cardView.setOnClickListener {
//            onItemClick(data.memberId) // 클릭된 아이템의 memberId를 전달
//        }
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int = missionDataList.size
}