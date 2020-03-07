package me.theforbiddenai.trelloapiwrapper.utils

import me.theforbiddenai.trelloapiwrapper.objects.CustomField
import java.util.*

class LimitOptions {

    val status: String = "null"
    val disableAt: Int = -1
    val warnAt: Int = -1

}

class DescData {
    val emoji: Map<String, String> = mapOf()
}

class ShortMember {

    val id: String = ""
    val activityBlocked: Boolean = false
    val avatarHash: String = ""
    val avatarUrl: String = ""
    val fullName: String = ""
    val idMemberReferrer: String = ""
    val initials: String = ""
    val username: String = ""

}

class DataObject {
    val idMember: String = ""
    val fromCopy: Boolean = false
    val text: String = ""
    val card: LimitedDataView = LimitedDataView()
    val cardSource: LimitedDataView = LimitedDataView()
    val board: LimitedDataView = LimitedDataView()
    val boardSource: BoardIdData = BoardIdData()
    val boardTarget: BoardIdData = BoardIdData()
    val list: LimitedDataView = LimitedDataView()
    val listBefore: LimitedDataView = LimitedDataView()
    val listAfter: LimitedDataView = LimitedDataView()
    val member: LimitedDataView = LimitedDataView()
    val customField: CustomField = CustomField()
    val customFieldItem: LimitCustomFieldItem = LimitCustomFieldItem()
    val checklist: LimitedDataView = LimitedDataView()
    val checkItem: LimitedDataView = LimitedDataView()
    val plugin: LimitedDataView = LimitedDataView()
    val attachment: LimitedDataView = LimitedDataView()
    val textData: DescData = DescData()
    val old: OldData = OldData()

    class BoardIdData {
        val id: String = ""
    }

    class LimitedDataView {
        val id: String = ""
        val name: String = ""
    }

    class LimitCustomFieldItem {
        val id: String = ""
        val value: CustomFieldItemValue = CustomFieldItemValue()
        val idCustomField: String = ""
        val modelType: String = ""

        class CustomFieldItemValue {
            val date: Date? = null
        }
    }

    class PrefData {
        val background: String = ""
        val invitations: String = ""
    }

    class OldData {
        val options: Array<CustomField.CustomFieldOption> = arrayOf()
        val dueComplete: Boolean = false
        val idList: String = ""
        val pos: Float = 0F
        val closed: Boolean = false
        val name: String = ""
        val softLimit: String = ""
        val prefs: PrefData = PrefData()
        val value: String = ""
        val due: String = ""
        val dueReminder: String = ""
        val desc: String = ""
    }
}

class Sticker {
    val id: String = ""
    val top: Float = 0F
    val left: Float = 0F
    val zIndex: Int = 0
    val rotate: Float = 0F
    val image: String = ""
    val imageUrl: String = ""
}

class OptionValue {
    var text: String = ""
}