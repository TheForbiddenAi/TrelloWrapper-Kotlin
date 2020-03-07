package me.theforbiddenai.trelloapiwrapper.objects

class Enterprise internal constructor() : TrelloObject() {

    val id: String = ""
    val name: String = ""
    val displayName: String = ""
    val prefs: EnterprisePrefs = EnterprisePrefs()
    val ssoActivationFailed: Boolean = false
    val idAdmins: Array<String> = arrayOf()
    val idMembers: Array<String> = arrayOf()
    val idOrganizations: Array<String> = arrayOf()
    val products: Array<Int> = arrayOf()
    val userTypes: UserTypes = UserTypes()

    fun getAdmins(): Array<Member> {
        val adminsUrl = "${trelloApi.baseApiUrl}/enterprises/$id/admins?${trelloApi.credentials}"
        return getTrelloObjectArray(adminsUrl)
    }

    fun getSignupUrl(
        authenticate: Boolean = false,
        confirmationAccepted: Boolean = false,
        returnUrl: String = "none",
        tosAccepted: Boolean = false
    ): String {
        val urlParams = "authenticate=$authenticate" +
                "&confirmationAccepted=$confirmationAccepted" +
                "&returnUrl=$returnUrl" +
                "&tosAccepted=$tosAccepted"
        val signupUrl = "${trelloApi.baseApiUrl}/enterprises/$id/signupUrl?$urlParams&${trelloApi.credentials}"
        return trelloApi.httpRequests.getRequest(signupUrl)
    }

    fun getMembers(): Array<Member> {
        val membersUrl = "${trelloApi.baseApiUrl}/enterprises/$id/members?${trelloApi.credentials}"
        return getTrelloObjectArray(membersUrl)
    }

    fun getMembersById(memberId: String): Member {
        val memberUrl = "${trelloApi.baseApiUrl}/enterprises/$id/members/$memberId?${trelloApi.credentials}"
        return getTrelloObject(memberUrl)
    }

    fun getNonTransferableMembers(organizationId: String): Array<Member> {
        val nonTransferableMember =
            "${trelloApi.baseApiUrl}/enterprises/$id/transferrable/organization/$organizationId?${trelloApi.credentials}"
        return getTrelloObjectArray(nonTransferableMember)
    }

    fun updateMemberStatus(memberId: String, deactivated: Boolean) {
        val updateMemberStatusUrl =
            "${trelloApi.baseApiUrl}/enterprises/$id/members/$memberId/deactivated?value=$deactivated&${trelloApi.credentials}"
        trelloApi.httpRequests.putRequest(updateMemberStatusUrl)
    }

    fun addOrganization(organizationId: String) {
        val addOrganizationUrl =
            "${trelloApi.baseApiUrl}/enterprises/$id/organizations?idOrganization=organizationId&${trelloApi.credentials}"
        trelloApi.httpRequests.putRequest(addOrganizationUrl)
    }

    fun addAdmin(memberId: String) {
        val addAdminUrl = "${trelloApi.baseApiUrl}/enterprises/$id/admins/$memberId?${trelloApi.credentials}"
        trelloApi.httpRequests.putRequest(addAdminUrl)
    }

    fun removeOrganization(organizationId: String) {
        val removeOrganizationUrl =
            "${trelloApi.baseApiUrl}/enterprises/$id/organizations/$organizationId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeOrganizationUrl)
    }

    fun removeAdmin(memberId: String) {
        val removeAdminUrl = "${trelloApi.baseApiUrl}/enterprises/$id/admins/$memberId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(removeAdminUrl)
    }


    class EnterprisePrefs {

        val ssoOnly: Boolean = false
        val signup = Signup()

        class Signup {

            val message: String = ""
            val confirmation: String = ""
            val banner: String = ""
            val bannerHtml: String = ""
            val confirmationHtml: String = ""
            val messageHtml: String = ""

        }

    }

    class UserTypes {
        val all: Int = 0
        val member: Int = 0
        val collaborator: Int = 0
        val saml: Int = 0
        val none: Int = 0
    }

}