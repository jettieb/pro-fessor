package com.example.pro_fessor.sampledata

data class MemberDto(
    val memberId : Int,
    val name: String,
    val phone: String,
    val email: String,
    val lat : Double,    //위도
    val lng : Double,    //경도
    val imgPath: Int,
    val major: String,
    val minor: String,
    val birth: String,
    val home: String
)