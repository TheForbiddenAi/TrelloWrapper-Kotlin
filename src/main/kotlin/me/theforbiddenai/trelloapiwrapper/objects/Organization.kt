package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.TrelloApi
import me.theforbiddenai.trelloapiwrapper.utils.DescData
import me.theforbiddenai.trelloapiwrapper.utils.LimitOptions
import me.theforbiddenai.trelloapiwrapper.utils.Membership

class Organization internal constructor() : TrelloObject() {

    constructor(trelloApi: TrelloApi, displayName: String) : this() {
        this.trelloApi = trelloApi
        this.displayName = displayName
    }

    val id: String = ""
    var name: String = ""
    var displayName: String = ""
    var desc: String = ""
    val descData: DescData =
        DescData()
    val url: String = ""
    var website: String = ""
    val logoHash: String = ""
    val logoUrl: String = ""
    val premiumFeatures: Array<String> = arrayOf()

    fun getActions(): Array<Action> {
        val actionsUrl = "${trelloApi.baseApiUrl}/organizations/$id/actions?${trelloApi.credentials}"
        return getTrelloObjectArray(actionsUrl)
    }

    fun getBoards(): Array<Board> {
        val boardsUrl = "${trelloApi.baseApiUrl}/organizations/$id/boards?${trelloApi.credentials}"
        return getTrelloObjectArray(boardsUrl)
    }

    fun getMembers(): Array<Member> {
        val memberUrl = "${trelloApi.baseApiUrl}/organizations/$id/members?${trelloApi.credentials}"
        return getTrelloObjectArray(memberUrl)
    }

    fun getMembersInvited(): Array<Member> {
        val memberUrl = "${trelloApi.baseApiUrl}/organizations/$id/membersInvited?${trelloApi.credentials}"
        return getTrelloObjectArray(memberUrl)
    }

    fun getMemberships(): Array<Membership> {
        val memberUrl = "${trelloApi.baseApiUrl}/organizations/$id/membersInvited?${trelloApi.credentials}"
        return getTrelloObjectArray(memberUrl)
    }

    fun getMembershipById(membershipId: String): Membership {
        val memberUrl = "${trelloApi.baseApiUrl}/organizations/$id/memberships/$membershipId?${trelloApi.credentials}"
        return getTrelloObject(memberUrl)
    }

    fun updateOrganization() {
        val json = trelloApi.gson.toJson(this)
        val memberUrl = "${trelloApi.baseApiUrl}/organizations/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(memberUrl, json)
    }

    fun updateOrganizationPrefs(prefName: OrganizationPrefNames, value: Any) {
        val urlParams = "value=$value"
        val updatePrefsUrl =
            "${trelloApi.baseApiUrl}/members/$id/prefs/${prefName.prefName}?$urlParams&${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updatePrefsUrl)
    }

    fun updateMember(memberId: String, type: String) {
        val updateMemberUrl =
            "${trelloApi.baseApiUrl}/organizations/$id/members/$memberId?type=$type&${trelloApi.credentials}"
        trelloApi.httpRequests.putRequest(updateMemberUrl)
    }

    fun updateMemberStatus(memberId: String, deactivated: Boolean) {
        val updateMemberStatusUrl =
            "${trelloApi.baseApiUrl}/organizations/$id/members/$memberId/deactivated?value=$deactivated&${trelloApi.credentials}"
        trelloApi.httpRequests.putRequest(updateMemberStatusUrl)
    }

    fun createOrganization(name: String = "", desc: String = "", website: String = ""): Organization {
        var urlParams = "displayName=$displayName"
        if (name.isNotEmpty()) urlParams += "&name=$name"
        if (desc.isNotEmpty()) urlParams += "&desc=$desc"
        if (website.isNotEmpty()) urlParams += "&website=$website"

        val createOrgUrl = "${trelloApi.baseApiUrl}/organizations?$urlParams&${trelloApi.credentials}"
        val result = trelloApi.httpRequests.postRequest(createOrgUrl)

        return createObjectFromJson(result)
    }

    fun deleteOrganization() {
        val deleteOrgUrl = "${trelloApi.baseApiUrl}/organizations/$id?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteOrgUrl)
    }

    fun deleteOrganizationLogo() {
        val deleteOrgLogoUrl = "${trelloApi.baseApiUrl}/organizations/$id/logo?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteOrgLogoUrl)
    }

    fun removeMemberFromTeam(memberId: String) {
        val removeMemberUrl = "${trelloApi.baseApiUrl}/organizations/$id/members/$memberId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeMemberUrl)
    }

    fun removeMemberFromTeamAndBoards(memberId: String) {
        val removeMemberUrl = "${trelloApi.baseApiUrl}/organizations/$id/members/$memberId/all?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeMemberUrl)
    }

    fun removeGoogleAppsDomain() {
        val removeGoogleAppsDomainUrl =
            "${trelloApi.baseApiUrl}/organizations/$id/prefs/associatedDomain?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeGoogleAppsDomainUrl)
    }

    fun removeEmailRestriction() {
        val removeEmailRestriction =
            "${trelloApi.baseApiUrl}/organizations/$id/prefs/orgInviteRestrict?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeEmailRestriction)
    }

    class OrganizationLimits {
        class OrgLimit {
            val totalMembersPerOrg: LimitOptions =
                LimitOptions()
            val freeBoardsPerOrg: LimitOptions =
                LimitOptions()
        }
    }

    class OrganizationPrefs {
        val permissionLevel: String = ""
        val orgInviteRestrict: Array<String> = arrayOf()
        val boardInviteRestrict: String = ""
        val externalMembersDisabled: Boolean = false
        val associatedDomain: String = ""
        val googleAppsVersion: Int = 0
        val boardVisibilityRestrict: Restrictions = Restrictions()
        val boardDeleteRestrict: Restrictions = Restrictions()
        val attachmentRestrictions: Restrictions = Restrictions()

        class Restrictions {
            val private: String = ""
            val org: String = ""
            val enterprise: String = ""
            val public: String = ""
        }
    }
}

enum class OrganizationPrefNames(internal val prefName: String) {
    ASSOCIATED_DOMAIN("associatedDomain"),
    EXTERNAL_MEMBERS_DISABLED("externalMembersDisabled"),
    GOOGLE_APPS_VERSION("googleAppsVersion"),
    BOARD_VISIBILITY_RESTRICT_ORG("boardVisibilityRestrict/org"),
    BOARD_VISIBILITY_RESTRICT_PRIVATE("boardVisibilityRestrict/private"),
    BOARD_VISIBILITY_RESTRICT_PUBLIC("boardVisibilityRestrict/public\n"),
    ORG_INVITE_RESTRICT("orgInviteRestrict"),
    PERMISSION_LEVEL("permissionLevel")
}