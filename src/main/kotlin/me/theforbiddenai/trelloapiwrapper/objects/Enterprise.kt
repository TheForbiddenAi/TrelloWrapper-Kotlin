package me.theforbiddenai.trelloapiwrapper.objects

class Enterprise : TrelloObject() {

    val id: String = ""
    val name: String = ""
    val displayName: String = ""

    class Prefs {

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

}