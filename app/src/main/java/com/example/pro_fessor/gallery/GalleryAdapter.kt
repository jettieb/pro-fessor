package com.example.pro_fessor.gallery

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto
import com.example.pro_fessor.sampledata.GalleryGroupData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.tab1.PhoneActivity


@Suppress("DEPRECATION")


class GalleryAdapter (private val context: Context,
                      private var dataList: MutableList<GalleryDto>,
                      private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){
    //View Holeder 클래스
    class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 컴포넌트의 뷰를 저장하는 변수 선언

        val imageView: ImageView = view.findViewById(R.id.gallery_component_image)
        // val titleTextView: TextView = view.findViewById(R.id.gallery_component_title)
        // val abstractTextView: TextView = view.findViewById(R.id.gallery_component_abstract)
        // val imageGroupView: ImageView = view.findViewById(R.id.gallery_group_image)
        val cardView: CardView = view.findViewById(R.id.gallery)
        val cameraButton = view.findViewById<Button>(R.id.fixed_button)

    }

    // ViewHolder 생성 (아이템 레이아웃과 연결)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        // phone_component.xml 파일을 뷰 객체로 변환
        // 변환한 뷰를 GalleryViewHolder에 전달하여 ViewHold 객체 생성
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_component, parent, false)
        return GalleryViewHolder(view)
    }

    // dataList에 있는 목록들을 순서대로 가져옴
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val data = dataList[position]

        // holder.titleTextView.text = data.title
        // holder.abstractTextView.text = data.abstract

        //TODO: image는 임의로 example_mask로 넣어둠.
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
            // image가 -1이 아닌 경우, 리소스 ID로 설정
            holder.imageView.setImageResource(data.image)
        }


        // holder.imageGroupView.setImageResource(R.drawable.example_mask)
        holder.cardView.setOnClickListener {
            onItemClick(data.memberId) // 클릭된 아이템의 memberId를 전달
        }

    }

    // 아이템 개수 반환
    override fun getItemCount(): Int = dataList.size

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }




    fun updateData(id: Int) {
        val imgData = GalleryData.getGalleryDataList() // Singleton에서 항상 데이터 가져오기
        if (id == 0) {
            dataList.clear()
            dataList.addAll(imgData)
            Log.d("GalleryAdapter", "Updated Data: $dataList")
            notifyDataSetChanged()
        } else {
            val groupData = GalleryGroupData.getGalleryGroupDataList()
            val date = groupData.find { it.memberId == id }?.title ?: ""
            val filteredData = imgData.filter { it.date == date }
            dataList.clear()
            dataList.addAll(filteredData)
            Log.d("GalleryAdapter", "Filtered Data: $filteredData")
            notifyDataSetChanged()
        }
    }

}