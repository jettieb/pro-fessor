package com.example.pro_fessor.tab1

import android.util.Log
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
        
    // 하나만 창이 열릴 수 있도록 이전에 열린 창 저장하는 변수 설정
    private var previousClick: Int = -1

    //View Holeder 클래스
    class PhoneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 컴포넌트의 뷰를 저장하는 변수 선언
        val nameTextView: TextView = view.findViewById(R.id.phone_component_name)
        val statusTextView: TextView = view.findViewById(R.id.phone_component_status)
        val imageView: ImageView = view.findViewById(R.id.phone_component_image)
        val cardView: CardView = view.findViewById(R.id.phone)
        val moreView: LinearLayout = view.findViewById(R.id.phone_component_more)
        //전화/메세지/정보
        val callView: ImageView = view.findViewById(R.id.phone_call)
        val messageView: ImageView = view.findViewById(R.id.phone_message)
        val infoView: ImageView = view.findViewById(R.id.phone_info)
    }

    // ViewHolder 생성 (아이템 레이아웃과 연결)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        // phone_component.xml 파일을 뷰 객체로 변환
        // 변환한 뷰를 PhoneViewHolder에 전달하여 ViewHold 객체 생성
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.phone_component, parent, false)
        return PhoneViewHolder(view)
    }

    // 생성된 뷰홀더에 데이터를 바인딩 - 스크롤 및 이벤트 발생 시 마다 호출됨!
    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        val member = memberList[position]
        val cv = cvList.find{it.memberId == member.memberId}

        if(cv != null){
            holder.nameTextView.text = member.name
            holder.statusTextView.text = cv.qualification
            //TODO: image는 임의로 example_mask로 넣어둠.
            holder.imageView.setImageResource(R.drawable.example_mask)

            // 현재 아이템이 previousClick인 경우 moreView를 표시, 아니면 숨김
            holder.moreView.visibility = if (position == previousClick) View.VISIBLE else View.GONE
            // 클릭 이벤트
            holder.cardView.setOnClickListener {
                // 이전에 열렸던 창이 있으면 닫음
                if (previousClick != -1 && previousClick != position) {
                    notifyItemChanged(previousClick)    //이전 창만 새로 갱신
                }
                // 동일한 아이템 클릭 시 닫기
                // 다른 아이템 클릭 시 previousClick 변수 설정
                previousClick = if (position == previousClick) -1 else position
                notifyItemChanged(position) // 현재 창 열기/닫기
            }

            // 전화 걸기
            holder.callView.setOnClickListener{

            }
            // 메세지 보내기
            holder.messageView.setOnClickListener {

            }
            //정보보기 - 다음
            holder.infoView.setOnClickListener {
                onItemClick(member.memberId) // 클릭된 아이템의 memberId를 전달
            }
        }
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int = memberList.size
}