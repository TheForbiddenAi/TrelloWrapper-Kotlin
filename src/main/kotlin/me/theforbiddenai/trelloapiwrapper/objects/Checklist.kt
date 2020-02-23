package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.GsonBuilder
import me.theforbiddenai.trelloapiwrapper.TrelloApi

private val gson = GsonBuilder().serializeNulls().create()

class Checklist {

    internal lateinit var trelloApi: TrelloApi

    val id: String = ""
    val name: String = ""
    val idBoard: String = ""
    val idCard: String = ""
    val pos: Float = 0F
    val checkItems: Array<CheckItems> = arrayOf()

    class CheckItems {
        val idChecklist: String = ""
        val state: String = ""
        val id: String = ""
        val name: String = ""
    }
}