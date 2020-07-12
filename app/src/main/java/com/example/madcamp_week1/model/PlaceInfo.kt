package com.example.madcamp_week1.model

import android.util.Log
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint

class PlaceInfo (
    private val placeName : String,
    private val x: Double,
    private val y: Double
){
    fun toMapPOIItem() : MapPOIItem{
        val marker = MapPOIItem()
        marker.itemName = placeName
        Log.d("TAG", placeName)
        val mapPoint = MapPoint.mapPointWithGeoCoord(x, y)
        marker.mapPoint = mapPoint
        return marker
    }
}