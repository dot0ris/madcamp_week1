package com.example.madcamp_week1.ui.main.gallery

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madcamp_week1.R
import java.io.File
import kotlin.coroutines.coroutineContext

class GalleryViewAdapter(private val context : Context, private val imageList: List<String>)
    : RecyclerView.Adapter<GalleryViewAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView : ImageView = itemView.findViewById<ImageView>(R.id.id_image)

        fun bind(img_path : String){
            Glide.with(context)
                .load(File(img_path))
                .fitCenter()
                .override(300, 200)
                .placeholder(R.drawable.image_load)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

}

