package com.example.pro_fessor.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pro_fessor.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate


@Suppress("DEPRECATION")
class MapFragment : Fragment() {
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private var isMapInitialized = false // 지도 초기화 여부를 체크하기 위한 플래그

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lat : Double = arguments?.getDouble("lat") ?: -1.0 //위도
        val lng : Double = arguments?.getDouble("lng") ?: -1.0 //경도
        val memberId : Int = arguments?.getInt("memberId") ?: -1 //경도

        // 상단바 이름 변경
        view.findViewById<TextView>(R.id.top_bar_text).text = "지도"

        // FusedLocationSource 초기화
        locationSource = FusedLocationSource(this, 1000)

        // Naver MapFragment를 가져오거나 새로 생성
        var mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as? com.naver.maps.map.MapFragment
        if (mapFragment == null) {
            mapFragment = com.naver.maps.map.MapFragment.newInstance()
            childFragmentManager.beginTransaction()
                .add(R.id.mapView, mapFragment)
                .commit()
        }

        // 지도 초기화 로직 (한 번만 실행)
        if (!isMapInitialized) {
            mapFragment?.getMapAsync { naverMap ->
                this.naverMap = naverMap
                this.isMapInitialized = true // 지도 초기화 완료
                setupMap(naverMap)

                if(lat != -1.0 && lng != -1.0 && memberId != -1){
                    val marker = Marker()
                    val memberDataList: List<MemberDto> = MemberData.getPhoneDataList()
                    val member = memberDataList.find { it.memberId == memberId }
                    if (member != null) {
                        setMarker(marker, lat, lng, member)
                    }

                    // 카메라 업데이트: 특정 핀으로 이동
                    naverMap.locationTrackingMode = LocationTrackingMode.None
                    val cameraUpdate = CameraUpdate.scrollTo(marker.position)
                        .animate(CameraAnimation.Easing)
                    naverMap.moveCamera(cameraUpdate)
                }
            }
        }
    }

    private fun setupMap(naverMap: NaverMap) {
        // 지도 설정
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        // UI 설정 (예: 줌 버튼 표시)
        naverMap.uiSettings.isZoomControlEnabled = true
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    //마커 표시
    private fun setMarker(marker: Marker, lat: Double, lng: Double, member: MemberDto) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.map_pin, null)

        //phone component update
        val cvDataList: List<CVDto> = CVData.getCVDataList()
        val cv = cvDataList.find {it.memberId == member.memberId}

        val imageView : ImageView = view.findViewById(R.id.phone_component_image)
        val nameText : TextView = view.findViewById(R.id.phone_component_name)
        val statusText : TextView = view.findViewById(R.id.phone_component_status)

        if(member != null && cv != null){
            imageView.setImageResource(member.imgPath)
            imageView.setBackgroundResource(R.drawable.circle)
            nameText.text = member.name
            statusText.text = cv.qualification
        }

        // 뷰를 Bitmap으로 변환
        val bitmap = createBitmapFromView(view)

        marker.isIconPerspectiveEnabled = true
        //아이콘 지정
        marker.icon = OverlayImage.fromBitmap(bitmap)
        marker.position = LatLng(lat, lng)
        marker.zIndex = 10

        //마커 표시
        marker.map = naverMap

        marker.setOnClickListener {
            callPhoneNumber(member.phone)
            true
        }
    }

    private fun createBitmapFromView(view: View): Bitmap {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun callPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber") // 전화번호를 URI 형식으로 설정
        }
        // Intent 실행
        startActivity(intent)
    }
}