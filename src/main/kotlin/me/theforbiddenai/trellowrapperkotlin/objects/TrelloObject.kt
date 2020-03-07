package me.theforbiddenai.trellowrapperkotlin.objects

import me.theforbiddenai.trellowrapperkotlin.TrelloApi

abstract class TrelloObject {

    @Transient
    internal lateinit var trelloApi: TrelloApi

    internal inline fun <reified T> getTrelloObject(url: String): T {
        return trelloApi.getTrelloObject(url)
    }


    internal inline fun <reified T> getTrelloObjectArray(url: String): Array<T> {
        return trelloApi.getTrelloObjectArray(url)
    }

    internal inline fun <reified T> createObjectFromJson(json: String): T {
        val foundObject = trelloApi.gson.fromJson(json, T::class.java)

        if (foundObject is TrelloObject) {
            foundObject.trelloApi = trelloApi
        }

        return foundObject
    }

}