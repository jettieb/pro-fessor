package com.example.pro_fessor.gallery

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto
import com.example.pro_fessor.sampledata.GalleryGroupData

@Suppress("DEPRECATION")
class GalleryAdapter (private val context: Context,
                      private var dataList: MutableList<GalleryDto>,
                      private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){

    class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.gallery_component_image)
        val cardView: CardView = view.findViewById(R.id.gallery)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_component, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val data = dataList[position]
        if (data.image == -1) {
            data.imagePath?.let { path ->
                try {
                    val bitmap = if (path.startsWith("content://")) {
                        val inputStream = context.contentResolver.openInputStream(Uri.parse(path))
                        BitmapFactory.decodeStream(inputStream)
                    } else {
                        BitmapFactory.decodeFile(path)
                    }
                    holder.imageView.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Log.e("GalleryAdapter", "Error decoding image: ${e.localizedMessage}")
                }
            }
        }
        else {
            holder.imageView.setImageResource(data.image)
        }
        holder.cardView.setOnClickListener {
            onItemClick(data.memberId)
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun updateData(id: Int) {
        val imgData = GalleryData.getGalleryDataList() // Singleton에서 항상 데이터 가져오기
        if (id == 0) {
            dataList.clear()
            dataList.addAll(imgData)
            notifyDataSetChanged()
        } else {
            val groupData = GalleryGroupData.getGalleryGroupDataList()
            val date = groupData.find { it.memberId == id }?.title ?: ""
            val filteredData = imgData.filter { it.date == date }
            dataList.clear()
            dataList.addAll(filteredData)
            notifyDataSetChanged()
        }
    }
}