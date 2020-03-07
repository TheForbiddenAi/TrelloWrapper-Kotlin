package me.theforbiddenai.trellowrapperkotlin.objects

import me.theforbiddenai.trellowrapperkotlin.TrelloApi
import me.theforbiddenai.trellowrapperkotlin.utils.OptionValue

class CustomField internal constructor() : TrelloObject() {

    constructor(
        trelloApi: TrelloApi,
        boardId: String,
        name: String,
        type: CustomFieldTypes,
        pos: Float
    ) : this() {
        this.trelloApi = trelloApi
        this.idModel = boardId
        this.name = name
        this.type = type.toString().toLowerCase()
        this.pos = pos
        this.modelType = "board"
    }

    val id: String = ""
    var idModel: String = ""
        private set
    var modelType: String = ""
        private set
    val fieldGroup: String = ""
    val display: Display = Display()
    var name: String = ""
    var pos: Float = 0F
    val options: Array<CustomFieldOption>? = null
    var type: String = ""

    fun getOptionById(optionId: String): CustomFieldOption {
        val optionUrl = "${trelloApi.baseApiUrl}/customFields/$id/options/$optionId?${trelloApi.credentials}"
        return getTrelloObject(optionUrl)
    }

    fun updateCustomField() {
        val json = trelloApi.gson.toJson(this)
        val updateCustomFieldUrl = "${trelloApi.baseApiUrl}/customFields/$id?${trelloApi.credentials}"

        trelloApi.httpRequests.putRequest(updateCustomFieldUrl, json)
    }

    fun createCustomField(): CustomField {
        val json = trelloApi.gson.toJson(this)
        val jsonString = json.toString()

        val createCustomFieldUrl = "${trelloApi.baseApiUrl}/customFields?${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createCustomFieldUrl, jsonString)
        return createObjectFromJson(result)
    }

    fun createCustomFieldOption(option: CustomFieldOption): CustomFieldOption {
        val json = trelloApi.gson.toJson(option)
        val jsonString = json.toString()

        val createCustomFieldOptionUrl = "${trelloApi.baseApiUrl}/customFields/$id/options?${trelloApi.credentials}"

        val result = trelloApi.httpRequests.postRequest(createCustomFieldOptionUrl, jsonString)
        return createObjectFromJson(result)
    }

    fun deleteCustomField() {
        val deleteCustomFieldUrl = "${trelloApi.baseApiUrl}/customFields/$id?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteCustomFieldUrl)
    }

    fun deleteCustomFieldOption(optionId: String) {
        val deleteCustomFieldOptionUrl =
            "${trelloApi.baseApiUrl}/customFields/$id/options/$optionId?${trelloApi.credentials}"
        trelloApi.httpRequests.deleteRequest(deleteCustomFieldOptionUrl)
    }

    class Display {
        var cardFront: Boolean = false
    }

    class CustomFieldOption {
        val id: String = ""
        val idCustomField: String = ""
        var value = OptionValue()
        var color: String = ""
        var pos: Float = 0F
    }

}

enum class CustomFieldTypes {
    LIST(),
    CHECKBOX(),
    DATE(),
    NUMBER(),
    TEXT()
}