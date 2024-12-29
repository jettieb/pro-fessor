package com.example.pro_fessor.gallery

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.tab1.PhoneAdapter
import com.example.pro_fessor.tab1.PhoneDetailActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("DEPRECATION")
class GalleryUploadFragment(private val galleryAdapter: GalleryAdapter?) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.gallery_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagePath = arguments?.getString("imagePath")
        val titleEditText = view.findViewById<EditText>(R.id.titleEditText)
        val abstractEditText = view.findViewById<EditText>(R.id.abstractEditText)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val uploadButton = view.findViewById<Button>(R.id.uploadButton)



        imagePath?.let { path ->
            try {
                val bitmap = if (path.startsWith("content://")) {
                    val inputStream = requireContext().contentResolver.openInputStream(Uri.parse(path))
                    BitmapFactory.decodeStream(inputStream)
                } else {
                    BitmapFactory.decodeFile(path)
                }
                view.findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
            } catch (e: Exception) {
                Log.e("GalleryUploadFragment", "Error decoding image: $e")
            }
        }

        // 업로드 버튼 클릭 이벤트 처리
        uploadButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val abstractText = abstractEditText.text.toString()

            if (title.isBlank() || abstractText.isBlank()) {
                Toast.makeText(requireContext(), "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 새 데이터 생성
            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val newGalleryItem = GalleryDto(
                date = currentDate,
                memberId = GalleryData.getGalleryDataList().size + 1,
                title = title,
                abstract = abstractText,
                imagePath = imagePath,
                image = -1
            )
            Log.d("Date", currentDate)
            // 데이터 리스트에 추가
            GalleryData.addGalleryItem(newGalleryItem)
            Log.d("GalleryData", "Current GalleryData: ${GalleryData.getGalleryDataList()}")
            galleryAdapter?.updateData(0)
            // Fragment 전환 또는 갤러리 화면 갱신
            Toast.makeText(requireContext(), "업로드 완료!", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
