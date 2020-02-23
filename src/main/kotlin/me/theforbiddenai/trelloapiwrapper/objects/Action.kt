package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import me.theforbiddenai.trelloapiwrapper.TrelloApi

private val gson = GsonBuilder().serializeNulls().create()

class Action {

    internal lateinit var trelloApi: TrelloApi

    val id: String = ""
    val idMemberCreator: String = ""
    val data: JsonObject = JsonObject()
    val type: String = ""
    val date: String = ""
    val display = gson.fromJson("", Display::class.java)
    val memberCreator = gson.fromJson("", MemberCreator::class.java)

    fun getBoard() : Board{
        val boardUrl = "${trelloApi.baseApiUrl}/actions/$id/board?${trelloApi.credentials}"
        return trelloApi.getBoardInternal(boardUrl)
    }

    class Display {

        val translationKey: String = ""

        @SerializedName("entities")
        val rawEntities: JsonObject = JsonObject()

        @Transient
        var entities = gson.fromJson("", Entities::class.java)
            internal set

        class Entities {

            @SerializedName("board")
            val board = gson.fromJson("", EntityBoard::class.java)

            @SerializedName("memberCreator")
            val memberCreator = gson.fromJson("", EntityMemberCreator::class.java)

            class EntityBoard {
                @SerializedName("type")
                val type: String = ""

                @SerializedName("id")
                val id: String = ""

                @SerializedName("text")
                val text: String = ""

                @SerializedName("shortLink")
                val shortLink: String = ""
            }

            class EntityMemberCreator {
                @SerializedName("type")
                val type: String = ""

                @SerializedName("id")
                val id: String = ""

                @SerializedName("username")
                val username: String = ""

                @SerializedName("text")
                val text: String = ""
            }
        }
    }

    class MemberCreator {

        @SerializedName("id")
        val id: String = ""

        @SerializedName("activityBlocked")
        val activityBlocked: Boolean = false

        @SerializedName("avatarHash")
        val avatarHash: String = ""

        @SerializedName("avatarUrl")
        val avatarUrl: String = ""

        @SerializedName("fullName")
        val fullName: String = ""

        @SerializedName("idMemberReferrer")
        val idMemberReferrer: String = ""

        @SerializedName("initials")
        val initials: String = ""

        @SerializedName("username")
        val username: String = ""

    }

}