package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.DataObject
import me.theforbiddenai.trelloapiwrapper.utils.ShortMember
import java.util.*

class Notification internal constructor() : TrelloObject() {

    val id: String = ""
    val data: DataObject =
        DataObject()
    val date: Date? = null
    val idMemberCreator: String = ""
    val idAction: String = ""
    val type: String = ""
    var unread: Boolean = false
    val memberCreator: ShortMember =
        ShortMember()

    fun getBoard(): Board {
        val boardUrl = "${trelloApi.baseApiUrl}/notifications/$id/board?${trelloApi.credentials}"
        return getTrelloObject(boardUrl)
    }

    fun getCard(): Card {
        val cardUrl = "${trelloApi.baseApiUrl}/notifications/$id/card?${trelloApi.credentials}"
        return getTrelloObject(cardUrl)
    }

    fun getList(): TrelloList {
        val listUrl = "${trelloApi.baseApiUrl}/notifications/$id/list?${trelloApi.credentials}"
        return getTrelloObject(listUrl)
    }

    fun getMember(): Member {
        val memberUrl = "${trelloApi.baseApiUrl}/notifications/$id/member?${trelloApi.credentials}"
        return getTrelloObject(memberUrl)
    }

    fun getOrganization(): Organization {
        val organizationUrl = "${trelloApi.baseApiUrl}/notifications/$id/organization?${trelloApi.credentials}"
        return getTrelloObject(organizationUrl)
    }

    fun updateNotification() {
        val updateNotificationUrl = "${trelloApi.baseApiUrl}/notifications/$id?unread=$unread&${trelloApi.credentials}"
        trelloApi.httpRequests.putRequest(updateNotificationUrl)
    }

    fun markAllNotificationsAsRead() {
        val markAsReadUrl = "${trelloApi.baseApiUrl}/notifications/all/read?${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(markAsReadUrl)
    }

}