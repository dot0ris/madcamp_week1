package com.example.madcamp_week1.ui.main.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madcamp_week1.R
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapPoint.GeoCoordinate
import net.daum.mf.map.api.MapView
import java.lang.String

class MapFragment : Fragment(), MapView.CurrentLocationEventListener {
    private val TAG = "MapTAG"

    private lateinit var mapViewContainer : ViewGroup
    private lateinit var mapView : MapView
    private lateinit var currentMapPoint : MapPoint
    private var isTrackingMode = true
    private var currentLng: Double? = null
    private var currentLat: Double? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = MapView(context)
        mapViewContainer = view.findViewById<ViewGroup>(R.id.map_view)
        mapViewContainer.addView(mapView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        mapView.setCurrentLocationEventListener(this)
    }


    override fun onCurrentLocationUpdate(mapView: MapView?, mapPoint: MapPoint?, accuracyInMeters: Float) {
        val mapPointGeo: GeoCoordinate = mapPoint!!.mapPointGeoCoord
        Log.i(
            TAG,
            String.format(
                "MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)",
                mapPointGeo.latitude,
                mapPointGeo.longitude,
                accuracyInMeters
            )
        )
        currentMapPoint = MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude)
        //이 좌표로 지도 중심 이동
        //이 좌표로 지도 중심 이동
        mapView!!.setMapCenterPoint(currentMapPoint, true)
        //전역변수로 현재 좌표 저장
        //전역변수로 현재 좌표 저장
        currentLat = mapPointGeo.latitude
        currentLng = mapPointGeo.longitude
        Log.d(TAG, "현재위치 => " + currentLat.toString() + "  " + currentLng)
        //loaderLayout.setVisibility(View.GONE)
        //트래킹 모드가 아닌 단순 현재위치 업데이트일 경우, 한번만 위치 업데이트하고 트래킹을 중단시키기 위한 로직
        //트래킹 모드가 아닌 단순 현재위치 업데이트일 경우, 한번만 위치 업데이트하고 트래킹을 중단시키기 위한 로직
        if (!isTrackingMode) {
            mapView!!.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
        }
    }

    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {

    }

    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {
        Log.i(TAG, "onCurrentLocationUpdateCancelled")
    }

    override fun onCurrentLocationUpdateFailed(p0: MapView?) {
        Log.i(TAG, "onCurrentLocationUpdateFailed")
    }



}