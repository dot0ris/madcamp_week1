package com.example.madcamp_week1.ui.main.gallery

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week1.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class GalleryFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : GalleryViewAdapter
    private var list = mutableListOf<Int>()
    private lateinit var listDir : List<String>
    private var cnt = 1

    /*
    private fun saveImage(drawableId : Int) : String {
        val bitmap = BitmapFactory.decodeResource(resources, drawableId)
        var file = context?.getDir("images", Context.MODE_PRIVATE)
        file = File(file, "image${cnt++}.jpg")
        try{
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        }catch (e: IOException) {
            e.printStackTrace()
        }
        return file.toString()
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var file = context?.getDir("images", Context.MODE_PRIVATE)
        for (i in 1..22){
            val url = "drawable/image"+i.toString().padStart(2, '0')
            list.add(resources.getIdentifier(url, "drawable", activity!!.packageName))
        }
        //listDir = list.map{saveImage(it)}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.apply{
            layoutManager = GridLayoutManager(activity, 2)
            adapter = GalleryViewAdapter(context, list)
        }
        return view
    }

}