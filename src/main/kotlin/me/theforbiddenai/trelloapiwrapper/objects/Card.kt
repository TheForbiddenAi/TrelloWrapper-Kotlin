package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.GsonBuilder
import me.theforbiddenai.trelloapiwrapper.TrelloApi
import me.theforbiddenai.trelloapiwrapper.utils.DescData

private val gson = GsonBuilder().serializeNulls().create()

class Card {

    internal lateinit var trelloApi: TrelloApi

    val id: String = ""
    val checkItemStates = gson.fromJson("", Array<CheckItemStates>::class.java)
    val closed: Boolean = false
    val dateLastActivity: Boolean = false
    val desc: String = ""
    val descData = gson.fromJson("", DescData::class.java)
    val dueReminder: Int = 0
    val idBoard: String = ""
    val idList: String = ""
    val idMembersVoted = gson.fromJson("", Array<String>::class.java)
    val idShort: Int = 0
    val idAttachmentCover: String = ""
    val idLabels = gson.fromJson("", Array<String>::class.java)
    val manualCoverAttachment: Boolean = false
    val name: String = ""
    val pos: Float = 0F
    val shortLink: String = ""
    val isTemplate: Boolean = false
    val badges = gson.fromJson("", Badge::class.java)
    val dueComplete: Boolean = false
    val due: String = ""
    val idChecklists = gson.fromJson("", Array<String>::class.java)
    val idMembers = gson.fromJson("", Array<String>::class.java)
    val labels = gson.fromJson("", Array<Label>::class.java)
    val shortUrl: String = ""
    val subscribed: Boolean = false
    val url: String = ""
    val cover = gson.fromJson("", Cover::class.java)

    class CheckItemStates {
        val idCheckItem: String = ""
        val state: String = ""
    }

    class Badge {

        val attachmentsByType = gson.fromJson("", AttachmentsByType::class.java)
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

            val trello = gson.fromJson("", Trello::class.java)

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

}