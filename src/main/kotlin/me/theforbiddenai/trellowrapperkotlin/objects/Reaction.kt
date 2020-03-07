package me.theforbiddenai.trellowrapperkotlin.objects

import me.theforbiddenai.trellowrapperkotlin.utils.ShortMember

class Reaction internal constructor() : TrelloObject() {

    val id: String = ""
    val idMember: String = ""
    val idModel: String = ""
    val idEmoji: String = ""
    val member: ShortMember = ShortMember()
    val emoji: Emoji = Emoji()

    class Emoji {
        val unified: String = ""
        val native: String = ""
        val name: String = ""
        val skinVariation: String = ""
        val shortName: String = ""
    }

}