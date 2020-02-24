package me.theforbiddenai.trelloapiwrapper.objects

class Checklist : TrelloObject() {

    val id: String = ""
    val name: String = ""
    val idBoard: String = ""
    val idCard: String = ""
    val pos: Float = 0F
    val checkItems: Array<CheckItems> = arrayOf()

    // TODO: Implement the rest of the get, put, and delete functions

    class CheckItems {
        val idChecklist: String = ""
        val state: String = ""
        val id: String = ""
        val name: String = ""
    }
}