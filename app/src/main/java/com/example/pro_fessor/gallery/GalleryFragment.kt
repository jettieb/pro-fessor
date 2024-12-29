package com.example.pro_fessor.gallery

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.R
import com.example.pro_fessor.sampledata.GalleryData
import com.example.pro_fessor.sampledata.GalleryData.getGalleryDataList
import com.example.pro_fessor.sampledata.GalleryDto
import com.example.pro_fessor.sampledata.GalleryGroupData

import com.example.pro_fessor.sampledata.GalleryGroupDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.tab1.PhoneActivity
import com.example.pro_fessor.tab1.PhoneAdapter
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("DEPRECATION")

class GalleryFragment : Fragment() {
    private val REQUEST_IMAGE_CAPTURE = 101
    private val CAMERA_PERMISSION_CODE = 103
    private val REQUEST_IMAGE_PICK = 102
    private var galleryAdapter: GalleryAdapter? = null
    private var galleryDataList: MutableList<GalleryDto> = GalleryData.getGalleryDataList()
    private var imageFilePath: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        var galleryDataList: MutableList<GalleryDto> = GalleryData.getGalleryDataList()

        val recyclerView1: RecyclerView = view.findViewById(R.id.recycler_view1)
        val galleryDataList1: List<GalleryGroupDto> = GalleryGroupData.getGalleryGroupDataList()
        val topBarTextView = view.findViewById<TextView>(R.id.top_bar_text)
        topBarTextView.text = "오늘 한 것"

        val cameraButton = view.findViewById<Button>(R.id.fixed_button)


        recyclerView.layoutManager = GridLayoutManager(activity, 2)  // 아이템 세로로 나열
        galleryAdapter = GalleryAdapter(context = requireContext(),
            dataList = GalleryData.getGalleryDataList().filter { it.date == "2024-12-29" }.toMutableList()) { id ->
            val fragment = GalleryDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", id)
                }
            }

            requireActivity().supportFragmentManager.beginTransaction().
                replace(R.id.content_frame, fragment).
                addToBackStack(null).commit()
        }



        recyclerView.adapter = galleryAdapter
        galleryAdapter?.updateData(1)

        cameraButton.setOnClickListener {
            showImageUploadOptions()
        }

        recyclerView1.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView1.adapter = GalleryGroupAdapter(galleryDataList1) { id ->
            galleryAdapter?.updateData(id)
        }
    }

    private fun showImageUploadOptions() {
        val options = arrayOf("사진 촬영", "갤러리에서 업로드")
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setTitle("이미지 업로드")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> checkCameraPermission() // 사진 촬영
                1 -> openGallery()          // 갤러리에서 업로드
            }
        }
        builder.show()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    private fun checkCameraPermission() {
        // 카메라 권한이 부여되었는지 확인
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 없다면 권한 요청
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
            // 권한이 이미 허용되었다면 사진 촬영 진행
            dispatchTakePictureIntent()
        }
    }

    // 카메라로 사진 찍기
    private fun dispatchTakePictureIntent() {
        // 카메라 앱을 실행하기 위한 Intent 생성
//        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile: File? = try {
            createImageFile()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

        photoFile?.let {
            val photoUri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.fileprovider", it)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용되면 사진 촬영 진행
                dispatchTakePictureIntent()
            } else {
                // 권한이 거부되었을 때 처리
                // (예: 사용자에게 권한이 필요하다는 메시지 표시)
            }
        }
    }

    // 찍은 사진 결과 표시
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    imageFilePath?.let {
                        openGalleryUploadFragment(it, "Captured Photo", "Camera Upload")
                    }
                }

                REQUEST_IMAGE_PICK -> {
                    data?.data?.let { uri ->
                        openGalleryUploadFragment(uri.toString(), "Captured Photo", "Camera Upload")
                    }
                }
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("IMG_${timeStamp}_", ".jpg", storageDir).apply {
            imageFilePath = absolutePath
        }
    }

    private fun openGalleryUploadFragment(imagePath: String, title: String, abstract: String) {
        val fragment = GalleryUploadFragment(galleryAdapter).apply {
            arguments = Bundle().apply {
                putString("imagePath", imagePath)
                putString("title", title)
                putString("abstract", abstract)
            }
        }

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun getRealPathFromUri(uri: Uri): String? {
        val cursor: Cursor? = requireContext().contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
            it.moveToFirst()
            it.getString(columnIndex)
        }
    }
}

