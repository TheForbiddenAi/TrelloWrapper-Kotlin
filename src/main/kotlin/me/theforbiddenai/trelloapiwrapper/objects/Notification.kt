package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.DataObject
import me.theforbiddenai.trelloapiwrapper.utils.ShortMember
import java.util.*

class Notification : TrelloObject() {

    val id: String = ""
    val data: DataObject = DataObject()
    val date: Date = Date()
    val idMemberCreator: String = ""
    val idAction: String = ""
    val type: String = ""
    val unread: Boolean = false
    val memberCreator: ShortMember = ShortMember()

}