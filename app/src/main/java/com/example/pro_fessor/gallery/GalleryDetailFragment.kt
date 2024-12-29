package com.example.pro_fessor.gallery

import android.content.Intent
import android.graphics.BitmapFactory
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
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.tab1.PhoneAdapter
import com.example.pro_fessor.tab1.PhoneDetailActivity

@Suppress("DEPRECATION")
class GalleryDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.gallery_phone_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberId = arguments?.getInt("id") ?: -1
        if (memberId != -1){
            val memberDataList: List<GalleryDto> = GalleryData.getGalleryDataList() //member data
            val member = memberDataList.find { it.memberId == memberId }    //람다식 내부에서 그 파라미터를 it라는 기본 이름으로 찾기 가능

            if(member != null){
                val bitmap = BitmapFactory.decodeFile(member.imagePath) // 파일 경로에서 Bitmap 생성
                view.findViewById<ImageView>(R.id.gallery_component_image).setImageBitmap(bitmap)
            }
        }
    }
}