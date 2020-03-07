package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.TrelloApi
import me.theforbiddenai.trelloapiwrapper.utils.DescData
import me.theforbiddenai.trelloapiwrapper.utils.Sticker
import java.util.*

class Card internal constructor() : TrelloObject() {

    constructor(trelloApi: TrelloApi, idList: String, name: String = "", desc: String = "") : this() {
        this.trelloApi = trelloApi
        this.idList = idList
        this.name = name
        this.desc = desc
    }

    val id: String = ""
    val checkItemStates: Array<CheckItemStates> = arrayOf()
    var closed: Boolean = false
    val dateLastActivity: Boolean = false
    var desc: String = ""
    val descData: DescData =
        DescData()
    val dueReminder: Int = 0
    var idBoard: String = ""
    var idList: String = ""
    val idMembersVoted: Array<String> = arrayOf()
    val idShort: Int = 0
    var idAttachmentCover: String = ""
    var idLabels: Array<String> = arrayOf()
    val manualCoverAttachment: Boolean = false
    var name: String = ""
    var pos: Float = 0F
    val shortLink: String = ""
    val isTemplate: Boolean = false
    val badges: Badge = Badge()
    var dueComplete: Boolean = false
    var due: Date? = null
    val idChecklists: Array<String> = arrayOf()
    var idMembers: Array<String> = arrayOf()
    val labels: Array<Label> = arrayOf()
    val shortUrl: String = ""
    var subscribed: Boolean = false
    val url: String = ""
    val cover: Cover = Cover()

    fun getActions(): Array<Action> {
        val actionsUrl = "${trelloApi.baseApiUrl}/cards/$id/actions?${trelloApi.credentials}"
        return getTrelloObjectArray(actionsUrl)
    }

    fun getAttachments(): Array<Attachment> {
        val attachmentsUrl = "${trelloApi.baseApiUrl}/cards/$id/attachments?${trelloApi.credentials}"
        return getTrelloObjectArray(attachmentsUrl)
    }

    fun getAttachment(attachmentId: String): Attachment {
        val attachmentUrl = "${trelloApi.baseApiUrl}/cards/$id/attachments/$attachmentId?${trelloApi.credentials}"
        return getTrelloObject(attachmentUrl)
    }

    fun getBoard(): Board {
        val boardUrl = "${trelloApi.baseApiUrl}/cards/$id/board?${trelloApi.credentials}"
        return getTrelloObject(boardUrl)
    }

    fun getChecklists(): Array<Checklist> {
        val checklistsUrl = "${trelloApi.baseApiUrl}/cards/$id/checklists?${trelloApi.credentials}"
        return getTrelloObjectArray(checklistsUrl)
    }

    fun getCheckItemById(checkItemId: String): Checklist.CheckItem {
        val checkItemsUrl = "${trelloApi.baseApiUrl}/cards/$id/checkItem/$checkItemId?${trelloApi.credentials}"
        return getTrelloObject(checkItemsUrl)
    }

    fun getCustomFields(): Array<CustomField> {
        val customFieldItemsUrl = "${trelloApi.baseApiUrl}/cards/$id/customFields?${trelloApi.credentials}"
        return getTrelloObjectArray(customFieldItemsUrl)
    }

    fun getList(): TrelloList {
        val listUrl = "${trelloApi.baseApiUrl}/cards/$id/list?${trelloApi.credentials}"
        return getTrelloObject(listUrl)
    }

    fun getMembers(): Array<Member> {
        val membersUrl = "${trelloApi.baseApiUrl}/cards/$id/members?${trelloApi.credentials}"
        return getTrelloObjectArray(membersUrl)
    }

    fun getMembersVoted(): Array<Member> {
        val membersUrl = "${trelloApi.baseApiUrl}/cards/$id/membersVoted?${trelloApi.credentials}"
        return getTrelloObjectArray(membersUrl)
    }

    fun getStickers(): Array<Sticker> {
        val stickerArray = "${trelloApi.baseApiUrl}/cards/$id/stickers?${trelloApi.credentials}"
        return getTrelloObjectArray(stickerArray)
    }

    fun getStickerById(stickerId: String): Sticker {
        val stickerArray = "${trelloApi.baseApiUrl}/cards/$id/stickers/$stickerId?${trelloApi.credentials}"
        return getTrelloObject(stickerArray)
    }

    fun updateCard() {
        val json = trelloApi.gson.toJson(this)
        val updateCardUrl = "${trelloApi.baseApiUrl}/cards/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateCardUrl, json)
    }

    fun updateComment(actionId: String, value: String) {
        val urlParams = "text=$value"
        val updateCommentUrl =
            "${trelloApi.baseApiUrl}/cards/$id/actions/$actionId/comments?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateCommentUrl)
    }

    fun updateCheckItem(
        checkItemId: String,
        name: String = "",
        checklistId: String = "",
        state: String = "incomplete",
        pos: String = "bottom"
    ) {
        var urlParams = "state=$state&pos=$pos"
        if (name.isNotEmpty()) urlParams += "&name=$name"
        if (checklistId.isNotEmpty()) urlParams += "&idChecklist=$checklistId"

        val updateCheckItemUrl =
            "${trelloApi.baseApiUrl}/cards/$id/checkItem/$checkItemId?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateCheckItemUrl)
    }

    fun updateSticker(stickerId: String, top: Float, left: Float, zIndex: Int, rotate: Float) {
        val urlParams = "top=$top&left=$left&zIndex=$zIndex&rotate=$rotate"
        val updateStickerUrl =
            "${trelloApi.baseApiUrl}/cards/$id/stickers/$stickerId?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateStickerUrl)
    }

