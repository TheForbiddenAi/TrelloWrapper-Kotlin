package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.DataObject
import me.theforbiddenai.trelloapiwrapper.utils.ShortMember
import java.util.*

class Action : TrelloObject() {

    val id: String = ""
    val idMemberCreator: String = ""
    val data: DataObject = DataObject()
    val type: String = ""
    val date: Date? = null
    val display: Display = Display()
    val memberCreator: ShortMember = ShortMember()

    fun getBoard(): Board {
        val boardUrl = "${trelloApi.baseApiUrl}/actions/$id/board?${trelloApi.credentials}"
        return getTrelloObject(boardUrl)
    }

    fun getCard(): Card {
        val cardUrl = "${trelloApi.baseApiUrl}/actions/$id/card?${trelloApi.credentials}"
        return getTrelloObject(cardUrl)
    }

    fun getList(): List {
        val listUrl = "${trelloApi.baseApiUrl}/actions/$id/list?${trelloApi.credentials}"
        return getTrelloObject(listUrl)
    }

    fun getMember(): Member {
        val listUrl = "${trelloApi.baseApiUrl}/actions/$id/member?${trelloApi.credentials}"
        return getTrelloObject(listUrl)
    }

    fun getMemberCreator(): Member {
        val listUrl = "${trelloApi.baseApiUrl}/actions/$id/member?${trelloApi.credentials}"
        return getTrelloObject(listUrl)
    }

    fun getOrganization(): Organization {
        val listUrl = "${trelloApi.baseApiUrl}/actions/$id/organization?${trelloApi.credentials}"
        return getTrelloObject(listUrl)
    }

    fun updateComment(commentText: String) {
        val json = trelloApi.gson.toJson(this)

        val urlParams = "text=$commentText"
        val updateActionUrl = "${trelloApi.baseApiUrl}/actions/$id?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateActionUrl, json)
    }

    fun deleteComment() {
        val updateActionUrl = "${trelloApi.baseApiUrl}/actions/$id?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(updateActionUrl)
    }


    class Display {

        val translationKey: String = ""
        val entities: Entities = Entities()

        class Entities {

            val board: LimitDisplayView = LimitDisplayView()
            val memberCreator: LimitDisplayView = LimitDisplayView()
            val card: LimitDisplayView = LimitDisplayView()
            val cardSource: LimitDisplayView = LimitDisplayView()
            val comment: LimitDisplayView = LimitDisplayView()
            val list: LimitDisplayView = LimitDisplayView()
            val listBefore: LimitDisplayView = LimitDisplayView()
            val listAfter: LimitDisplayView = LimitDisplayView()
            val customField: LimitDisplayView = LimitDisplayView()
            val customFieldItem: LimitDisplayView = LimitDisplayView()
            val checkitem: LimitDisplayView = LimitDisplayView()
            val checklist: LimitDisplayView = LimitDisplayView()
            val plugin: LimitDisplayView = LimitDisplayView()
            val attachment: LimitDisplayView = LimitDisplayView()
            val name: LimitDisplayView = LimitDisplayView()

            class LimitDisplayView {
                val type: String = ""
                val id: String = ""
                val text: String = ""
            }
        }
    }

}