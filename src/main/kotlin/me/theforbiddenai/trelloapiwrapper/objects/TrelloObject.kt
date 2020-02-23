package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.Gson
import me.theforbiddenai.trelloapiwrapper.TrelloApi

abstract class TrelloObject {

    @Transient
    internal lateinit var trelloApi: TrelloApi

    internal inline fun <reified T : TrelloObject> getObject(url: String): T {
        return trelloApi.getObject(url)
    }

    internal inline fun <reified T : TrelloObject> getObjectArray(url: String): Array<T> {
        val gson: Gson = trelloApi.gson
        val jsonData = trelloApi.httpRequests.getRequest(url)

        return try {
            val objectArray: Array<T> = gson.fromJson(jsonData, arrayOf<T>()::class.java)
            objectArray.forEach { it.trelloApi = trelloApi }

            objectArray
        } catch (ex: IllegalStateException) {
            emptyArray()
        }
    }

}