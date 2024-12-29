package com.example.pro_fessor.gallery

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
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto
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

        uploadButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val abstractText = abstractEditText.text.toString()

            if (title.isBlank() || abstractText.isBlank()) {
                Toast.makeText(requireContext(), "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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
            GalleryData.addGalleryItem(newGalleryItem)
            Log.d("GalleryData", "Current GalleryData: ${GalleryData.getGalleryDataList()}")
            galleryAdapter?.updateData(0)
            Toast.makeText(requireContext(), "업로드 완료!", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
