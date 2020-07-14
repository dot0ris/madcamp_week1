package com.example.madcamp_week1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.madcamp_week1.ui.main.SectionsPagerAdapter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import kotlin.system.exitProcess

val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

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
        ab.setDisplayShowTitleEnabled(true)
        ab.setTitle(TAB_TITLES[0])

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageSelected(position: Int) {
                ab.setTitle(TAB_TITLES[position])
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                invalidateOptionsMenu()
            }
        })
        viewPager.offscreenPageLimit = 3

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
//        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_settings_24)
//        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_settings_24)
//        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_settings_24)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            MY_PERMISSIONS_REQUEST_PERMISSION -> {
//                // If request is cancelled, the result arrays are empty.
//                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                } else {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return
//            }
//
//            // Add other 'when' lines to check for other
//            // permissions this app might request.
//            else -> {
//                // Ignore all other requests.
//            }
//        }
//    }
}