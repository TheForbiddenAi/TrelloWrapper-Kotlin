package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.TrelloApi
import me.theforbiddenai.trelloapiwrapper.utils.LimitOptions

class List internal constructor() : TrelloObject() {

    constructor(trelloApi: TrelloApi, name: String, idBoard: String) : this() {
        this.trelloApi = trelloApi
        this.name = name
        this.idBoard = idBoard
    }

    val id: String = ""
    var name: String = ""
    var closed: Boolean = false
    var idBoard: String = ""
    var pos: Float = 0F
    var subscribed: Boolean = false
    var softLimit: Int? = 0
    val limits: ListLimits = ListLimits()

    fun getActions(): Array<Action> {
        val actionsUrl = "${trelloApi.baseApiUrl}/lists/$id/actions?${trelloApi.credentials}"
        return getObjectArray(actionsUrl)
    }

    fun getBoard(): Board {
        val boardUrl = "${trelloApi.baseApiUrl}/lists/$id/board?${trelloApi.credentials}"
        return getObject(boardUrl)
    }

    fun getCards(): Array<Card> {
        val cardsUrl = "${trelloApi.baseApiUrl}/lists/$id/cards?${trelloApi.credentials}"
        return getObjectArray(cardsUrl)
    }

    fun update() {
        if (id.isEmpty()) {
            throw IllegalArgumentException("Failed to find a list with the given id")
        }

        val json = trelloApi.gson.toJson(this)
        val updateListUrl = "${trelloApi.baseApiUrl}/lists/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateListUrl, json)
    }

    fun create(idListSource: String = ""): List {
        var urlParams = "name=$name&idBoard=$idBoard"
        if (idListSource.isNotEmpty()) {
            urlParams = "idListSource=$idListSource&$urlParams"
        }

        val createList = "${trelloApi.baseApiUrl}/lists?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createList)
        val list = trelloApi.gson.fromJson(result, this::class.java)
        list.trelloApi = trelloApi

        return list
    }

    class ListLimits {

        val cards: CardLimits = CardLimits()

        class CardLimits {

            val openPerList: LimitOptions = LimitOptions()
            val totalPerList: LimitOptions = LimitOptions()

        }

    }

}