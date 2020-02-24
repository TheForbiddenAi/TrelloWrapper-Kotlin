package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class Action : TrelloObject() {

    val id: String = ""
    val idMemberCreator: String = ""
    val data: JsonObject = JsonObject()
    val type: String = ""
    val date: String = ""
    val display: Display = Display()
    val memberCreator: MemberCreator = MemberCreator()

    fun getBoard(): Board {
        val boardUrl = "${trelloApi.baseApiUrl}/actions/$id/board?${trelloApi.credentials}"
        return getObject(boardUrl)
    }

    // TODO: Implement the rest of the get, put, and delete functions

    class Display {

        val translationKey: String = ""

        @SerializedName("entities")
        val rawEntities: JsonObject = JsonObject()

        @Transient
        var entities: Entities = Entities()
            internal set

        class Entities {

            val board: EntityBoard = EntityBoard()
            val memberCreator: EntityMemberCreator = EntityMemberCreator()

            class EntityBoard {

                val type: String = ""
                val id: String = ""
                val text: String = ""
                val shortLink: String = ""

            }

            class EntityMemberCreator {

                val type: String = ""
                val id: String = ""
                val username: String = ""
                val text: String = ""

            }
        }
    }

    class MemberCreator {

        val id: String = ""
        val activityBlocked: Boolean = false
        val avatarHash: String = ""
        val avatarUrl: String = ""
        val fullName: String = ""
        val idMemberReferrer: String = ""
        val initials: String = ""
        val username: String = ""

    }

}