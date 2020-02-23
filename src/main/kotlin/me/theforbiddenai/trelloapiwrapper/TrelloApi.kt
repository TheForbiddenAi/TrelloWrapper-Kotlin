package me.theforbiddenai.trelloapiwrapper

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.GsonBuilder
import me.theforbiddenai.trelloapiwrapper.objects.Action
import me.theforbiddenai.trelloapiwrapper.objects.Board
import me.theforbiddenai.trelloapiwrapper.objects.Card


class TrelloApi(
    apiKey: String,
    token: String
) {

    private val gson = GsonBuilder().serializeNulls().create()
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

    /**
     * Deserializes the json from a given url into a given class
     *
     * @param clazz The class the json is being deserialized to
     * @param url The object's url
     * @return The found object
     */
    internal fun <T> getObjectInternal(clazz: Class<T>, url: String): T {
        val jsonData = getJsonData(url)

        if (jsonData.isEmpty()) {
            val clazzName: String = clazz.simpleName.toLowerCase()
            throw IllegalArgumentException("Failed to find a(n) $clazzName with the given id")
        }

        return gson.fromJson(getJsonData(url), clazz)
    }

    internal fun getBoardInternal(url: String): Board {
        val board = getObjectInternal(Board::class.java, url)
        board.trelloApi = this;

        return board
    }

    /**
     * Retrieves the json data from the given url
     *
     * @param url The url the json data is coming from
     * @return The found json data in the form of a string
     */
    internal fun getJsonData(url: String): String {
        val (_, _, result) = url.httpGet().responseString()

        return when (result) {
            is Result.Success -> {
                result.get()
            }

            is Result.Failure -> {
                ""
            }

        }
    }

}

