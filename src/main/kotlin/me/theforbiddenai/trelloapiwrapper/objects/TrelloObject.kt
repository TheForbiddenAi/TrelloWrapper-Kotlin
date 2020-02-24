package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.TrelloApi

abstract class TrelloObject {

    @Transient
    internal lateinit var trelloApi: TrelloApi

    internal inline fun <reified T : TrelloObject> getObject(url: String): T {
        return trelloApi.getObject(url)
    }

    internal inline fun <reified T : TrelloObject> getObjectArray(url: String): Array<T> {
        return trelloApi.getObjectArray(url)
    }

}