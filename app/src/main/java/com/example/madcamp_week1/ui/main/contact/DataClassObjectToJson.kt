package com.example.madcamp_week1.ui.main.contact

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import com.example.madcamp_week1.ui.main.contact.ConvertJsonFile

fun main(args: Array<String>) {
    val gson = GsonBuilder().setPrettyPrinting().create()

    // this variables will be parsed from app
    var name = "Timmy"
    var contact = "010-5555-5555"
    var type = 1

    val dataObj = ConvertJsonFile(name, contact, type)

    val jsonDataObj: String = gson.toJson(dataObj)

}