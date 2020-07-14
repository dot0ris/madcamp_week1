package com.example.madcamp_week1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
//import android.R
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.provider.ContactsContract
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.madcamp_week1.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.madcamp_week1.ui.main.SectionsPagerAdapter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private val MY_PERMISSIONS_REQUEST_PERMISSION = 1
    private val TAG = "mainTAG"

    private fun writeImagesToStorage() {
        Log.d("Gallery", ">> writeImagesToStorage")
        val assetManager = assets
//        if (File(filesDir.absolutePath + File.separator + "images").exists()){
//            Log.d("Gallery", "images directory already exists")
//            return
//        }
        val images = assetManager.list("gallery")
        if (images != null) {
            //Log.d("Gallery", "${imagePaths.get(1)}")
            val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            for(image in images){
                //Log.d("Gallery", "$image")
                try{
                    val srcFile = "gallery" + File.separator +"$image"
                    val input = assetManager.open(srcFile)
                    val output = FileOutputStream(File(dir, image))
                    input.copyTo(output)

                }catch(e: IOException){
                    Log.d("Gallery", "IO Exception")
                }catch(e: FileNotFoundException){
                    Log.d("Gallery", "File Not Found Exception")
                }catch(e: Exception){
                    e.printStackTrace()
                    exitProcess(0)
                }
            }
        }

        Log.d("Gallery", "<< writeImagesToStorage")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageSelected(position: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                invalidateOptionsMenu()
            }
        })
        viewPager.offscreenPageLimit = 3

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        writeImagesToStorage()
    }

    // Adjust visibility of each options in menu_main
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        when(findViewById<ViewPager>(R.id.view_pager).currentItem){
            0 -> {
                menu!!.setGroupVisible(R.id.menu_group_contact, true)
                menu!!.setGroupVisible(R.id.menu_group_gallery, false)
                menu!!.setGroupVisible(R.id.menu_group_tab3, false)

            }
            1 -> {
                menu!!.setGroupVisible(R.id.menu_group_contact, false)
                menu!!.setGroupVisible(R.id.menu_group_gallery, true)
                menu!!.setGroupVisible(R.id.menu_group_tab3, false)
            }
            2 -> {
                menu!!.setGroupVisible(R.id.menu_group_contact, false)
                menu!!.setGroupVisible(R.id.menu_group_gallery, false)
                menu!!.setGroupVisible(R.id.menu_group_tab3, true)
            }
        }

        return super.onCreateOptionsMenu(menu)
    }
}