package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.DescData
import me.theforbiddenai.trelloapiwrapper.utils.LimitOptions

class Board : TrelloObject() {

    val id: String = ""
    val name: String = ""
    val desc: String = ""
    val descData: DescData = DescData()
    val closed: Boolean = false
    val idOrganization: String = ""
    val idEnterprise: String = ""
    val limits: BoardLimits = BoardLimits()
    val pinned: Boolean = false
    val starred: Boolean = false
    val url: String = ""
    val prefs: Prefs = Prefs()
    val memberships: Array<Membership> = arrayOf()
    val subscribed: String = ""
    val labelNames: Map<String, String> = mapOf()
    val dateLastActivity: String = ""
    val dateLastView: String = ""
    val shortUrl: String = ""
    val enterpriseOwned: Boolean = false

    fun getLabels(): Array<Label> {
        val labelsUrl = "${trelloApi.baseApiUrl}/boards/$id/labels?${trelloApi.credentials}"
        return getObjectArray(labelsUrl)
    }

    fun getActions(): Array<Action> {
        val actionsUrl = "${trelloApi.baseApiUrl}/boards/$id/actions?${trelloApi.credentials}"
        return getObjectArray(actionsUrl)
    }

    class BoardLimits {

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

    class Prefs {

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

    class Membership {

        val id: String = ""
        val idMember: String = ""
        val memberType: String = ""
        val unconfirmed: Boolean = false
        val deactivated: Boolean = false

    }
}