package me.theforbiddenai.trelloapiwrapper.utils

import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.google.gson.JsonParser

class HttpRequests {

    /**
     * Retrieves the json data from the given url
     *
     * @param url The url the json data is coming from
     * @return The found json data in the form of a string
     */
    internal fun getJsonData(url: String): String {
        val (_, response, result) = url.httpGet().responseString()

        return when (result) {
            is Result.Success -> {
                result.get()
            }

            is Result.Failure -> {
                ""
            }

        }
    }

    /**
     * Sends a put request with a given payload to a given url
     *
     * @param url The url the payload is going to
     * @param payload The payload being sent to the url
     * @return The found json data in the form of a string
     */
    internal fun postJsonData(url: String): String {
        val (_, response, result) = url.httpPost().responseString()

        return when (result) {
            is Result.Success -> {
                result.get()
            }

            is Result.Failure -> {
                throw IllegalArgumentException(getErrorMessage(response))
            }
        }

    }

    /**
     * Sends a delete request to a given url
     *
     * @param url The url the delete request is going to
     */
    internal fun deleteJsonData(url: String) {
        val (_, response, result) = url.httpDelete().responseString()

        if (result is Result.Failure) {
            throw IllegalArgumentException(getErrorMessage(response))
        }

    }

    /**
     * Sends a put request with a given payload to a given url
     *
     * @param url The url the payload is going to
     * @param payload The payload being sent to the url
     */
    internal fun putJsonData(url: String, payload: String) {
        val (_, response, result) = url.httpPut()
            .header("Content-Type" to "application/json")
            .body(payload)
            .responseString()

        if (result is Result.Failure) {
            throw IllegalArgumentException(getErrorMessage(response))
        }

    }

    /**
     * Retrieves the error messages for put, post, and delete requests
     *
     * @param response The http response
     * @return The found error string
     */
    private fun getErrorMessage(response: Response): String {
        val errorJsonString = response.data.toString(Charsets.UTF_8)
        val errorJsonObject = JsonParser.parseString(errorJsonString).asJsonObject

        return errorJsonObject.get("message").asString.capitalize()
    }

}