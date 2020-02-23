package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.annotations.SerializedName

class Membership {

    @SerializedName("id")
    val id: String = ""

    @SerializedName("idMember")
    val idMember: String = ""

    @SerializedName("memberType")
    val memberType: String = ""

    @SerializedName("unconfirmed")
    val unconfirmed: Boolean = false

    @SerializedName("deactivated")
    val deactivated: Boolean = false

}