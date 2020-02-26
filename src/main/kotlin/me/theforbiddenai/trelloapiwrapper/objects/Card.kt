package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.DescData
import me.theforbiddenai.trelloapiwrapper.utils.OptionValue
import java.util.*

class Card : TrelloObject() {

    val id: String = ""
    val checkItemStates: Array<CheckItemStates> = arrayOf()
    val closed: Boolean = false
    val dateLastActivity: Boolean = false
    val desc: String = ""
    val descData: DescData = DescData()
    val dueReminder: Int = 0
    val idBoard: String = ""
    val idList: String = ""
    val idMembersVoted: Array<String> = arrayOf()
    val idShort: Int = 0
    val idAttachmentCover: String = ""
    val idLabels: Array<String> = arrayOf()
    val manualCoverAttachment: Boolean = false
    val name: String = ""
    val pos: Float = 0F
    val shortLink: String = ""
    val isTemplate: Boolean = false
    val badges: Badge = Badge()
    val dueComplete: Boolean = false
    val due: String = ""
    val idChecklists: Array<String> = arrayOf()
    val idMembers: Array<String> = arrayOf()
    val labels: Array<Label> = arrayOf()
    val shortUrl: String = ""
    val subscribed: Boolean = false
    val url: String = ""
    val cover: Cover = Cover()

    fun getActions(): Array<Action> {
        val actionsUrl = "${trelloApi.baseApiUrl}/cards/$id/actions?${trelloApi.credentials}"
        return getTrelloObjectArray(actionsUrl)
    }

    fun getAttachments(): Array<Attachment> {
        val attachmentsUrl = "${trelloApi.baseApiUrl}/cards/$id/attachments?${trelloApi.credentials}"
        return getObjectArray(attachmentsUrl)
    }

    fun getAttachment(attachmentId: String): Attachment {
        val attachmentUrl = "${trelloApi.baseApiUrl}/cards/$id/attachments/$attachmentId?${trelloApi.credentials}"
        return getObject(attachmentUrl)
    }

    fun getBoard(): Board {
        val boardUrl = "${trelloApi.baseApiUrl}/cards/$id/board?${trelloApi.credentials}"
        return getTrelloObject(boardUrl)
    }

    fun getChecklists(): Array<Checklist> {
        val checklistsUrl = "${trelloApi.baseApiUrl}/cards/$id/checklists?${trelloApi.credentials}"
        return getObjectArray(checklistsUrl)
    }

    fun getCheckItem(checkItemId: String): Checklist.CheckItem {
        val checkItemsUrl = "${trelloApi.baseApiUrl}/cards/$id/checkItem/$checkItemId?${trelloApi.credentials}"
        return getObject(checkItemsUrl)
    }

    fun getCustomFieldItems(): Array<CardCustomFieldItems> {
        val customFieldItemsUrl = "${trelloApi.baseApiUrl}/cards/$id/customFieldItems?${trelloApi.credentials}"
        return getObjectArray(customFieldItemsUrl)
    }

    fun getList(): List {
        val listUrl = "${trelloApi.baseApiUrl}/cards/$id/list?${trelloApi.credentials}"
        return getTrelloObject(listUrl)
    }

    fun getMembers(): Array<Member> {
        val membersUrl = "${trelloApi.baseApiUrl}/cards/$id/members?${trelloApi.credentials}"
        return getObjectArray(membersUrl)
    }

    fun getMembersVoted(): Array<Member> {
        val membersUrl = "${trelloApi.baseApiUrl}/cards/$id/membersVoted?${trelloApi.credentials}"
        return getObjectArray(membersUrl)
    }

    // TODO: Implement the rest of the put, and delete functions

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
        val date: Date = Date()
        val edgeColor: String = ""
        val idMember: String = ""
        val isUpload: Boolean = false
        val mimeType: String = ""
        val name: String = ""
        val previews: Array<Preview> = arrayOf()

        class Preview {
            val id: String = ""
            val _id: String = ""
            val scaled: Boolean = false
            val url: String = ""
            val bytes: Int = 0
            val height: Int = 0
            val width: Int = 0
        }

    }

    class CardCustomFieldItems {
        val id: String = ""
        val value: OptionValue = OptionValue()
        val idCustomField: String = ""
        val idModel: String = ""
        val modelType: String = ""
    }

}