package me.theforbiddenai.trelloapiwrapper.objects

import com.google.gson.GsonBuilder
import me.theforbiddenai.trelloapiwrapper.TrelloApi

private val gson = GsonBuilder().serializeNulls().create()

class CustomField {

    internal lateinit var trelloApi: TrelloApi

    val id: String = ""
    val idModel: String = ""
    val modelType: String = ""
    val fieldGroup: String = ""
    val display: Display = Display()
    val name: String = ""
    val pos: Float = 0F
    val options: Array<Option> = arrayOf()
    val type: String = ""

    class Display {

        val cardFront: Boolean = false

    }

    class Option {

        val id: String = ""
        val idCustomField: String = ""
        val value = Value()
        val color: String = ""
        val pos: Float = 0F

        class Value {
            val text: String = ""
        }

    }
}