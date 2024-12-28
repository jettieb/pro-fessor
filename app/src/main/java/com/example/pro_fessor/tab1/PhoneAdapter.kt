package com.example.pro_fessor.tab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.MemberDto

class PhoneAdapter (private val memberList: List<MemberDto>,
                    private val cvList: List<CVDto>,
                    private val onItemClick: (Int) -> Unit) //람다식으로 인자값 받음
    : RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>(){

    //View Holeder 클래스
    class PhoneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 컴포넌트의 뷰를 저장하는 변수 선언
        val nameTextView: TextView = view.findViewById(R.id.phone_component_name)
        val statusTextView: TextView = view.findViewById(R.id.phone_component_status)
        val imageView: ImageView = view.findViewById(R.id.phone_component_image)
        val cardView: CardView = view.findViewById(R.id.phone)
        val moreView: LinearLayout = view.findViewById(R.id.phone_component_more)
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
        val member = memberList[position]
        val cv = cvList.find{it.memberId == member.memberId}

        if(cv != null){
            holder.nameTextView.text = member.name
            holder.statusTextView.text = cv.qualification
            //TODO: image는 임의로 example_mask로 넣어둠.
            holder.imageView.setImageResource(R.drawable.example_mask)

            // 클릭 이벤트 설정
            holder.cardView.setOnClickListener {
                holder.moreView.visibility = View.VISIBLE
                onItemClick(member.memberId) // 클릭된 아이템의 memberId를 전달
            }
        }
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int = memberList.size
}