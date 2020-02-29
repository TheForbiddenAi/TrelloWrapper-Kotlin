package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.TrelloApi
import me.theforbiddenai.trelloapiwrapper.utils.DescData
import me.theforbiddenai.trelloapiwrapper.utils.LimitOptions
import java.util.*

class Board internal constructor() : TrelloObject() {

    constructor(
        trelloApi: TrelloApi,
        name: String,
        defaultLists: Boolean = false,
        defaultLabels: Boolean = true
    ) : this() {
        this.trelloApi = trelloApi
        this.name = name
        this.defaultLists = defaultLists
        this.defaultLabels = defaultLabels
    }

    private var defaultLists: Boolean = false
    private var defaultLabels: Boolean = true

    val id: String = ""
    var name: String = ""
    var desc: String = ""
    val descData: DescData = DescData()
    var closed: Boolean = false
    var idOrganization: String = ""
    val idEnterprise: String = ""
    val limits: BLimits = BLimits()
    val pinned: Boolean = false
    val starred: Boolean = false
    val url: String = ""
    val prefs: BoardPrefs = BoardPrefs()
    val memberships: Array<Membership> = arrayOf()
    val subscribed: String = ""
    val labelNames: DefaultLabels = DefaultLabels()
    val dateLastActivity: Date? = null
    val dateLastView: Date? = null
    val shortUrl: String = ""
    val enterpriseOwned: Boolean = false

    fun getActions(): Array<Action> {
        val actionsUrl = "${trelloApi.baseApiUrl}/boards/$id/actions?${trelloApi.credentials}"
        return getTrelloObjectArray(actionsUrl)
    }

    fun getBoardStars(): Array<BoardStar> {
        val boardStarsUrl = "${trelloApi.baseApiUrl}/boards/$id/boardStars?${trelloApi.credentials}"
        return getTrelloObjectArray(boardStarsUrl)
    }

    fun getCards(): Array<Card> {
        val cardsUrl = "${trelloApi.baseApiUrl}/boards/$id/cards?${trelloApi.credentials}"
        return getTrelloObjectArray(cardsUrl)
    }

    fun getCard(cardId: String): Card {
        val cardUrl = "${trelloApi.baseApiUrl}/boards/$id/cards/$cardId?${trelloApi.credentials}"
        return getTrelloObject(cardUrl)
    }

    fun getChecklists(): Array<Checklist> {
        val checklistsUrl = "${trelloApi.baseApiUrl}/boards/$id/checklists?${trelloApi.credentials}"
        return getTrelloObjectArray(checklistsUrl)
    }

    fun getCustomFields(): Array<CustomField> {
        val customFieldsUrl = "${trelloApi.baseApiUrl}/boards/$id/customFields?${trelloApi.credentials}"
        return getTrelloObjectArray(customFieldsUrl)
    }

    fun getLabels(): Array<Label> {
        val labelsUrl = "${trelloApi.baseApiUrl}/boards/$id/labels?${trelloApi.credentials}"
        return getTrelloObjectArray(labelsUrl)
    }

    fun getLists(): Array<List> {
        val listsUrl = "${trelloApi.baseApiUrl}/boards/$id/lists?${trelloApi.credentials}"
        return getTrelloObjectArray(listsUrl)
    }

    fun getMembers(): Array<Member> {
        val membersUrl = "${trelloApi.baseApiUrl}/boards/$id/members?${trelloApi.credentials}"
        return getTrelloObjectArray(membersUrl)
    }

    fun getMyPrefs(): MyPrefs {
        val myPrefsUrl = "${trelloApi.baseApiUrl}/boards/$id/myprefs?${trelloApi.credentials}"
        return getTrelloObject(myPrefsUrl)
    }

    fun updateBoard() {
        val json = trelloApi.gson.toJson(this)
        val updateBoardUrl = "${trelloApi.baseApiUrl}/boards/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateBoardUrl, json)
    }

    fun updatePrefs(prefEnum: BoardPrefNames, value: Any) {
        val permOptionUrlParams = "${prefEnum.prefName}?value=$value"
        val updatePrefsUrl = "${trelloApi.baseApiUrl}/boards/$id/prefs/$permOptionUrlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updatePrefsUrl)
    }

    fun inviteMemberByEmail(email: String, userType: UserPermTypes, fullName: String) {
        val lowercaseType = userType.toString().toLowerCase()

        val urlParams = "email=$email&type=${lowercaseType}&fullName=$fullName"
        val inviteMemberUrl = "${trelloApi.baseApiUrl}/boards/$id/members?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(inviteMemberUrl)
    }

