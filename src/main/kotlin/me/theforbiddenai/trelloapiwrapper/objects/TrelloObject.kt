package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.TrelloApi

abstract class TrelloObject {

    @Transient
    internal lateinit var trelloApi: TrelloApi

    internal inline fun <reified T : TrelloObject> getTrelloObject(url: String): T {
        return trelloApi.getObject(url)
    }

    internal inline fun <reified T : TrelloObject> getTrelloObjectArray(url: String): Array<T> {
        return trelloApi.getObjectArray(url)
    }

    internal inline fun <reified T> getObject(url: String): T {
        val json = trelloApi.httpRequests.getRequest(url)
        return trelloApi.gson.fromJson(json, T::class.java)
    }

    internal inline fun <reified T> getObjectArray(url: String): Array<T> {
        val json = trelloApi.httpRequests.getRequest(url)
        return trelloApi.gson.fromJson(json, arrayOf<T>()::class.java) ?: emptyArray()
    }

    internal inline fun <reified T : TrelloObject> createObjectFromJson(json: String): T {
        val trelloObject = trelloApi.gson.fromJson(json, T::class.java)
        trelloObject.trelloApi = trelloApi

        return trelloObject
    }

}