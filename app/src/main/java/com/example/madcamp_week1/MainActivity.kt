package com.example.madcamp_week1

//import android.R
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
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

class MainActivity : AppCompatActivity() {
    val MY_PERMISSIONS_REQUEST_PERMISSION = 1
    // loads contacts list in phone
    //private fun makeRequest() {
    //    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
    //        == PackageManager.PERMISSION_GRANTED) {
    //        mArray = getAllContacts()
    //    } else {
    //        requestPermission()
    //    }
    //}

    //private fun requestPermission() {

    //}

    //private fun getAllContacts() {
    //    var nameList : ArrayList<String> = ArrayList<String>()
    //    var cr : ContentResolver = getContentResolver()
    //    var cur : Cursor? = cr.query(ContactsContract.Contacts.CONTENT_URI,
    //    null, null, null, null)
    //    if ((cur != null ? cur.getCount() : 0) > 0) {
    //        while (cur != null and cur.moveToNext()) {

    //        }
    //    }
    //}

    //private fun makeRequest() {
    //    if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
    //        ActivityCompat.requestPermissions(this@MainActivity,
    //            arrayOf(Manifest.permission.REAM_PHONE_STATE),
    //            SyncStateContract.Constants.RECORD_REQUEST_CODE)
    //    }
    //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        //val fab: FloatingActionButton = findViewById(R.id.fab)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.READ_CONTACTS),
//                1 // MY_PERMISSIONS_REQUEST_PERMISSION
//            )
//        }

//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.WRITE_CONTACTS),
//                2 // MY_PERMISSIONS_REQUEST_PERMISSION
//            )
//        }
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