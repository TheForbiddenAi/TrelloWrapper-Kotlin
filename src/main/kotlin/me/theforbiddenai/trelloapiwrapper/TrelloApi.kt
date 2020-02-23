package me.theforbiddenai.trelloapiwrapper

import com.google.gson.GsonBuilder
import me.theforbiddenai.trelloapiwrapper.objects.*
import me.theforbiddenai.trelloapiwrapper.objects.List
import me.theforbiddenai.trelloapiwrapper.utils.HttpRequests


class TrelloApi(
    apiKey: String,
    token: String
) {

    internal val gson = GsonBuilder().serializeNulls().create()

    internal val httpRequests = HttpRequests()
    internal val baseApiUrl: String = "https://api.trello.com/1"
    internal val credentials: String = "key=$apiKey&token=$token"

    fun getAction(actionId: String): Action {
        val actionUrl = "$baseApiUrl/actions/$actionId?$credentials"

        val action = getObject<Action>(actionUrl)
        action.display.entities = gson.fromJson(action.display.rawEntities, Action.Display.Entities::class.java)

        return action
    }

    fun getBoard(boardId: String): Board {
        val boardUrl = "$baseApiUrl/boards/$boardId?fields=all&$credentials"
        return getObject(boardUrl)
    }

    fun getCard(cardId: String): Card {
        val cardUrl = "$baseApiUrl/cards/$cardId?$credentials"
        return getObject(cardUrl)
    }

    fun getCheckList(checklistId: String): Checklist {
        val checklistUrl = "$baseApiUrl/checklists/$checklistId?$credentials"
        return getObject(checklistUrl)
    }

    fun getCustomField(customFieldId: String): CustomField {
        val customFieldUrl = "$baseApiUrl/customFields/$customFieldId?$credentials"
        return getObject(customFieldUrl)
    }

    fun getEnterprise(enterpriseId: String): Enterprise {
        val enterpriseUrl = "$baseApiUrl/enterprises/$enterpriseId?$credentials"
        return getObject(enterpriseUrl)
    }

    fun getLabel(labelId: String): Label {
        val labelUrl = "$baseApiUrl/labels/$labelId?$credentials"
        return getObject(labelUrl)
    }

    fun getList(listId: String): List {
        val labelUrl = "$baseApiUrl/lists/$listId?$credentials"
        return getObject(labelUrl)
    }

    /**
     * Deserializes the json from a given url into a given class that inherits from TrelloObject
     *
     * @param url The object's url
     * @return The found trello object
     */
    internal inline fun <reified T : TrelloObject> getObject(url: String): T {
        val jsonData = httpRequests.getRequest(url)
        val clazz = T::class.java

        if (jsonData.isEmpty()) {
            val clazzName: String = clazz.simpleName.toLowerCase()
            val checkVowel: String = if (clazzName.matches("^[aeiouy].*".toRegex())) "an" else "a"
            throw IllegalArgumentException("Failed to find $checkVowel $clazzName with the given id")
        }

        val trelloObject = gson.fromJson(jsonData, clazz)
        trelloObject.trelloApi = this

        return trelloObject
    }


}

