package com.example.pro_fessor.notification

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.map.MapFragment
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

        fun bind(notification: NotificationDto) {
            messageTextView.text = notification.message
            itemView.setOnClickListener {
                onNotificationClick(notification)
            }
            if (notification.clicked) {
                messageTextView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.background2))
            }
        }
    }
}
