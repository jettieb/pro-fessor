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
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_fessor.mission.MissionAdapter
import com.example.pro_fessor.mission.MissionCompleteFragment
import com.example.pro_fessor.sampledata.CVDto
import com.example.pro_fessor.sampledata.MemberData
import com.example.pro_fessor.sampledata.MemberDto
import com.example.pro_fessor.sampledata.MissionData
import com.example.pro_fessor.sampledata.MissionDto
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import java.lang.reflect.Member


@Suppress("DEPRECATION")
class MapFragment : Fragment() {
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private var isMapInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchButton = view.findViewById<ImageView>(R.id.top_bar_search)
        searchButton.visibility = View.GONE

        val lat = arguments?.getDouble("lat") ?: -1.0
        val lng = arguments?.getDouble("lng") ?: -1.0
        val memberId = arguments?.getInt("memberId") ?: -1

        val memberDataList = MemberData.getPhoneDataList()

        view.findViewById<TextView>(R.id.top_bar_text).text = "지도"

        locationSource = FusedLocationSource(this, 1000)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as? com.naver.maps.map.MapFragment
            ?: com.naver.maps.map.MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.mapView, it).commit()
            }

        if (!isMapInitialized) {
            mapFragment.getMapAsync { map ->
                naverMap = map
                isMapInitialized = true
                setupMap()
                setupMarkers(memberDataList)
                setupRecyclerView(view, memberDataList)

                if (lat != -1.0 && lng != -1.0 && memberId != -1) {
                    moveToLocation(lat, lng, memberDataList.find { it.memberId == memberId })
                }
            }
        }
    }

    private fun setupMap() {
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.uiSettings.isZoomControlEnabled = true
    }

    private fun setupMarkers(memberDataList: List<MemberDto>) {
        memberDataList.forEach { member ->
            val marker = Marker().apply { setupMarker(this, member) }
        }
    }

    private fun setupRecyclerView(view: View, memberDataList: List<MemberDto>) {
        val recyclerView: RecyclerView = view.findViewById(R.id.map_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = MapAdapter(memberDataList) { id ->
            val member = memberDataList.find { it.memberId == id }
            member?.let { moveToLocation(it.lat, it.lng, it) }
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                updateCameraForVisibleItem(recyclerView, memberDataList)
            }
        })
    }

    private fun updateCameraForVisibleItem(recyclerView: RecyclerView, memberDataList: List<MemberDto>) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val recyclerViewCenter = recyclerView.width / 2

        val closestItem = (layoutManager.findFirstVisibleItemPosition()..layoutManager.findLastVisibleItemPosition())
            .mapNotNull { position ->
                recyclerView.findViewHolderForAdapterPosition(position)?.itemView?.let { view ->
                    val itemCenter = (view.left + view.right) / 2
                    val distance = kotlin.math.abs(itemCenter - recyclerViewCenter)
                    position to distance
                }
            }
            .minByOrNull { it.second }?.first

        closestItem?.let { position ->
            memberDataList.getOrNull(position)?.let {
                moveToLocation(it.lat, it.lng, it)
            }
        }
    }

    private fun moveToLocation(lat: Double, lng: Double, member: MemberDto?) {
        member?.let {
            val marker = Marker().apply { setupMarker(this, member) }
            val cameraUpdate = CameraUpdate.scrollTo(marker.position).animate(CameraAnimation.Easing)
            naverMap.moveCamera(cameraUpdate)
            naverMap.locationTrackingMode = LocationTrackingMode.None
        }
    }

    private fun setupMarker(marker: Marker, member: MemberDto) {
        val markerView = LayoutInflater.from(context).inflate(R.layout.map_pin, null).apply {
            findViewById<ImageView>(R.id.phone_component_image).apply {
                setImageResource(member.imgPath)
                setBackgroundResource(R.drawable.circle)
            }
            findViewById<TextView>(R.id.phone_component_name).text = member.name
            findViewById<TextView>(R.id.phone_component_status).text = CVData.getCVDataList()
                .find { it.memberId == member.memberId }?.qualification
            findViewById<CardView>(R.id.phone).radius = 50f

        }

        marker.apply {
            icon = OverlayImage.fromBitmap(createBitmapFromView(markerView))
            position = LatLng(member.lat, member.lng)
            zIndex = 10
            isIconPerspectiveEnabled = true
            map = naverMap

            setOnClickListener {
                callPhoneNumber(member.phone)
                true
            }
        }
    }

    private fun createBitmapFromView(view: View): Bitmap {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        return Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888).apply {
            Canvas(this).also { view.draw(it) }
        }
    }

    private fun callPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}
