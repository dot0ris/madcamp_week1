package com.example.madcamp_week1.ui.main.contact

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.system.Os.bind
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.example.madcamp_week1.R
import java.io.File
import kotlin.coroutines.coroutineContext

//class ContactViewAdapter(private val items: ArrayList<PhoneBook>) :
class ContactViewAdapter(private val context: Context, val phonebookList: List<PhoneBook>) :
    RecyclerView.Adapter<ContactViewAdapter.ViewHolder>() {

    //override fun getItemCount() = phonebook.size

    override fun onBindViewHolder(holder: ContactViewAdapter.ViewHolder, position: Int) {
        val item = phonebookList[position]
//        val listener = View.OnClickListener {it ->
//            Toast.makeText(it.context, "name: ${item.name}", Toast.LENGTH_SHORT).show()
//        }
        holder.apply {
            bind(item)
            //itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ContactViewAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ViewHolder(inflatedView)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        //private var view: View = v
        //fun bind(listener: View.OnClickListener, item: PhoneBook) {
        //    view.thumbnail.setImageDrawable(item.image)
        //    view. title.text = item.title
        //    view.setOnClickListener(listener)
        //}
        //val id = v?.findViewById<TextView>(R.id.id)
        val name = v?.findViewById<TextView>(R.id.name)
        val number = v?.findViewById<TextView>(R.id.number)

        fun bind (phoneBook: PhoneBook) {
            //id?.text = phoneBook.id
            name?.text = phoneBook.name
            number?.text = phoneBook.number
        }
    }

    override fun getItemCount(): Int {
        return phonebookList.size
    }
}