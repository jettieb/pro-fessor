package com.example.pro_fessor.sampledata

import com.example.pro_fessor.R

object GalleryData {
    private val galleryList: MutableList<GalleryDto> =
        mutableListOf(
            GalleryDto("2024-12-29",1, "연구실 탐험1", "오늘은 연구실1 탐험을 했어요", "", R.drawable.img),
            GalleryDto("12-30",2, "연구실 탐험1", "오늘은 연구실1 탐험을 했어요", "", R.drawable.img),
            GalleryDto("12-31",2, "연구실 탐험2", "오늘은 연구실2 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-31",3, "연구실 탐험3", "오늘은 연구실3 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-31",4, "연구실 탐험4", "오늘은 연구실4 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-31",5, "연구실 탐험5", "오늘은 연구실5 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-31",6, "연구실 탐험6", "오늘은 연구실6 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-31",7, "연구실 탐험7", "오늘은 연구실7 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-30",8,"연구실 탐험8", "오늘은 연구실8 탐험을 했어요", "@drawable/example_mask", R.drawable.img),
            GalleryDto("12-31",9,"연구실 탐험9", "오늘은 연구실9 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-31",10, "연구실 탐험10", "오늘은 연구실10 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-29",11, "연구실 탐험1", "오늘은 연구실1 탐험을 했어요", "@drawable/img_1", R.drawable.img),
            GalleryDto("12-28",12, "연구실 탐험2", "오늘은 연구실2 탐험을 했어요", "@drawable/img_2", R.drawable.img),
            GalleryDto("12-31",13, "연구실 탐험3", "오늘은 연구실3 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-29",14, "연구실 탐험4", "오늘은 연구실4 탐험을 했어요", "@drawable/img_2", R.drawable.img),
            GalleryDto("12-28",15, "연구실 탐험5", "오늘은 연구실5 탐험을 했어요", "@drawable/img_1", R.drawable.img),
            GalleryDto("12-29",16,"연구실 탐험6", "오늘은 연구실6 탐험을 했어요", "@drawable/img_2", R.drawable.img),
            GalleryDto("12-28",17, "연구실 탐험7", "오늘은 연구실7 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-31",18, "연구실 탐험8", "오늘은 연구실8 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-30",19,"연구실 탐험9", "오늘은 연구실9 탐험을 했어요", "@drawable/img", R.drawable.img),
            GalleryDto("12-31",20, "연구실 탐험10", "오늘은 연구실10 탐험을 했어요", "@drawable/img", R.drawable.img)
        )

    fun getGalleryDataList(): MutableList<GalleryDto> {
        return galleryList
    }

    fun addGalleryItem(item: GalleryDto) {
        galleryList.add(item)
    }

    fun clearGalleryData() {
        galleryList.clear()
    }
}

