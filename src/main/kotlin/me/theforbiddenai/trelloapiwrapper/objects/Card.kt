package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.DescData

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

    // TODO: Implement the rest of the get, put, and delete functions

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

}