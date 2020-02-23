package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.TrelloApi

class Label internal constructor() : TrelloObject() {

    constructor(trelloApi: TrelloApi, name: String, color: String) : this() {
        this.trelloApi = trelloApi
        this.name = name
        this.color = color
    }

    val id: String = ""
    val idBoard: String = ""
    var name: String = ""
    var color: String = ""

    fun update() {
        if (id.isEmpty()) {
            throw IllegalArgumentException("Failed to find a tag with the given id")
        }

        val json = trelloApi.gson.toJson(this)
        val updateLabelUrl = "${trelloApi.baseApiUrl}/labels/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateLabelUrl, json)
    }

    fun create(idBoard: String): Label {
        val urlParams = "name=$name&color=$color&idBoard=$idBoard"
        val createLabelUrl = "${trelloApi.baseApiUrl}/labels?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createLabelUrl)
        val label = trelloApi.gson.fromJson(result, this::class.java)
        label.trelloApi = trelloApi

        return label
    }

    fun delete() {
        if (id.isEmpty()) {
            throw IllegalArgumentException("Failed to find a tag with the given id")
        }

        val deleteLabelUrl = "${trelloApi.baseApiUrl}/labels/$id?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteLabelUrl)

    }
}