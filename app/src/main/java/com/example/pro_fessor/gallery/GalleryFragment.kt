package com.example.pro_fessor.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto
import com.example.pro_fessor.sampledata.GalleryGroupData
import com.example.pro_fessor.sampledata.GalleryGroupDto
import com.example.pro_fessor.sampledata.PhoneData
import com.example.pro_fessor.sampledata.PhoneDto
import com.example.pro_fessor.tab1.PhoneAdapter

class GalleryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val galleryDataList: List<GalleryDto> = GalleryData.getGalleryDataList()

        val recyclerView1: RecyclerView = view.findViewById(R.id.recycler_view1)
        val galleryDataList1: List<GalleryGroupDto> = GalleryGroupData.getGalleryGroupDataList()

        recyclerView.layoutManager = GridLayoutManager(activity, 2)  // 아이템 세로로 나열
        recyclerView.adapter = GalleryAdapter(galleryDataList)

        recyclerView1.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView1.adapter = GalleryGroupAdapter(galleryDataList1)
    }
}