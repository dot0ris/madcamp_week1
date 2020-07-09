package com.example.madcamp_week1.ui.main.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week1.R
import java.io.File

class GalleryFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : GalleryViewAdapter
    private var list = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = mutableListOf<Int>(R.drawable.image01, R.drawable.image02, R.drawable.image03)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.apply{
            layoutManager = GridLayoutManager(activity, 2)
            adapter =
                GalleryViewAdapter(list)
        }
        return view
    }
}