package com.example.madcamp_week1.ui.main.map

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.madcamp_week1.R
import com.example.madcamp_week1.model.PlaceInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapPoint.GeoCoordinate
import net.daum.mf.map.api.MapView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.String
import java.nio.charset.Charset


class MapFragment : Fragment(), MapView.CurrentLocationEventListener, MapView.POIItemEventListener, View.OnClickListener {
    private val TAG = "MapTAG"

    private lateinit var mapViewContainer : ViewGroup
    private lateinit var mapView : MapView
    private lateinit var fab : FloatingActionButton
    private lateinit var currentMapPoint : MapPoint
    private var isTrackingMode = false
    private var currentLng: Double? = null
    private var currentLat: Double? = null
    private val toilets = mutableListOf<PlaceInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseCsv("csv/대전광역시_유성구_공중화장실_20200313.csv")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = MapView(context)
        mapViewContainer = view.findViewById<ViewGroup>(R.id.map_view)
        mapViewContainer.addView(mapView)
        fab = view.findViewById(R.id.fab_location)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        mapView.setCurrentLocationEventListener(this)

        val toiletPOIs = toilets.map{x -> x.toMapPOIItem()}
        mapView.addPOIItems(toiletPOIs.toTypedArray())
        mapView.setPOIItemEventListener(this)

        fab.setOnClickListener(this)
    }

    private fun parseCsv(srcFile: kotlin.String) {
        val assetManager = context!!.assets
        try{
            val input = InputStreamReader(assetManager.open(srcFile), Charset.forName("euc-kr"))
            val reader = BufferedReader(input)
            reader.readLine()
            var line: kotlin.String?
            while (reader.readLine().also { line = it } != null) {
                val args = line!!.split(",")
                val name = args[1]
                val x = args[18].toDouble()
                val y = args[19].toDouble()
                val address = args[3]
                val phone = args[15]
                val hours = args[16]
                //Log.d(TAG, "$name $x $y")
                val place = PlaceInfo(name, x, y, address, phone, hours)
                toilets.add(place)
            }
            Log.d(TAG, "finished")
        }catch(e: Exception){
            Log.d(TAG, e.toString())
        }
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

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {

    }

    override fun onCalloutBalloonOfPOIItemTouched(
        mapView: MapView?,
        mapPOIItem: MapPOIItem?,
        calloutBalloonButtonType: MapPOIItem.CalloutBalloonButtonType
    ) {
        val lat: Double = mapPOIItem!!.mapPoint.mapPointGeoCoord.latitude
        val lng: Double = mapPOIItem.mapPoint.mapPointGeoCoord.longitude
        Toast.makeText(context, mapPOIItem.itemName, Toast.LENGTH_SHORT).show()

    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {

    }

    override fun onPOIItemSelected(mapView: MapView?, mapPOIItem: MapPOIItem?) {

    }

    override fun onClick(view: View?) {
        when(view!!.id) {
            R.id.fab_location -> {
                isTrackingMode = !isTrackingMode
                if(isTrackingMode) mapView!!.setMapCenterPoint(currentMapPoint, true)
            }
        }

    }


}