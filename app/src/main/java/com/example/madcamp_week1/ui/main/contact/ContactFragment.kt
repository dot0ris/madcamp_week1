package com.example.madcamp_week1.ui.main.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.madcamp_week1.R
<<<<<<< Updated upstream
=======
import org.json.JSONObject
>>>>>>> Stashed changes

class ContactFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        view.findViewById<TextView>(R.id.section_label).text = "Contacts"

        return view
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream:app/src/main/java/com/example/madcamp_week1/ui/main/ContactFragment.kt
=======
=======
>>>>>>> Stashed changes
    fun jsonParsingExample() {
        val jsonString = """
            {
                "info": [
                    {
                        "name": "John Doe",
                        "contact": "010-1234-5678",
                        "type": 0
                    },
                    {
                        "name": "Jane"
                        "contact": "01098765432",
                        "type": 2
                    }
                ]
            }
        """.trimIndent()

        val jObject = JSONObject(jsonString)
        val jArray = jObject.getJSONArray("info")

        for (i in 0 until jArray.length()) {
            val obj = jArray.getJSONObject(i)
            val name = obj.getString("name")
            val contact = obj.getString("contact")
            val type = obj.getInt("type")
            Log.d(TAG, "name($i): $name")
            Log.d(TAG, "contact($i): $contact")
            Log.d(TAG, "type($i): $type")
        }
    }
<<<<<<< Updated upstream:app/src/main/java/com/example/madcamp_week1/ui/main/contact/ContactFragment.kt
<<<<<<< Updated upstream:app/src/main/java/com/example/madcamp_week1/ui/main/contact/ContactFragment.kt
<<<<<<< Updated upstream
>>>>>>> Stashed changes:app/src/main/java/com/example/madcamp_week1/ui/main/contact/ContactFragment.kt
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes:app/src/main/java/com/example/madcamp_week1/ui/main/ContactFragment.kt
=======
>>>>>>> Stashed changes:app/src/main/java/com/example/madcamp_week1/ui/main/ContactFragment.kt
}