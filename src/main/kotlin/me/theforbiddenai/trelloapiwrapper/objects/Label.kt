package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.GsonBuilder
import me.theforbiddenai.trelloapiwrapper.TrelloApi

private val gson = GsonBuilder().serializeNulls().create()

class Label internal constructor() {

    constructor(name: String, color: String) : this() {
        this.name = name
        this.color = color
    }

    @Transient
    internal lateinit var trelloApi: TrelloApi

    val id: String = ""
    val idBoard: String = ""
    var name: String = ""
    var color: String = ""

    // boardId can not be the shortLink it must be the id
    fun createLabel(boardId: String, trelloApi: TrelloApi): Label {
        val urlParams = "name=$name&color=$color&idBoard=$boardId"
        val url = "${trelloApi.baseApiUrl}/labels?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postJsonData(url)
        val label = gson.fromJson(result, this::class.java)
        label.trelloApi = trelloApi

        return label
    }

    fun updateLabel() {
        if (id.isEmpty()) {
            throw IllegalArgumentException("Failed to find a tag with the given id")
        }

        val json = gson.toJson(this)
        val url = "${trelloApi.baseApiUrl}/labels/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putJsonData(url, json)
    }
}