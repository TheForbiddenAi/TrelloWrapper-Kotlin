package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.TrelloApi
import me.theforbiddenai.trelloapiwrapper.utils.DescData
import java.util.*

class Checklist internal constructor() : TrelloObject() {

    constructor(trelloApi: TrelloApi, cardId: String, name: String = "", pos: Float = 0F) : this() {
        this.trelloApi = trelloApi
        this.idCard = cardId
        this.name = name
        this.pos = pos
    }

    val id: String = ""
    var name: String = ""
    val idBoard: String = ""
    var idCard: String = ""
    var pos: Float = 0F
    val checkItems: Array<CheckItem> = arrayOf()

    fun getBoard(): Board {
        val boardUrl = "${trelloApi.baseApiUrl}/checklists/$id/board?${trelloApi.credentials}"
        return getTrelloObject(boardUrl)
    }

    fun getCards(): Array<Card> {
        val cardsUrl = "${trelloApi.baseApiUrl}/checklists/$id/cards?${trelloApi.credentials}"
        return getTrelloObjectArray(cardsUrl)
    }

    fun getCheckItemById(checkItemId: String): CheckItem {
        val cardsUrl = "${trelloApi.baseApiUrl}/checklists/$id/checkItems/$checkItemId?${trelloApi.credentials}"
        return getTrelloObject(cardsUrl)
    }

    fun updateChecklist() {
        val json = trelloApi.gson.toJson(this)
        val updateChecklistUrl = "${trelloApi.baseApiUrl}/checklists/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateChecklistUrl, json)
    }

    fun createChecklist(checklistSourceId: String = ""): Checklist {
        val checklistJson = trelloApi.gson.toJson(this)
        val jsonString = checklistJson.toString()

        val urlParams = if (checklistSourceId.isNotEmpty()) "idChecklistSource=$checklistSourceId&" else ""
        val createChecklistUrl = "${trelloApi.baseApiUrl}/checklists?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createChecklistUrl, jsonString)
        return createObjectFromJson(result)
    }

    fun createCheckItem(name: String, checked: Boolean = false, pos: String = ""): CheckItem {
        var urlParams = "name=$name&checked=$checked"
        if (pos.isNotEmpty()) urlParams += "&pos=$pos"

        val createCheckItemUrl = "${trelloApi.baseApiUrl}/checklists/$id/checkItems?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createCheckItemUrl)
        return createObjectFromJson(result)
    }

    fun deleteChecklist() {
        val deleteChecklistUrl = "${trelloApi.baseApiUrl}/checklists/$id?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteChecklistUrl)
    }

    fun deleteCheckItem(checkItemId: String) {
        val deleteCheckItemUrl =
            "${trelloApi.baseApiUrl}/checklists/$id/checkItems/$checkItemId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteCheckItemUrl)
    }

    class CheckItem {
        val idChecklist: String = ""
        val state: String = ""
        val idMember: String = ""
        val id: String = ""
        val name: String = ""
        val nameData: DescData =
            DescData()
        val type: String = ""
        val pos: Float = 0F
        val due: Date? = null
    }
}