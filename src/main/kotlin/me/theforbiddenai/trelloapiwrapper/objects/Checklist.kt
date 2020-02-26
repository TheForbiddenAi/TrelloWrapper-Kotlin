package me.theforbiddenai.trelloapiwrapper.objects

class Checklist : TrelloObject() {

    val id: String = ""
    val name: String = ""
    val idBoard: String = ""
    val idCard: String = ""
    val pos: Float = 0F
    val checkItems: Array<CheckItem> = arrayOf()

    // TODO: Implement the rest of the get, put, and delete functions

    class CheckItem {
        val idChecklist: String = ""
        val state: String = ""
        val id: String = ""
        val name: String = ""
    }
}