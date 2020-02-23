package me.theforbiddenai.trelloapiwrapper

import com.google.gson.GsonBuilder
import me.theforbiddenai.trelloapiwrapper.objects.*
import me.theforbiddenai.trelloapiwrapper.utils.HttpRequests


class TrelloApi(
    apiKey: String,
    token: String
) {

    private val gson = GsonBuilder().serializeNulls().create()

    internal val httpRequests = HttpRequests()
    internal val baseApiUrl: String = "https://api.trello.com/1"
    internal val credentials: String = "key=$apiKey&token=$token"

    fun getAction(actionId: String): Action {
        val actionUrl = "$baseApiUrl/actions/$actionId?$credentials"

        val action = getObjectInternal(Action::class.java, actionUrl)
        action.trelloApi = this
        action.display.entities = gson.fromJson(action.display.rawEntities, Action.Display.Entities::class.java)

        return action
    }

    fun getBoard(boardId: String): Board {
        val boardUrl = "$baseApiUrl/boards/$boardId?fields=all&$credentials"
        return getBoardInternal(boardUrl)
    }

    fun getCard(cardId: String): Card {
        val cardUrl = "$baseApiUrl/cards/$cardId?$credentials"

        val card = getObjectInternal(Card::class.java, cardUrl)
        card.trelloApi = this

        return card
    }

    fun getCheckList(checklistId: String): Checklist {
        val checklistUrl = "$baseApiUrl/checklists/$checklistId?$credentials"

        val checklist = getObjectInternal(Checklist::class.java, checklistUrl)
        checklist.trelloApi = this

        return checklist
    }

    fun getCustomField(customFieldId: String): CustomField {
        val customFieldUrl = "$baseApiUrl/customFields/$customFieldId?$credentials"

        val customField = getObjectInternal(CustomField::class.java, customFieldUrl)
        customField.trelloApi = this

        return customField
    }

    fun getEnterprise(enterpriseId: String): Enterprise {
        val enterpriseUrl = "$baseApiUrl/enterprises/$enterpriseId?$credentials"

        val enterprise = getObjectInternal(Enterprise::class.java, enterpriseUrl)
        enterprise.trelloApi = this

        return enterprise
    }

    fun getLabel(labelId: String): Label {
        val labelUrl = "$baseApiUrl/labels/$labelId?$credentials"

        val label = getObjectInternal(Label::class.java, labelUrl)
        label.trelloApi = this

        return label
    }


    /**
     * Deserializes the json from a given url into a given class
     *
     * @param clazz The class the json is being deserialized to
     * @param url The object's url
     * @return The found object
     */
    private fun <T> getObjectInternal(clazz: Class<T>, url: String): T {
        val jsonData = httpRequests.getJsonData(url)

        if (jsonData.isEmpty()) {
            val clazzName: String = clazz.simpleName.toLowerCase()
            val checkVowel: String = if (clazzName.matches("^[aeiouy].*".toRegex())) "an" else "a"
            throw IllegalArgumentException("Failed to find $checkVowel $clazzName with the given id")
        }

        return gson.fromJson(jsonData, clazz)
    }

    internal fun getBoardInternal(url: String): Board {
        val board = getObjectInternal(Board::class.java, url)
        board.trelloApi = this

        return board
    }


}

