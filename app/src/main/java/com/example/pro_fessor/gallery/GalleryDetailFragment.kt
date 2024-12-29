package com.example.pro_fessor.gallery

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryDto

@Suppress("DEPRECATION")
class GalleryDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.gallery_phone_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberId = arguments?.getInt("id") ?: -1
        if (memberId != -1){
            val memberDataList: List<GalleryDto> = GalleryData.getGalleryDataList()
            val member = memberDataList.find { it.memberId == memberId }

            if (member?.image == -1) {
                member.imagePath?.let { path ->
                    try {
                        val bitmap = if (path.startsWith("content://")) {
                            val inputStream = requireContext().contentResolver.openInputStream(Uri.parse(path))
                            BitmapFactory.decodeStream(inputStream)
                        } else {
                            BitmapFactory.decodeFile(path)
                        }
                        view.findViewById<ImageView>(R.id.gallery_component_image).setImageBitmap(bitmap)
                    } catch (e: Exception) {
                        Log.e("GalleryUploadFragment", "Error decoding image: $e")
                    }
                }
            }
            else {
                member?.image?.let {
                    view.findViewById<ImageView>(R.id.gallery_component_image).setImageResource(it)
                }
            }

        }


    }
}