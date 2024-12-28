package com.example.pro_fessor.phone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.MemberDto

class PhoneAdapter (private val dataList: List<MemberDto>,
                    private val onItemClick: (Int) -> Unit) //람다식으로 인자값 받음
    : RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>(){

    //View Holeder 클래스
    class PhoneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 컴포넌트의 뷰를 저장하는 변수 선언
        val nameTextView: TextView = view.findViewById(R.id.phone_component_name)
        val phoneTextView: TextView = view.findViewById(R.id.phone_component_phone)
        val emailTextView: TextView = view.findViewById(R.id.phone_component_email)
        val imageView: ImageView = view.findViewById(R.id.phone_component_image)
        val cardView: CardView = view.findViewById(R.id.phone)
    }

    // ViewHolder 생성 (아이템 레이아웃과 연결)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        // phone_component.xml 파일을 뷰 객체로 변환
        // 변환한 뷰를 PhoneViewHolder에 전달하여 ViewHold 객체 생성
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.phone_component, parent, false)
        return PhoneViewHolder(view)
    }

    // dataList에 있는 목록들을 순서대로 가져옴
    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        val data = dataList[position]

        holder.nameTextView.text = data.name
        holder.phoneTextView.text = data.phone
        holder.emailTextView.text = data.email
        //TODO: image는 임의로 example_mask로 넣어둠.
        holder.imageView.setImageResource(R.drawable.example_mask)

        // 클릭 이벤트 설정
        holder.cardView.setOnClickListener {
            onItemClick(data.memberId) // 클릭된 아이템의 memberId를 전달
        }
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int = dataList.size
}