package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.DescData
import me.theforbiddenai.trelloapiwrapper.utils.LimitOptions
import java.util.*

class Board : TrelloObject() {

    val id: String = ""
    val name: String = ""
    val desc: String = ""
    val descData: DescData = DescData()
    val closed: Boolean = false
    val idOrganization: String = ""
    val idEnterprise: String = ""
    val limits: BLimits = BLimits()
    val pinned: Boolean = false
    val starred: Boolean = false
    val url: String = ""
    val prefs: BoardPrefs = BoardPrefs()
    val memberships: Array<Membership> = arrayOf()
    val subscribed: String = ""
    val labelNames: Map<String, String> = mapOf()
    val dateLastActivity: Date = Date()
    val dateLastView: Date = Date()
    val shortUrl: String = ""
    val enterpriseOwned: Boolean = false

    fun getActions(): Array<Action> {
        val actionsUrl = "${trelloApi.baseApiUrl}/boards/$id/actions?${trelloApi.credentials}"
        return getObjectArray(actionsUrl)
    }

    fun getBoardStars(): Array<BoardStar> {
        val boardStarsUrl = "${trelloApi.baseApiUrl}/boards/$id/boardStars?${trelloApi.credentials}"
        return getObjectArray(boardStarsUrl)
    }

    fun getCards(): Array<Card> {
        val cardsUrl = "${trelloApi.baseApiUrl}/boards/$id/cards?${trelloApi.credentials}"
        return getObjectArray(cardsUrl)
    }

    fun getCard(cardId: String): Card {
        val cardUrl = "${trelloApi.baseApiUrl}/boards/$id/cards/$cardId?${trelloApi.credentials}"
        return getObject(cardUrl)
    }

    fun getChecklists(): Array<Checklist> {
        val checklistsUrl = "${trelloApi.baseApiUrl}/boards/$id/checklists?${trelloApi.credentials}"
        return getObjectArray(checklistsUrl)
    }

    fun getCustomFields(): Array<CustomField> {
        val customFieldsUrl = "${trelloApi.baseApiUrl}/boards/$id/customFields?${trelloApi.credentials}"
        return getObjectArray(customFieldsUrl)
    }

    fun getLabels(): Array<Label> {
        val labelsUrl = "${trelloApi.baseApiUrl}/boards/$id/labels?${trelloApi.credentials}"
        return getObjectArray(labelsUrl)
    }

    fun getLists(): Array<List> {
        val listsUrl = "${trelloApi.baseApiUrl}/boards/$id/lists?${trelloApi.credentials}"
        return getObjectArray(listsUrl)
    }

    fun getMembers(): Array<Member> {
        val membersUrl = "${trelloApi.baseApiUrl}/boards/$id/members?${trelloApi.credentials}"
        return getObjectArray(membersUrl)
    }


    // TODO: Implement the rest of the put and delete functions

    class BLimits {

        val attachments: AttachmentLimits = AttachmentLimits()
        val boards: BoardLimits = BoardLimits()
        val cards: CardLimits = CardLimits()
        val checklists: CheckListLimits = CheckListLimits()
        val checkItems: CheckItemLimits = CheckItemLimits()
        val customFields: CustomFieldLimits = CustomFieldLimits()
        val customFieldOptions: CustomFieldOptionLimits = CustomFieldOptionLimits()
        val labels: LabelLimits = LabelLimits()
        val lists: ListLimits = ListLimits()
        val stickers: StickerLimits = StickerLimits()
        val reactions: ReactionLimits = ReactionLimits()

        class AttachmentLimits {

            val perBoard: LimitOptions = LimitOptions()
            val perCard: LimitOptions = LimitOptions()
        }

        class BoardLimits {

            val totalMembersPerBoard: LimitOptions = LimitOptions()

        }

        class CardLimits {

            val openPerBoard: LimitOptions = LimitOptions()
            val openPerList: LimitOptions = LimitOptions()
            val totalPerBoard: LimitOptions = LimitOptions()
            val totalPerList: LimitOptions = LimitOptions()
        }

        class CheckListLimits {

            val perBoard: LimitOptions = LimitOptions()
            val perCard: LimitOptions = LimitOptions()

        }

        class CheckItemLimits {

            val perChecklist: LimitOptions = LimitOptions()

        }

        class CustomFieldLimits {

            val perBoard: LimitOptions = LimitOptions()

        }

        class CustomFieldOptionLimits {

            val perField: LimitOptions = LimitOptions()

        }

        class LabelLimits {

            val perBoard: LimitOptions = LimitOptions()

        }

        class ListLimits {

            val openPerBoard: LimitOptions = LimitOptions()
            val totalPerBoard: LimitOptions = LimitOptions()

        }

        class StickerLimits {

            val perCard: LimitOptions = LimitOptions()

        }

        class ReactionLimits {

            val perAction: LimitOptions = LimitOptions()
            val uniquePerAction: LimitOptions = LimitOptions()

        }

    }

    class BoardPrefs {

        val permissionLevel: String = ""
        val hideVotes: Boolean = false
        val voting: String = ""
        val comments: String = ""
        val invitations: String = ""
        val selfJoin: Boolean = false
        val cardCovers: Boolean = false
        val isTemplate: Boolean = false
        val cardAging: String = ""
        val calendarFeedEnabled: Boolean = false
        val background: String = ""
        val backgroundImage: String = ""
        val backgroundTile: Boolean = false
        val backgroundBrightness: String = ""
        val backgroundColor: String = ""
        val backgroundBottomColor: String = ""
        val backgroundTopColor: String = ""
        val canBePublic: Boolean = false
        val canBeEnterprise: Boolean = false
        val canBeOrg: Boolean = false
        val canBePrivate: Boolean = false
        val canInvite: Boolean = false

    }

    class BoardStar : TrelloObject() {
        val id: String = ""
        val idBoard: String = ""
        val pos: Float = 0F

        class StarObject {
            val _id: String = ""
            val idBoard: String = ""
            val pos: Float = 0F
        }
    }

    class Membership : TrelloObject() {

        val id: String = ""
        val idMember: String = ""
        val memberType: String = ""
        val unconfirmed: Boolean = false
        val deactivated: Boolean = false

    }

}