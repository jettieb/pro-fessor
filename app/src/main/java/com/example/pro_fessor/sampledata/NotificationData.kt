package com.example.pro_fessor.sampledata

import com.example.pro_fessor.notification.NotificationType
import java.time.LocalDate

object NotificationData {
    private val NotificationDataList : MutableList<NotificationDto> =
        mutableListOf(
            NotificationDto(
                id = 1,
                type = NotificationType.GALLERY_POST,
                message = "게시물이 생성되었습니다",
                targetId = 2,
                clicked = false
            ),
            NotificationDto(
                id = 2,
                type = NotificationType.MISSION_COMPLETE,
                message = "도전과제가 완료되었습니다",
                targetId = 3,
                clicked = false
            ),
            NotificationDto(
                id = 3,
                type = NotificationType.MAP_LOCATION,
                message = "이동하였습니다",
                targetId = 2,
                clicked = false
            )
        )

    fun getNotificationDataList(): MutableList<NotificationDto> {
        return NotificationDataList
    }

    fun addNotificationItem(newMissionItem: NotificationDto) {
        NotificationDataList.add(newMissionItem)
    }
}