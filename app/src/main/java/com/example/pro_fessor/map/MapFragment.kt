package com.example.pro_fessor.map

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
        val lng : Double = arguments?.getDouble("lng") ?: -1.0 //

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

                if(lat != -1.0 && lng != -1.0){
                    val marker = Marker()
                    setMarker(marker, lat, lng);
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
    private fun setMarker(marker: Marker, lat: Double, lng: Double) {
        marker.isIconPerspectiveEnabled = true
        //아이콘 지정
        marker.icon = OverlayImage.fromResource(R.drawable.map_pin)
        marker.position = LatLng(lat, lng)
        marker.zIndex = 10
        //마커 표시
        marker.map = naverMap
    }
}