package me.theforbiddenai.trelloapiwrapper.objects

class Enterprise : TrelloObject() {

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

    // TODO: Implement the rest of the get, put, and delete functions


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