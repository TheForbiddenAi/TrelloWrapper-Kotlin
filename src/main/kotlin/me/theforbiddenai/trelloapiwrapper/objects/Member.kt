package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.LimitOptions
import java.util.*

class Member : TrelloObject() {

    val id: String = ""
    val activityBlocked: Boolean = false
    val avatarHash: String = ""
    val avatarUrl: String = ""
    val bio: String = ""
    val bioData: BioData = BioData()
    val confirmed: Boolean = false
    val fullName: String = ""
    val idEnterprise: String = ""
    val idEnterprisesDeactivated: Array<String> = arrayOf()
    val idMemberReferrer: String = ""
    val idPremOrgsAdmin: Array<String> = arrayOf()
    val initials: String = ""
    val memberType: String = ""
    val products: Array<Int> = arrayOf()
    val url: String = ""
    val username: String = ""
    val avatarSource: String = ""
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
        return getObjectArray(boardBackgroundsUrl)
    }

    fun getBoardBackground(idBackground: String): BoardBackground {
        val boardBackgroundUrl =
            "${trelloApi.baseApiUrl}/members/$id/boardBackgrounds/$idBackground?${trelloApi.credentials}"
        return getObject(boardBackgroundUrl)
    }

    fun getBoardStars(): Array<MemberBoardStar> {
        val boardStarsUrl = "${trelloApi.baseApiUrl}/members/$id/boardStars?${trelloApi.credentials}"
        return getObjectArray(boardStarsUrl)
    }

    fun getBoardStar(idStar: String): MemberBoardStar {
        val boardStarUrl = "${trelloApi.baseApiUrl}/members/$id/boardStars/$idStar?${trelloApi.credentials}"
        return getObject(boardStarUrl)
    }

    fun getBoardsInvited(): Array<Board> {
        val boardsInvitedUrl = "${trelloApi.baseApiUrl}/members/$id/boardsInvited?${trelloApi.credentials}"
        return getTrelloObjectArray(boardsInvitedUrl)
    }

    fun getCards(): Array<Card> {
        val cardsUrl = "${trelloApi.baseApiUrl}/members/$id/cards?${trelloApi.credentials}"
        return getTrelloObjectArray(cardsUrl)
    }

    fun getEnterprises(): Array<Enterprise> {
        val cardsUrl = "${trelloApi.baseApiUrl}/members/$id/enterprises?${trelloApi.credentials}"
        return getTrelloObjectArray(cardsUrl)
    }

    // TODO: Implement the rest of the get, put, and delete functions

    class BioData {

        val emoji: Map<String, String> = mapOf()

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
}