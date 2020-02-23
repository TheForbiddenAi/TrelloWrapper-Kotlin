package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import me.theforbiddenai.trelloapiwrapper.TrelloApi
import me.theforbiddenai.trelloapiwrapper.utils.DescData
import me.theforbiddenai.trelloapiwrapper.utils.LimitOptions

private val gson = GsonBuilder().serializeNulls().create()

class Board {

    internal lateinit var trelloApi: TrelloApi

    val id: String = ""
    val name: String = ""
    val desc: String = ""
    val descData = gson.fromJson("", DescData::class.java)
    val closed: Boolean = false
    val idOrganization: String = ""
    val idEnterprise: String = ""
    val limits = gson.fromJson("", Limits::class.java)
    val pinned: Boolean = false
    val starred: Boolean = false
    val url: String = ""
    val prefs = gson.fromJson("", MyPrefs::class.java)
    val memberships = gson.fromJson("", Array<Membership>::class.java)
    val subscribed: String = ""
    val labelNames = gson.fromJson("", mapOf<String, String>()::class.java)
    val powerUps: ArrayList<String> = arrayListOf()
    val dateLastActivity: String = ""
    val dateLastView: String = ""
    val shortUrl: String = ""
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

        val attachments = gson.fromJson("", AttachmentLimits::class.java)
        val boards = gson.fromJson("", BoardLimits::class.java)
        val cards = gson.fromJson("", CardLimits::class.java)
        val checklists = gson.fromJson("", CheckListLimits::class.java)
        val checkItems = gson.fromJson("", CheckItemLimits::class.java)
        val customFields = gson.fromJson("", CustomFieldLimits::class.java)
        val customFieldOptions = gson.fromJson("", CustomFieldOptionLimits::class.java)
        val labels = gson.fromJson("", LabelLimits::class.java)
        val lists = gson.fromJson("", ListLimits::class.java)
        val stickers = gson.fromJson("", StickerLimits::class.java)
        val reactions = gson.fromJson("", ReactionLimits::class.java)

        class AttachmentLimits {

            val perBoard = gson.fromJson("", LimitOptions::class.java)
            val perCard = gson.fromJson("", LimitOptions::class.java)
        }

        class BoardLimits {

            val totalMembersPerBoard = gson.fromJson("", LimitOptions::class.java)

        }

        class CardLimits {

            val openPerBoard = gson.fromJson("", LimitOptions::class.java)
            val openPerList = gson.fromJson("", LimitOptions::class.java)
            val totalPerBoard = gson.fromJson("", LimitOptions::class.java)
            val totalPerList = gson.fromJson("", LimitOptions::class.java)
        }

        class CheckListLimits {

            val perBoard = gson.fromJson("", LimitOptions::class.java)
            val perCard = gson.fromJson("", LimitOptions::class.java)

        }

        class CheckItemLimits {

            val perChecklist = gson.fromJson("", LimitOptions::class.java)

        }

        class CustomFieldLimits {

            val perBoard = gson.fromJson("", LimitOptions::class.java)

        }

        class CustomFieldOptionLimits {

            val perField = gson.fromJson("", LimitOptions::class.java)

        }

        class LabelLimits {

            val perBoard = gson.fromJson("", LimitOptions::class.java)

        }

        class ListLimits {

            val openPerBoard = gson.fromJson("", LimitOptions::class.java)
            val totalPerBoard = gson.fromJson("", LimitOptions::class.java)

        }

        class StickerLimits {

            val perCard = gson.fromJson("", LimitOptions::class.java)

        }

        class ReactionLimits {

            val perAction = gson.fromJson("", LimitOptions::class.java)
            val uniquePerAction = gson.fromJson("", LimitOptions::class.java)

        }

    }
}