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