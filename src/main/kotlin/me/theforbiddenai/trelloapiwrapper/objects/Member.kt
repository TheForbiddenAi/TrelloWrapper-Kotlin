package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.DescData
import me.theforbiddenai.trelloapiwrapper.utils.LimitOptions
import me.theforbiddenai.trelloapiwrapper.utils.Sticker
import java.util.*

class Member internal constructor() : TrelloObject() {

    val id: String = ""
    val activityBlocked: Boolean = false
    val avatarHash: String = ""
    val avatarUrl: String = ""
    var bio: String = ""
    val bioData: DescData = DescData()
    val confirmed: Boolean = false
    var fullName: String = ""
    val idEnterprise: String = ""
    val idEnterprisesDeactivated: Array<String> = arrayOf()
    val idMemberReferrer: String = ""
    val idPremOrgsAdmin: Array<String> = arrayOf()
    var initials: String = ""
    val memberType: String = ""
    val products: Array<Int> = arrayOf()
    val url: String = ""
    var username: String = ""
    var avatarSource: String = ""
    val email: String = ""
    val gravatarHash: String = ""
    val idBoards: Array<String> = arrayOf()
    val idOrganizations: Array<String> = arrayOf()
    val idEnterprisesAdmin: Array<String> = arrayOf()
    val limits: MemberLimits = MemberLimits()
    val loginTypes: Array<String> = arrayOf()
    val marketingOptIn: MarketingOptIn = MarketingOptIn()
    val prefs: MemberPrefs = MemberPrefs()
    val uploadedAvatarHash: String = ""
    val uploadedAvatarUrl: String = ""
    val premiumFeatures: Array<String> = arrayOf()

    fun getActions(): Array<Action> {
        val actionsUrl = "${trelloApi.baseApiUrl}/members/$id/actions?${trelloApi.credentials}"
        return getTrelloObjectArray(actionsUrl)
    }

    fun getBoards(): Array<Board> {
        val boardsUrl = "${trelloApi.baseApiUrl}/members/$id/boards?${trelloApi.credentials}"
        return getTrelloObjectArray(boardsUrl)
    }

    fun getBoardBackgrounds(): Array<BoardBackground> {
        val boardBackgroundsUrl = "${trelloApi.baseApiUrl}/members/$id/boardBackgrounds?${trelloApi.credentials}"
        return getTrelloObjectArray(boardBackgroundsUrl)
    }

    fun getBoardBackgroundById(backgroundId: String): BoardBackground {
        val boardBackgroundUrl =
            "${trelloApi.baseApiUrl}/members/$id/boardBackgrounds/$backgroundId?${trelloApi.credentials}"
        return getTrelloObject(boardBackgroundUrl)
    }

    fun getBoardStars(): Array<MemberBoardStar> {
        val boardStarsUrl = "${trelloApi.baseApiUrl}/members/$id/boardStars?${trelloApi.credentials}"
        return getTrelloObjectArray(boardStarsUrl)
    }

    fun getBoardStarById(boardStarId: String): MemberBoardStar {
        val boardStarUrl = "${trelloApi.baseApiUrl}/members/$id/boardStars/$boardStarId?${trelloApi.credentials}"
        return getTrelloObject(boardStarUrl)
    }

    fun getBoardsInvited(): Array<Board> {
        val boardsInvitedUrl = "${trelloApi.baseApiUrl}/members/$id/boardsInvited?${trelloApi.credentials}"
        return getTrelloObjectArray(boardsInvitedUrl)
    }

    fun getCards(): Array<Card> {
        val cardsUrl = "${trelloApi.baseApiUrl}/members/$id/cards?${trelloApi.credentials}"
        return getTrelloObjectArray(cardsUrl)
    }

    fun getCustomEmojis(): Array<CustomEmoji> {
        val emojisUrl = "${trelloApi.baseApiUrl}/members/$id/customEmoji?${trelloApi.credentials}"
        return getTrelloObjectArray(emojisUrl)
    }

    fun getCustomEmojiById(emojiId: String): CustomEmoji {
        val emojiUrl = "${trelloApi.baseApiUrl}/members/$id/customEmoji/$emojiId?fields=all&${trelloApi.credentials}"
        return getTrelloObject(emojiUrl)
    }

    fun getStickers(): Array<Sticker> {
        val stickersUrl = "${trelloApi.baseApiUrl}/members/$id/customStickers?${trelloApi.credentials}"
        return getTrelloObjectArray(stickersUrl)
    }

    fun getStickerById(stickerId: String): Sticker {
        val stickerUrl =
            "${trelloApi.baseApiUrl}/members/$id/customStickers/$stickerId?fields=all&${trelloApi.credentials}"
        return getTrelloObject(stickerUrl)
    }