    fun inviteMemberById(memberId: String, userType: UserPermTypes, allowBillableGuest: Boolean = false) {
        val lowercaseType = userType.toString().toLowerCase()

        val urlParams = "type=${lowercaseType}&allowBillableGuest=${allowBillableGuest}"
        val inviteMemberByIdUrl =
            "${trelloApi.baseApiUrl}/boards/$id/members/$memberId?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(inviteMemberByIdUrl)
    }

    fun updateMyPrefs(prefName: MyPrefNames, value: Any) {
        val urlParams = "value=$value"
        val updateMyPrefsUrl =
            "${trelloApi.baseApiUrl}/boards/$id/myprefs/${prefName.prefName}?$urlParams&${trelloApi.credentials}"

        println(updateMyPrefsUrl)
        trelloApi.httpRequests.putRequest(updateMyPrefsUrl)
    }

    fun createBoard(): Board {
        val boardJson = trelloApi.gson.toJson(this)
        val jsonString = boardJson.toString()

        val createBoardUrl = "${trelloApi.baseApiUrl}/boards?${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createBoardUrl, jsonString)

        return createObjectFromJson(result)
    }

    fun enablePlugin(pluginId: String) {
        val urlParams = "idPlugin=$pluginId"
        val enablePluginUrl = "${trelloApi.baseApiUrl}/boards/$id/boardPlugins?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.postRequest(enablePluginUrl)
    }

    fun addTag(tagId: String) {
        val addTagUrl = "${trelloApi.baseApiUrl}/boards/$id/idTags/?value=$tagId&${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(addTagUrl)
    }

    fun createLabel(name: String, color: String): Label {
        val urlParams = "name=$name&color=$color"
        val createLabelUrl = "${trelloApi.baseApiUrl}/boards/$id/labels?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createLabelUrl)
        return createObjectFromJson(result)
    }

    fun createList(name: String, pos: String = "top"): List {
        val urlParams = "name=$name&pos=$pos"
        val createListUrl = "${trelloApi.baseApiUrl}/boards/$id/lists?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createListUrl)
        return createObjectFromJson(result)
    }

    fun markAsViewed() {
        val markAsReadUrl = "${trelloApi.baseApiUrl}/boards/$id/markAsViewed?${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(markAsReadUrl)
    }

    fun deleteBoard() {
        val deleteBoardUrl = "${trelloApi.baseApiUrl}/boards/$id?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteBoardUrl)
    }

    fun disablePlugin(pluginId: String) {
        val urlParams = "idPlugin=$pluginId"
        val enablePluginUrl = "${trelloApi.baseApiUrl}/boards/$id/boardPlugins?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.deleteRequest(enablePluginUrl)
    }

    fun removeMember(memberId: String) {
        val removeMemberUrl = "${trelloApi.baseApiUrl}/boards/$id/members/$memberId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeMemberUrl)
    }

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

    class MyPrefs {
        val showSidebar: Boolean = false
        val showSidebarMembers: Boolean = false
        val showSidebarBoardActions: Boolean = false
        val showSidebarActivity: Boolean = false
        val showListGuide: Boolean = false
        val idEmailList: String = ""
        val emailPosition: String = ""
    }

    class DefaultLabels {
        var green: String = ""
        var yellow: String = ""
        var orange: String = ""
        var red: String = ""
        var purple: String = ""
        var blue: String = ""
        var sky: String = ""
        var lime: String = ""
        var pink: String = ""
        var black: String = ""
    }

    class BoardStar {
        val id: String = ""
        val idBoard: String = ""
        val pos: Float = 0F

        class StarObject {
            val _id: String = ""
            val idBoard: String = ""
            val pos: Float = 0F
        }
    }

    class Membership {

        val id: String = ""
        val idMember: String = ""
        val memberType: String = ""
        val unconfirmed: Boolean = false
        val deactivated: Boolean = false

    }

}

enum class BoardPrefNames(internal val prefName: String) {
    PERMISSION_LEVEL("permissionLevel"),
    SELF_JOIN("selfJoin"),
    CARD_COVERS("cardCovers"),
    HIDE_VOTES("hideVotes"),
    INVITATIONS("invitations"),
    VOTING("voting"),
    COMMENTS("comments"),
    BACKGROUND("background"),
    CARD_AGING("cardAging"),
    CALENDAR_FEED_ENABLED("calendarFeedEnabled")
}

enum class UserPermTypes {
    ADMIN,
    NORMAL,
    OBSERVER
}

enum class MyPrefNames(internal val prefName: String) {
    EMAIL_POSITION("emailPosition"),
    ID_EMAIL_LIST("idEmailList"),
    SHOW_LIST_GUIDE("showListGuide"),
    SHOW_SIDEBAR("showSidebar"),
    SHOW_SIDEBAR_ACTIVITY("showSidebarActivity"),
    SHOW_SIDEBAR_BOARD_ACTIONS("showSidebarBoardActions"),
    SHOW_SIDEBAR_MEMBERS("showSidebarMembers")
}
