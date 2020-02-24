package me.theforbiddenai.trelloapiwrapper.objects

class CustomField : TrelloObject() {

    val id: String = ""
    val idModel: String = ""
    val modelType: String = ""
    val fieldGroup: String = ""
    val display: Display = Display()
    val name: String = ""
    val pos: Float = 0F
    val options: Array<Option> = arrayOf()
    val type: String = ""

    // TODO: Implement the rest of the get, put, and delete functions

    class Display {

        val cardFront: Boolean = false

    }

    class Option {

        val id: String = ""
        val idCustomField: String = ""
        val value = OptionValue()
        val color: String = ""
        val pos: Float = 0F

        class OptionValue {
            val text: String = ""
        }

    }
}