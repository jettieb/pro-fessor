package com.example.pro_fessor.sampledata

data class MemberDto(
    val memberId : Int,
    val name: String,
    val phone: String,
    val email: String,
    val lat : Double,    //위도
    val lng : Double,    //경도
)