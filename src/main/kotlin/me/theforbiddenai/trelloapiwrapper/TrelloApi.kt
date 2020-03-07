package me.theforbiddenai.trelloapiwrapper

import com.google.gson.GsonBuilder
import me.theforbiddenai.trelloapiwrapper.objects.*
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
        return getTrelloObject(actionUrl)
    }

    fun getBoard(boardId: String): Board {
        val boardUrl = "$baseApiUrl/boards/$boardId?fields=all&$credentials"
        return getTrelloObject(boardUrl)
    }

    fun getCard(cardId: String): Card {
        val cardUrl = "$baseApiUrl/cards/$cardId?$credentials"
        return getTrelloObject(cardUrl)
    }

    fun getCheckList(checklistId: String): Checklist {
        val checklistUrl = "$baseApiUrl/checklists/$checklistId?$credentials"
        return getTrelloObject(checklistUrl)
    }

    fun getCustomField(customFieldId: String): CustomField {
        val customFieldUrl = "$baseApiUrl/customFields/$customFieldId?$credentials"
        return getTrelloObject(customFieldUrl)
    }

    fun getEnterprise(enterpriseId: String): Enterprise {
        val enterpriseUrl = "$baseApiUrl/enterprises/$enterpriseId?$credentials"
        return getTrelloObject(enterpriseUrl)
    }

    fun getLabel(labelId: String): Label {
        val labelUrl = "$baseApiUrl/labels/$labelId?$credentials"
        return getTrelloObject(labelUrl)
    }

    fun getList(listId: String): TrelloList {
        val labelUrl = "$baseApiUrl/lists/$listId?$credentials"
        return getTrelloObject(labelUrl)
    }

    fun getMember(memberId: String): Member {
        val memberUrl = "$baseApiUrl/members/$memberId?fields=all&$credentials"
        return getTrelloObject(memberUrl)
    }

    fun getNotification(notificationId: String): Notification {
        val notificationUrl = "$baseApiUrl/notifications/$notificationId?$credentials"
        return getTrelloObject(notificationUrl)
    }

    fun getOrganization(organizationId: String): Organization {
        val notificationUrl = "$baseApiUrl/organizations/$organizationId?$credentials"
        return getTrelloObject(notificationUrl)
    }

    fun getReactions(actionId: String): Array<Reaction> {
        val notificationUrl = "$baseApiUrl/actions/$actionId/reactions?$credentials"
        return getTrelloObjectArray(notificationUrl)
    }

    /**
     * Deserializes the json from a given url into a given class that inherits from TrelloObject
     *
     * @param url The object's url
     * @return The found trello object
     */
    internal inline fun <reified T> getTrelloObject(url: String): T {
        val jsonData = httpRequests.getRequest(url)
        val clazz = T::class.java

        if (jsonData.isEmpty()) {
            val clazzName: String = clazz.simpleName.toLowerCase()
            val checkVowel: String = if (clazzName.matches("^[aeiouy].*".toRegex())) "an" else "a"
            throw IllegalArgumentException("Failed to find $checkVowel $clazzName with the given id")
        }

        val trelloObject = gson.fromJson(jsonData, clazz)
        if (trelloObject is TrelloObject) {
            trelloObject.trelloApi = this
        }

        return trelloObject
    }

    /**
     * Deserializes the json from a given url into an array of the given class that inherits from TrelloObject
     *
     * @param url The object's url
     * @return An array of the found trello objects
     */
    internal inline fun <reified T> getTrelloObjectArray(url: String): Array<T> {
        val jsonData = httpRequests.getRequest(url)

        val objectArray: Array<T> = gson.fromJson(jsonData, arrayOf<T>()::class.java) ?: return emptyArray()
        if (objectArray.isArrayOf<TrelloObject>()) {
            objectArray as Array<TrelloObject>

            objectArray.forEach { it.trelloApi = this }
        }


        return objectArray

    }


}