    fun createCard(): Card {
        val cardJson = trelloApi.gson.toJson(this)
        val jsonString = cardJson.toString()

        val createCardUrl = "${trelloApi.baseApiUrl}/cards?${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createCardUrl, jsonString)
        return createObjectFromJson(result)
    }

    fun addComment(text: String) {
        val addCommentUrl = "${trelloApi.baseApiUrl}/cards/$id/actions/comments?text=$text&${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(addCommentUrl)
    }

    fun addAttachment(url: String, name: String = ""): Attachment {
        var urlParams = "url=$url"
        if (name.isNotEmpty()) urlParams += "&name=$name"
        val addAttachmentUrl = "${trelloApi.baseApiUrl}/cards/$id/attachments?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(addAttachmentUrl)
        return createObjectFromJson(result)
    }

    fun addChecklist(name: String = "", checklistSource: String = "", pos: String = "") {
        var urlParams = ""
        if (name.isNotEmpty()) urlParams = "name=$name"
        if (checklistSource.isNotEmpty()) urlParams += "&idChecklistSource=$checklistSource"
        if (pos.isNotEmpty()) urlParams += "&pos=$pos"
        urlParams = urlParams.removePrefix("&")

        val addChecklistUrl = "${trelloApi.baseApiUrl}/cards/$id/checklists?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.postRequest(addChecklistUrl)
    }

    fun addLabel(labelId: String) {
        val addLabelUrl = "${trelloApi.baseApiUrl}/cards/$id/idLabels?value=$labelId&${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(addLabelUrl)
    }

    fun addMember(memberId: String) {
        val addMemberUrl = "${trelloApi.baseApiUrl}/cards/$id/idMembers?value=$memberId&${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(addMemberUrl)
    }

    fun addLabel(color: String, name: String) {
        val urlParams = "color=$color&name=$name"

        val addLabelUrl = "${trelloApi.baseApiUrl}/cards/$id/labels?$urlParams&${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(addLabelUrl)
    }

    fun markAssociatedNotificationsRead() {
        val addLabelUrl = "${trelloApi.baseApiUrl}/cards/$id/markAssociatedNotificationsRead?${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(addLabelUrl)
    }

    fun addMemberVote(memberId: String) {
        val addMemberVoteUrl = "${trelloApi.baseApiUrl}/cards/$id/membersVoted?value=$memberId&${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(addMemberVoteUrl)
    }

    fun addSticker(imageIdentifier: String, top: Float, left: Float, zIndex: Int, rotate: Float = 0F): Sticker {
        val urlParams = "image=$imageIdentifier&top=$top&left=$left&zIndex=$zIndex&rotate=$rotate"
        val addStickerUrl = "${trelloApi.baseApiUrl}/cards/$id/stickers?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(addStickerUrl)
        return createObjectFromJson(result)
    }

    fun deleteCard() {
        val deleteCardUrl = "${trelloApi.baseApiUrl}/cards/$id?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteCardUrl)
    }

    fun deleteComment(actionId: String) {
        val deleteCommentUrl = "${trelloApi.baseApiUrl}/cards/$id/actions/$actionId/comments?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteCommentUrl)
    }

    fun deleteAttachment(attachmentId: String) {
        val deleteAttachmentUrl = "${trelloApi.baseApiUrl}/cards/$id/attachments/$attachmentId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteAttachmentUrl)
    }

    fun deleteCheckItem(checkItemId: String) {
        val deleteCheckItemUrl = "${trelloApi.baseApiUrl}/cards/$id/checkItem/$checkItemId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteCheckItemUrl)
    }

    fun deleteChecklist(checklistId: String) {
        val deleteChecklistUrl = "${trelloApi.baseApiUrl}/cards/$id/checklists/$checklistId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteChecklistUrl)
    }

    fun removeLabel(labelId: String) {
        val removeLabelId = "${trelloApi.baseApiUrl}/cards/$id/idLabels/$labelId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeLabelId)
    }

    fun removeMember(memberId: String) {
        val removeMemberId = "${trelloApi.baseApiUrl}/cards/$id/idMembers/$memberId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeMemberId)
    }

    fun removeMemberVote(memberId: String) {
        val removeMemberVoteUrl = "${trelloApi.baseApiUrl}/cards/$id/membersVoted/$memberId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeMemberVoteUrl)
    }

    fun removeSticker(memberId: String) {
        val removeStickerUrl = "${trelloApi.baseApiUrl}/cards/$id/stickers/$memberId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeStickerUrl)
    }

    class CheckItemStates {
        val idCheckItem: String = ""
        val state: String = ""
    }

    class Badge {

        val attachmentsByType = AttachmentsByType()
        val board: Int = 0
        val votes: Int = 0
        val viewingMemberVoted: Boolean = false
        val subscribed: Boolean = false
        val fogbugz: String = ""
        val checkItems: Int = 0
        val checkItemsChecked: Int = 0
        val comments: Int = 0
        val attachments: Int = 0
        val description: Boolean = false
        val due: String = ""
        val dueComplete: Boolean = false

        class AttachmentsByType {

            val trello = Trello()

            class Trello {

                val board: Int = 0
                val card: Int = 0

            }
        }

    }

    class Cover {
        val idAttachment: String = ""
        val color: String = ""
        val idUploadedBackground: String = ""
        val size: String = ""
        val brightness: String = ""
    }

    class Attachment {
        val id: String = ""
        val bytes: Int = 0
        val date: Date? = null
        val edgeColor: String = ""
        val idMember: String = ""
        val isUpload: Boolean = false
        val mimeType: String = ""
        val name: String = ""
    }


}