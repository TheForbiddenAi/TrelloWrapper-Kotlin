package me.theforbiddenai.trelloapiwrapper

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.GsonBuilder
import me.theforbiddenai.trelloapiwrapper.objects.Action
import me.theforbiddenai.trelloapiwrapper.objects.Board


class TrelloApi(
    apiKey: String,
    token: String
) {

    private val gson = GsonBuilder().serializeNulls().create()
    internal val baseApiUrl: String = "https://api.trello.com/1"
    internal val credentials: String = "key=$apiKey&token=$token"

    fun getAction(actionId: String): Action {
        val actionUrl = "$baseApiUrl/actions/$actionId?$credentials"

        val actionJsonData = getJsonData(actionUrl)
        if (actionJsonData.isEmpty()) {
            throw IllegalArgumentException("Failed to find an action with the given id")
        }

        val action = gson.fromJson(actionJsonData, Action::class.java)
        action.trelloApi = this
        action.display.entities = gson.fromJson(action.display.rawEntities, Action.Display.Entities::class.java)

        return action

    }

    fun getBoard(boardId: String): Board {
        val boardUrl = "$baseApiUrl/boards/$boardId?fields=all&$credentials"
        return getBoardInternal(boardUrl)
    }

    /**
     * Deserializes the json into a board object from a given url
     *
     * @param url The complete board url
     * @return The found board
     */
    internal fun getBoardInternal(url: String): Board {
        val boardJsonData = getJsonData(url)
        if (boardJsonData.isEmpty()) {
            throw IllegalArgumentException("Failed to find a board with the given id")
        }

        val board = gson.fromJson(getJsonData(url), Board::class.java)
        board.trelloApi = this

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

