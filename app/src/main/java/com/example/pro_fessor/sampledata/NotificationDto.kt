package com.example.pro_fessor.sampledata

import com.example.pro_fessor.notification.NotificationType
import java.time.LocalDate

data class NotificationDto (
    val id: Int,
    val type: NotificationType,
    val message: String,
    val targetId: Int, // 이동 대상 ID
    var clicked: Boolean
)