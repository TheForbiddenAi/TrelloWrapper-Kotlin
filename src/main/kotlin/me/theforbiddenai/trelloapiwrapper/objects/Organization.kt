package me.theforbiddenai.trelloapiwrapper.objects

import me.theforbiddenai.trelloapiwrapper.utils.DescData
import me.theforbiddenai.trelloapiwrapper.utils.LimitOptions

class Organization : TrelloObject() {

    val id: String = ""
    val name: String = ""
    val displayName: String = ""
    val desc: String = ""
    val descData: DescData = DescData()
    val idBoards: Array<String> = arrayOf()
    val idEnterprise: String = ""
    val idMemberCreator: String = ""
    val invited: Boolean = false
    val invitations: Array<String> = arrayOf()
    val limits: OrganizationLimits = OrganizationLimits()
    val memberships: Array<Board.Membership> = arrayOf()
    val prefs: OrganizationPrefs = OrganizationPrefs()
    val powerUps: Array<Int> = arrayOf()
    val products: Array<Int> = arrayOf()
    val billableMemberCount: String = ""
    val activeBillableMemberCount: String = ""
    val billableCollaboratorCount: String = ""
    val url: String = ""
    val website: String = ""
    val logoHash: String = ""
    val logoUrl: String = ""
    val premiumFeatures: Array<String> = arrayOf()

    class OrganizationLimits {
        class OrgLimit {
            val totalMembersPerOrg: LimitOptions = LimitOptions()
            val freeBoardsPerOrg: LimitOptions = LimitOptions()
        }
    }

    class OrganizationPrefs {
        val permissionLevel: String = ""
        val orgInviteRestrict: Array<String> = arrayOf()
        val boardInviteRestrict: String = ""
        val externalMembersDisabled: Boolean = false
        val associatedDomain: String = ""
        val googleAppsVersion: Int = 0
        val boardVisibilityRestrict: Restrictions = Restrictions()
        val boardDeleteRestrict: Restrictions = Restrictions()
        val attachmentRestrictions: Restrictions = Restrictions()

        class Restrictions {
            val private: String = ""
            val org: String = ""
            val enterprise: String = ""
            val public: String = ""
        }
    }
}