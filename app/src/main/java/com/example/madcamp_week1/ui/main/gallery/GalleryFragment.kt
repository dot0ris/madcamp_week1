package com.example.madcamp_week1.ui.main.gallery

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week1.R
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class GalleryFragment : Fragment(){
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : GalleryViewAdapter
    private lateinit var img_paths : MutableList<String>
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
        setHasOptionsMenu(true)
        img_paths = mutableListOf<String>()

        val imgDir = File(context!!.filesDir, "gallery")
        imgDir.list()?.forEach{
            img_paths.add(imgDir.toString() + File.separator + it)
            Log.d("ImgPath", img_paths[img_paths.size-1])
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.apply{
            layoutManager = GridLayoutManager(activity, 2)
            adapter = GalleryViewAdapter(context, img_paths)
        }
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_gallery1 -> Snackbar.make(view!!, "Gallery Option 1", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            R.id.action_gallery2 -> Snackbar.make(view!!, "Gallery Option 2", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        return super.onOptionsItemSelected(item)
    }

}