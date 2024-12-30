package com.example.pro_fessor.sampledata

import java.time.LocalDate

data class MissionDto (
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val isDone: Boolean,
    val category: Int,   //선택한 순서대로 번호 지정
    val percent: Int
)