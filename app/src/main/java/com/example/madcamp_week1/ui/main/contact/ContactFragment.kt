package com.example.madcamp_week1.ui.main.contact

//import android.support.v4.app.Fragment
//import android.support.v4.app.LoaderManager
//import com.example.madcamp_week1.ui.main.contact.PhoneBook
import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class ContactFragment : Fragment() { //, LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    //private lateinit var adapter: ContactViewAdapter
    lateinit var pBooksList: List<PhoneBook>
    private lateinit var fab : FloatingActionButton
    //private lateinit var phoneBooks: List<PhoneBook> = getContacts(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
//        if (ContextCompat.checkSelfPermission(this.requireActivity(),
//                Manifest.permission.READ_CONTACTS)
//            != PackageManager.PERMISSION_GRANTED) {
//
//            // Permission is not granted
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(),
//                    Manifest.permission.READ_CONTACTS)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(this.requireActivity(),
//                    arrayOf(Manifest.permission.READ_CONTACTS),
//                    1) //MY_PERMISSIONS_REQUEST_READ_CONTACTS)
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        } else {
//            // Permission has already been granted
//        }

//        if(ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this.requireActivity() ,
//                arrayOf(Manifest.permission.READ_CONTACTS),
//                1 // MY_PERMISSIONS_REQUEST_PERMISSION
//            )
//        }

        // adapter = ContactViewAdapter(context!!, phonebooks)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.fragment_contact, container, false)
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        recyclerView = view.findViewById(R.id.recycler_contact)
        recyclerView.apply {
            adapter = ContactViewAdapter(context, mutableListOf())
            layoutManager = LinearLayoutManager(context)
        }
        //view.findViewById<TextView>(R.id.section_label).text = "Contacts"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "fuck")
//        recyclerView.apply {
//            adapter = ContactViewAdapter(context, pBooksList)
//            Log.d(TAG, "ssibal")
//        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_get_contact -> {
                Log.d(TAG, "onclick call")
                if (checkLocationPermission()) {
                    pBooksList = getContacts()
                    Log.d("TAG", "${pBooksList.size}")
                    recyclerView.apply {
                        adapter = ContactViewAdapter(context, pBooksList)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //override fun onActivityCreated(savedInstanceState: Bundle?) {
    //    super.onActivityCreated(savedInstanceState)

        // Gets the ListView from the View list of the parent activity
    //    activity?.also {
    //        contactsList = it.findViewById<RecyclerView>(R.id.contact_list)
            // Gets a CursorAdapter
    //        cursorAdapter = SimpleCursorAdapter(
    //            it,
    //            R.layout.contact_item,
    //            null,
    //            FROM_COLUMNS, TO_IDS,
//                0
//            )
//            //contactsList.adapter = cursorAdapter
//        }
//        //contactsList.o
//    }

    fun getContacts(): List<PhoneBook> {
        // 권한 확인
//        val permissionCheck = ContextCompat.checkSelfPermission(
//            this.requireActivity(),
//            Manifest.permission.READ_CONTACTS
//        )

            // 데이터베이스 혹은 content resolver 를 통해 가져온 데이터를 적재할 저장소를 먼저 정의
            val datas: MutableList<PhoneBook> = ArrayList()

            // 1. Resolver 가져오기(데이터베이스 열어주기)
            // 전화번호부에 이미 만들어져 있는 ContentProvider 를 통해 데이터를 가져올 수 있음
            // 다른 앱에 데이터를 제공할 수 있도록 하고 싶으면 ContentProvider 를 설정
            // 핸드폰 기본 앱 들 중 데이터가 존재하는 앱들은 Content Provider 를 갖는다
            // ContentResolver 는 ContentProvider 를 가져오는 통신 수단
            val resolver = requireContext().contentResolver

            // 2. 전화번호가 저장되어 있는 테이블 주소값(Uri)을 가져오기
            val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

            // 3. 테이블에 정의된 칼럼 가져오기
            // ContactsContract.CommonDataKinds.Phone 이 경로에 상수로 칼럼이 정의
            val projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID // 인덱스 값, 중복될 수 있음 -- 한 사람 번호가 여러개인 경우
                , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                , ContactsContract.CommonDataKinds.Phone.NUMBER
            )

            // 4. ContentResolver로 쿼리를 날림 -> resolver 가 provider 에게 쿼리하겠다고 요청
            val cursor =
                resolver.query(phoneUri, projection, null, null, null)

            // 4. 커서로 리턴된다. 반복문을 돌면서 cursor 에 담긴 데이터를 하나씩 추출
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // 4.1 이름으로 인덱스를 찾아준다
                    val idIndex = cursor.getColumnIndex(projection[0]) // 이름을 넣어주면 그 칼럼을 가져와준다.
                    val nameIndex = cursor.getColumnIndex(projection[1])
                    val numberIndex = cursor.getColumnIndex(projection[2])
                    // 4.2 해당 index 를 사용해서 실제 값을 가져온다.
                    val id = cursor.getString(idIndex)
                    val name = cursor.getString(nameIndex)
                    val number = cursor.getString(numberIndex)
                    val phoneBook = PhoneBook()
                    phoneBook.id = id
                    phoneBook.name = name
                    phoneBook.number = number
                    Log.d("TAG", "$id, $name, $number")
                    datas.add(phoneBook)
                }
            }
            // 데이터 계열은 반드시 닫아줘야 한다.
            cursor!!.close()
            return datas
    }

    private fun checkLocationPermission() : Boolean {
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                1//MY_PERMISSIONS_REQUEST_PERMISSION
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    pBooksList = getContacts()
                    recyclerView.apply {
                        adapter = ContactViewAdapter(context, pBooksList)
                        Log.d(TAG, "permission granted!")
                    }
                    } else {
                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Log.d(TAG, "permission not granted")
                    }
                    return
                }

                // Add other 'when' lines to check for other
                // permissions this app might request.
                else -> {
                    // Ignore all other requests.
                    Log.d(TAG, "some other request")
                }
            }
        }

//    private fun jsonParsingExample() {
//        val jsonString = """
//            {
//                "info": [
//                    {
//                        "name": "John Doe",
//                        "contact": "010-1234-5678",
//                        "type": 0
//                    },
//                    {
//                        "name": "Jane"
//                        "contact": "01098765432",
//                        "type": 2
//                    }
//                ]
//            }
//        """.trimIndent()
//
//        val jObject = JSONObject(jsonString)
//        val jArray = jObject.getJSONArray("info")
//
//        for (i in 0 until jArray.length()) {
//            val obj = jArray.getJSONObject(i)
//            val name = obj.getString("name")
//            val contact = obj.getString("contact")
//            val type = obj.getInt("type")
//            Log.d(TAG, "name($i): $name")
//            Log.d(TAG, "contact($i): $contact")
//            Log.d(TAG, "type($i): $type")
//        }
//    }
//
//    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
//        TODO("Not yet implemented")
//    }
//
//    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onLoaderReset(loader: Loader<Cursor>) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        TODO("Not yet implemented")
//    }
}
