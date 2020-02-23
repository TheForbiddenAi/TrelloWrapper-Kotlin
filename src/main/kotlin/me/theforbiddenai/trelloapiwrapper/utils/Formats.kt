package me.theforbiddenai.trelloapiwrapper.utils

import com.google.gson.GsonBuilder

private val gson = GsonBuilder().serializeNulls().create()

class LimitOptions {

    val status: String = ""
    val disableAt: Int = 0
    val warnAt: Int = 0

}

class DescData {

    val emoji = gson.fromJson("", mapOf<String, String>()::class.java)

}