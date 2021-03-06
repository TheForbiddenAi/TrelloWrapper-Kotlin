package me.theforbiddenai.trellowrapperkotlin.objects

import me.theforbiddenai.trellowrapperkotlin.TrelloApi
import me.theforbiddenai.trellowrapperkotlin.utils.LimitOptions

class TrelloList internal constructor() : TrelloObject() {

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
        return getTrelloObjectArray(actionsUrl)
    }

    fun getBoard(): Board {
        val boardUrl = "${trelloApi.baseApiUrl}/lists/$id/board?${trelloApi.credentials}"
        return getTrelloObject(boardUrl)
    }

    fun getCards(): Array<Card> {
        val cardsUrl = "${trelloApi.baseApiUrl}/lists/$id/cards?${trelloApi.credentials}"
        return getTrelloObjectArray(cardsUrl)
    }

    fun updateList() {
        val json = trelloApi.gson.toJson(this)
        val updateListUrl = "${trelloApi.baseApiUrl}/lists/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateListUrl, json)
    }

    fun setSoftLimit(softLimit: Int) {
        val updateListUrl = "${trelloApi.baseApiUrl}/lists/$id/softLimit?value=$softLimit&${trelloApi.credentials}"
        trelloApi.httpRequests.putRequest(updateListUrl)
    }

    fun createList(idListSource: String = ""): TrelloList {
        var urlParams = "name=$name&idBoard=$idBoard"
        if (idListSource.isNotEmpty()) {
            urlParams = "idListSource=$idListSource&$urlParams"
        }

        val createList = "${trelloApi.baseApiUrl}/lists?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createList)
        return createObjectFromJson(result)
    }

    fun archiveAllCards() {
        val archiveCardsUrl = "${trelloApi.baseApiUrl}/lists/$id/archiveAllCards?${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(archiveCardsUrl)
    }

    /**
     * Moves all cards to a given list on a given board
     *
     * @param idList The id of the list that the cards are being moved to
     * @param idBoard The id of the board that the list the cards are being moved to are on
     */
    fun moveAllCards(idList: String, idBoard: String = this.idBoard) {
        val urlParams = "idBoard=$idBoard&idList=$idList"

        val moveCardsUrl = "${trelloApi.baseApiUrl}/lists/$id/moveAllCards?$urlParams&${trelloApi.credentials}"
        println(moveCardsUrl)
        trelloApi.httpRequests.postRequest(moveCardsUrl)
    }

    class ListLimits {
        val cards: CardLimits = CardLimits()

        class CardLimits {
            val openPerList: LimitOptions = LimitOptions()
            val totalPerList: LimitOptions = LimitOptions()
        }

    }

}