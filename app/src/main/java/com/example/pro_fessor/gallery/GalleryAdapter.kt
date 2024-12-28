package com.example.pro_fessor.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.GalleryDto
import com.example.pro_fessor.sampledata.MemberDto

class GalleryAdapter (private val dataList: List<GalleryDto>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){
    //View Holeder 클래스
    class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 컴포넌트의 뷰를 저장하는 변수 선언
        val imageView: ImageView = view.findViewById(R.id.gallery_component_image)
        // val titleTextView: TextView = view.findViewById(R.id.gallery_component_title)
        // val abstractTextView: TextView = view.findViewById(R.id.gallery_component_abstract)
        // val imageGroupView: ImageView = view.findViewById(R.id.gallery_group_image)
    }

    // ViewHolder 생성 (아이템 레이아웃과 연결)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        // phone_component.xml 파일을 뷰 객체로 변환
        // 변환한 뷰를 GalleryViewHolder에 전달하여 ViewHold 객체 생성
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_component, parent, false)
        return GalleryViewHolder(view)
    }

    // dataList에 있는 목록들을 순서대로 가져옴
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val data = dataList[position]

        // holder.titleTextView.text = data.title
        // holder.abstractTextView.text = data.abstract

        //TODO: image는 임의로 example_mask로 넣어둠.
        holder.imageView.setImageResource(R.drawable.img)
        // holder.imageGroupView.setImageResource(R.drawable.example_mask)
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int = dataList.size
}