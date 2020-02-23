package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.*
import com.google.gson.annotations.SerializedName
import me.theforbiddenai.trelloapiwrapper.TrelloApi

private val gson = GsonBuilder().serializeNulls().create()

class Board {

    internal lateinit var trelloApi: TrelloApi

    @SerializedName("id")
    val id: String = ""

    @SerializedName("name")
    val name: String = ""

    @SerializedName("desc")
    val desc: String = ""

    @SerializedName("closed")
    val closed: Boolean = false

    @SerializedName("idOrganization")
    val idOrganization: String = ""

    @SerializedName("idEnterprise")
    val idEnterprise: String = ""

    @SerializedName("limits")
    val limits = gson.fromJson("", Limits::class.java)

    @SerializedName("pinned")
    val pinned: Boolean = false

    @SerializedName("starred")
    val starred: Boolean = false

    @SerializedName("url")
    val url: String = ""

    @SerializedName("prefs")
    val prefs = gson.fromJson("", MyPrefs::class.java)

    @SerializedName("memberships")
    val memberships = gson.fromJson("", Array<Membership>::class.java)

    @SerializedName("subscribed")
    val subscribed: String = ""

    @SerializedName("labelNames")
    val labelNames = gson.fromJson("", mapOf<String, String>()::class.java)

    @SerializedName("powerUps")
    val powerUps: ArrayList<String> = arrayListOf()

    @SerializedName("dateLastActivity")
    val dateLastActivity: String = ""

    @SerializedName("dateLastView")
    val dateLastView: String = ""

    @SerializedName("shortUrl")
    val shortUrl: String = ""

    @SerializedName("enterpriseOwned")
    val enterpriseOwned: Boolean = false

    fun getLabels(): Array<Label> {
        val labelUrl = "${trelloApi.baseApiUrl}/boards/$id/labels?${trelloApi.credentials}"
        val jsonLabelArray = JsonParser.parseString(trelloApi.getJsonData(labelUrl)).asJsonArray

        return jsonLabelArray.map { gson.fromJson(it, Label::class.java) }.toTypedArray()
    }

    fun getActions(): Array<Action> {
        val actionUrl = "${trelloApi.baseApiUrl}/boards/$id/actions?${trelloApi.credentials}"
        val jsonActionArray = JsonParser.parseString(trelloApi.getJsonData(actionUrl)).asJsonArray

        val actionArray = jsonActionArray.map { gson.fromJson(it, Action::class.java) }.toTypedArray()
        actionArray.forEach { it.trelloApi = trelloApi }

        return actionArray
    }



    class Limits {

        @SerializedName("attachments")
        val attachments = gson.fromJson("", AttachmentLimits::class.java)

        @SerializedName("boards")
        val boards = gson.fromJson("", BoardLimits::class.java)

        @SerializedName("cards")
        val cards = gson.fromJson("", CardLimits::class.java)

        @SerializedName("checklists")
        val checklists = gson.fromJson("", CheckListLimits::class.java)

        @SerializedName("checkItems")
        val checkItems = gson.fromJson("", CheckItemLimits::class.java)

        @SerializedName("customFields")
        val customFields = gson.fromJson("", CustomFieldLimits::class.java)

        @SerializedName("customFieldOptions")
        val customFieldOptions = gson.fromJson("", CustomFieldOptionLimits::class.java)

        @SerializedName("labels")
        val labels = gson.fromJson("", LabelLimits::class.java)

        @SerializedName("lists")
        val lists = gson.fromJson("", ListLimits::class.java)

        @SerializedName("stickers")
        val stickers = gson.fromJson("", StickerLimits::class.java)

        @SerializedName("reactions")
        val reactions = gson.fromJson("", ReactionLimits::class.java)

        class AttachmentLimits {

            @SerializedName("perBoard")
            val perBoard = gson.fromJson("", LimitOptions::class.java)

            @SerializedName("perCard")
            val perCard = gson.fromJson("", LimitOptions::class.java)
        }

        class BoardLimits {

            @SerializedName("totalMembersPerBoard")
            val totalMembersPerBoard = gson.fromJson("", LimitOptions::class.java)

        }

        class CardLimits {

            @SerializedName("openPerBoard")
            val openPerBoard = gson.fromJson("", LimitOptions::class.java)

            @SerializedName("openPerList")
            val openPerList = gson.fromJson("", LimitOptions::class.java)

            @SerializedName("totalPerBoard")
            val totalPerBoard = gson.fromJson("", LimitOptions::class.java)

            @SerializedName("totalPerList")
            val totalPerList = gson.fromJson("", LimitOptions::class.java)
        }

        class CheckListLimits {

            @SerializedName("perBoard")
            val perBoard = gson.fromJson("", LimitOptions::class.java)

            @SerializedName("perCard")
            val perCard = gson.fromJson("", LimitOptions::class.java)

        }

        class CheckItemLimits {

            @SerializedName("perChecklist")
            val perChecklist = gson.fromJson("", LimitOptions::class.java)

        }

        class CustomFieldLimits {

            @SerializedName("perBoard")
            val perBoard = gson.fromJson("", LimitOptions::class.java)

        }

        class CustomFieldOptionLimits {

            @SerializedName("perField")
            val perField = gson.fromJson("", LimitOptions::class.java)

        }

        class LabelLimits {

            @SerializedName("perBoard")
            val perBoard = gson.fromJson("", LimitOptions::class.java)

        }

        class ListLimits {

            @SerializedName("openPerBoard")
            val openPerBoard = gson.fromJson("", LimitOptions::class.java)

            @SerializedName("totalPerBoard")
            val totalPerBoard = gson.fromJson("", LimitOptions::class.java)

        }

        class StickerLimits {

            @SerializedName("perCard")
            val perCard = gson.fromJson("", LimitOptions::class.java)

        }

        class ReactionLimits {

            @SerializedName("perAction")
            val perAction = gson.fromJson("", LimitOptions::class.java)

            @SerializedName("uniquePerAction")
            val uniquePerAction = gson.fromJson("", LimitOptions::class.java)

        }

        class LimitOptions {

            @SerializedName("status")
            val status: String = ""

            @SerializedName("disableAt")
            val disableAt: Int = 0

            @SerializedName("warnAt")
            val warnAt: Int = 0

        }
    }
}