    fun getEnterprises(): Array<Enterprise> {
        val enterprisesUrl = "${trelloApi.baseApiUrl}/members/$id/enterprises?${trelloApi.credentials}"
        return getTrelloObjectArray(enterprisesUrl)
    }

    fun getNotifications(): Array<Notification> {
        val notificationsUrl = "${trelloApi.baseApiUrl}/members/$id/notifications?${trelloApi.credentials}"
        return getTrelloObjectArray(notificationsUrl)
    }

    fun getOrganizations(): Array<Organization> {
        val organizationsUrl = "${trelloApi.baseApiUrl}/members/$id/organizations?${trelloApi.credentials}"
        return getTrelloObjectArray(organizationsUrl)
    }

    fun getOrganizationInvited(): Array<Organization> {
        val invitedOrganizationsUrl =
            "${trelloApi.baseApiUrl}/members/$id/organizations/organizationsInvited?${trelloApi.credentials}"
        return getTrelloObjectArray(invitedOrganizationsUrl)
    }

    fun updateMember() {
        val json = trelloApi.gson.toJson(this)
        val updateMemberUrl = "${trelloApi.baseApiUrl}/members/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateMemberUrl, json)
    }

    fun updateMemberPrefs(prefName: MemberPrefNames, value: Any) {
        val urlParams = "value=$value"
        val updatePrefsUrl =
            "${trelloApi.baseApiUrl}/members/$id/prefs/${prefName.prefName}?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updatePrefsUrl)
    }

    fun updateBoardBackground(boardBackgroundId: String, brightness: String = "unknown", title: Boolean = false) {
        val urlParams = "brightness=$brightness&title=$title"
        val updateBoardBackgroundUrl =
            "${trelloApi.baseApiUrl}/members/$id/boardBackgrounds/$boardBackgroundId?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateBoardBackgroundUrl)
    }

    fun updateBoardStarPosition(boardStarId: String, pos: Float) {
        val updateStarUrl =
            "${trelloApi.baseApiUrl}/members/$id/boardStars/$boardStarId?pos=$pos&${trelloApi.credentials}"
        trelloApi.httpRequests.putRequest(updateStarUrl)
    }

    fun addBoardStar(boardId: String, pos: String): Board.BoardStar {
        val urlParams = "idBoard=$boardId&pos=$pos"
        val addStarUrl = "${trelloApi.baseApiUrl}/members/$id/boardStars?$urlParams&${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(addStarUrl)
        return createObjectFromJson(result)
    }

    fun dismissOneTimeMessage(value: String) {
        val dismissMessage =
            "${trelloApi.baseApiUrl}/members/$id/oneTimeMessagesDismissed?value=$value&${trelloApi.credentials}"
        trelloApi.httpRequests.postRequest(dismissMessage)
    }

    fun deleteBoardBackground(backgroundId: String) {
        val deleteBgUrl = "${trelloApi.baseApiUrl}/members/$id/boardBackgrounds/$backgroundId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteBgUrl)
    }

    fun removeBoardStar(boardStarId: String) {
        val removeStarUrl = "${trelloApi.baseApiUrl}/members/$id/boardStars/$boardStarId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeStarUrl)
    }

    fun deleteCustomSticker(stickerId: String) {
        val removeStickerUrl = "${trelloApi.baseApiUrl}/members/$id/customStickers/$stickerId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeStickerUrl)
    }

    class MemberLimits {

        val boards: BoardLimits = BoardLimits()
        val orgs: OrgLimits = OrgLimits()

        class BoardLimits {
            val totalPerMember: LimitOptions = LimitOptions()
        }

        class OrgLimits {
            val totalPerMember: LimitOptions = LimitOptions()
        }

    }

    class MarketingOptIn {
        val optedIn: Boolean = false
        val date: Date? = null
    }

    class MemberPrefs {
        val privacy: Privacy = Privacy()
        val sendSummaries: Boolean = false
        val minutesBetweenSummaries: Int = 0
        val minutesBeforeDeadlineToNotify: Int = 0
        val colorBlind: Boolean = false
        val locale: String = ""

        class Privacy {
            val fullName: String = ""
            val avatar: String = ""
        }
    }

    class BoardBackground {
        val id: String = ""
        val type: String = ""
        val tile: Boolean = false
        val brightness: String = ""
        val color: String = ""
    }

    class MemberBoardStar {
        val id: String = ""
        val idBoard: String = ""
        val pos: Float = 0F
    }

    class CustomEmoji {
        val id: String = ""
        val url: String = ""
        val name: String = ""
    }
}

enum class MemberPrefNames(internal val prefName: String) {
    COLOR_BLIND("colorBlind"),
    LOCALE("locale"),
    MINUTES_BETWEEN_SUMMARIES("minutesBetweenSummaries")
}