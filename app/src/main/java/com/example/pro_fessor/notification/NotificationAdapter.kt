package com.example.pro_fessor.notification

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.map.MapFragment
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.sampledata.MissionData
import com.example.pro_fessor.sampledata.MissionDto
import com.example.pro_fessor.sampledata.NotificationData
import com.example.pro_fessor.sampledata.NotificationDto
import java.time.format.DateTimeFormatter
import java.util.Locale

class NotificationAdapter(
    private val notifications: List<NotificationDto>,
    private val onNotificationClick: (NotificationDto) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_component, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bind(notification)
    }

    override fun getItemCount() = notifications.size


    inner class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val messageTextView: TextView = view.findViewById(R.id.notification_message)
        private val imageView: ImageView = view.findViewById(R.id.notification_category)
        private val color: LinearLayout = view.findViewById(R.id.color)
        private val dateTextView: TextView = view.findViewById(R.id.mission_date)

        fun bind(notification: NotificationDto) {
            if (notification.type == NotificationType.GALLERY_POST) {
                imageView.setImageResource(R.drawable.n_up)
                val galleryId = notification.targetId
                if (galleryId != -1){
                    val galleryDataList: List<GalleryDto> = GalleryData.getGalleryDataList()
                    val gallery = galleryDataList.find { it.id == galleryId }
                    val memberDataList: List<MemberDto> = MemberData.getPhoneDataList()
                    val member = memberDataList.find { it.memberId == gallery?.memberId }

                    val abstractText = gallery?.abstract
                    val titleText = gallery?.title
                    val date = gallery?.date
                    if (date != null) {
                        Log.d("date", date)
                    }
                    val name = member?.name

                    messageTextView.text = name + " 님께서 " + notification.message + "."
                    dateTextView.text = "업로드 날짜: " + date
                }
            }
            else if (notification.type == NotificationType.MAP_LOCATION) {
                imageView.setImageResource(R.drawable.n_loc)
                val phoneId = notification.targetId
                if (phoneId != -1){
                    val memberDataList: List<MemberDto> = MemberData.getPhoneDataList()
                    val member = memberDataList.find { it.memberId == phoneId }
                    val name = member?.name
                    val lat = member?.lat
                    val lng = member?.lng
                    messageTextView.text = name + " 님께서 " + notification.message + "."
                    dateTextView.text = "위도: " + lat + " , 경도: " + lng
                }
            }
            else {
                imageView.setImageResource(R.drawable.n_ch)
                val challengeId = notification.targetId
                val missionDataList: List<MissionDto> = MissionData.getMissionDataList()
                val mission = missionDataList.find { it.id == challengeId}
                val title = mission?.name
                messageTextView.text = "조성원 님께서 \"" + title + "\"\n" + notification.message + "."
                dateTextView.text = "기간: " + mission?.startDate + " - " + mission?.endDate
            }

            color.setBackgroundColor(
                if (notification.clicked) ContextCompat.getColor(itemView.context, R.color.background2)
                else ContextCompat.getColor(itemView.context, R.color.notification_color)
            )

            itemView.setOnClickListener {
                if (!notification.clicked) {
                    notification.clicked = true
                }
                onNotificationClick(notification)
            }
        }
    }
}
