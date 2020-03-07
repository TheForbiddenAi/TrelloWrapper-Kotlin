package me.theforbiddenai.trellowrapperkotlin.objects

import me.theforbiddenai.trellowrapperkotlin.TrelloApi

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

    fun updateLabel() {
        val json = trelloApi.gson.toJson(this)
        val updateLabelUrl = "${trelloApi.baseApiUrl}/labels/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateLabelUrl, json)
    }

    fun createLabel(idBoard: String): Label {
        val urlParams = "name=$name&color=$color&idBoard=$idBoard"
        val createLabelUrl = "${trelloApi.baseApiUrl}/labels?$urlParams&${trelloApi.credentials}"
        val result = trelloApi.httpRequests.postRequest(createLabelUrl)

        return createObjectFromJson(result)
    }

    fun deleteLabel() {
        val deleteLabelUrl = "${trelloApi.baseApiUrl}/labels/$id?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteLabelUrl)

    }
}