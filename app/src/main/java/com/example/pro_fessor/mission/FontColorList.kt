package com.example.pro_fessor.mission

import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto

object FontColorList {
    private val colorList: MutableList<String> =
        mutableListOf("#E5AEB3", "#CC9573", "#FFD662", "#79AC2C", "#78BAEC", "#E5AEB3", "#CC9573", "#FFD662", "#79AC2C", "#78BAEC")

    fun getFontColorList(): MutableList<String> {
        return FontColorList.colorList
    }
}
