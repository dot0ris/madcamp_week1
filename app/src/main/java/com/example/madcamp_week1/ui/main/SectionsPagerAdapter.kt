package com.example.madcamp_week1.ui.main

import android.content.Context
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.madcamp_week1.R
import com.example.madcamp_week1.ui.main.gallery.GalleryFragment
import com.example.madcamp_week1.ui.main.map.MapFragment
import com.example.madcamp_week1.ui.main.contact.ContactFragment

private val TAB_IMAGES = arrayOf(
    R.drawable.ic_baseline_contacts_24,
    R.drawable.ic_baseline_image_24,
    R.drawable.ic_baseline_map_24
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        // return PlaceholderFragment.newInstance(position + 1)
        return when(position) {
            0 -> ContactFragment()
            1 -> GalleryFragment()
            2 -> MapFragment()
            else -> Fragment()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getPageTitle(position: Int): CharSequence? {
        val image = context.resources.getDrawable(TAB_IMAGES[position], null)
        image.setBounds(0, 0, image.intrinsicWidth, image.intrinsicHeight)
        val sb = SpannableString(" ")
        val imageSpan = ImageSpan(image, ImageSpan.ALIGN_BOTTOM)
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return sb
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}