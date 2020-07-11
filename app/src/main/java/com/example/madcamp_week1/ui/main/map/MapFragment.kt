package com.example.madcamp_week1.ui.main.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.madcamp_week1.R
import net.daum.mf.map.api.MapView

class MapFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        val mapView = MapView(context)
        val mapViewContainer = view.findViewById<ViewGroup>(R.id.map_view)
        mapViewContainer.addView(mapView)
        return view
    }
}