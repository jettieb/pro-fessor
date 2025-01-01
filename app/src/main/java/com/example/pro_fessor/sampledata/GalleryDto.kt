package com.example.pro_fessor.sampledata

data class GalleryDto(
    val id: Int,
    val date: String,
    val memberId: Int,
    val title: String,
    val abstract: String,
    val imagePath: String? = null,
    val image: Int
)