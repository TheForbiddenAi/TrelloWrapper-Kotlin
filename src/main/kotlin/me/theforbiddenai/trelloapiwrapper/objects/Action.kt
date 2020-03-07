package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.JsonObject
import me.theforbiddenai.trelloapiwrapper.utils.DataObject
import me.theforbiddenai.trelloapiwrapper.utils.ShortMember
import java.util.*

class Action internal constructor() : TrelloObject() {

    val id: String = ""
    val idMemberCreator: String = ""
    val data: DataObject =
        DataObject()
    val type: String = ""
    val date: Date? = null
    val display: Display = Display()
    val memberCreator: ShortMember =
        ShortMember()

    fun getBoard(): Board {
        val boardUrl = "${trelloApi.baseApiUrl}/actions/$id/board?${trelloApi.credentials}"
        return getTrelloObject(boardUrl)
    }

    fun getCard(): Card {
        val cardUrl = "${trelloApi.baseApiUrl}/actions/$id/card?${trelloApi.credentials}"
        return getTrelloObject(cardUrl)
    }

    fun getList(): TrelloList {
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

    fun getReactions(): Array<Reaction> {
        val reactionsUrl = "${trelloApi.baseApiUrl}/actions/$id/reactions?${trelloApi.credentials}"
        return getTrelloObjectArray(reactionsUrl)
    }

    fun getReactionById(reactionId: String): Reaction {
        val reactionUrl = "${trelloApi.baseApiUrl}/actions/$id/reactions/$reactionId?${trelloApi.credentials}"
        return getTrelloObject(reactionUrl)
    }

    fun updateComment(commentText: String) {
        val json = trelloApi.gson.toJson(this)

        val urlParams = "text=$commentText"
        val updateActionUrl = "${trelloApi.baseApiUrl}/actions/$id?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateActionUrl, json)
    }

    fun addReaction(shortName: String, skinVariation: String, native: String, unified: String): Reaction {
        val jsonObject = JsonObject()
        if (shortName.isNotEmpty()) jsonObject.addProperty("shortName", shortName)
        if (skinVariation.isNotEmpty()) jsonObject.addProperty("skinVariation", skinVariation)
        if (native.isNotEmpty()) jsonObject.addProperty("native", native)
        if (unified.isNotEmpty()) jsonObject.addProperty("unified", unified)

        val addReactionUrl = "${trelloApi.baseApiUrl}/actions/$id/reactions?${trelloApi.credentials}"

        val jsonString = jsonObject.toString()
        val result = trelloApi.httpRequests.postRequest(addReactionUrl, jsonString)

        return createObjectFromJson(result)
    }

    fun removeReaction(reactionId: String) {
        val removeReactionUrl = "${trelloApi.baseApiUrl}/actions/$id/reactions/$reactionId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeReactionUrl)
    }

    fun deleteComment() {
        val deleteActionUrl = "${trelloApi.baseApiUrl}/actions/$id?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteActionUrl)
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