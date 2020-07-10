package com.example.madcamp_week1.ui.main.contact

import java.io.FileReader
import com.google.gson.Gson

import com.example.madcamp_week1.ui.main.contact.ConvertJsonFile

fun main(args: Array<String>) {
    // sample json data, json will be replaced with read data.
    val json = """
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
    """
    val gson = Gson()

    vla rawJsonData =
    val toDataClass: ConvertJsonFile = gson.fromJson(FileReader("contacts_list.json"), ConvertJsonFile::class.java)

}