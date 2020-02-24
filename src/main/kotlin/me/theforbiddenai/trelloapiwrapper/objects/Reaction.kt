package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.ShortMember

class Reaction : TrelloObject() {

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