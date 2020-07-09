package com.example.madcamp_week1.ui.main.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week1.R

class GalleryViewAdapter(private val imageList: List<Int>)
    : RecyclerView.Adapter<GalleryViewAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView : TextView = itemView.findViewById<TextView>(R.id.id_listitem)

        fun bind(v : Int){
            textView.text = v.toString()
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