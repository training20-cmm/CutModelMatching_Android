package training20.tcmobile

object RoleManager {

    private var role: Role? = null

    fun current(): Role? {
        return role
    }

    fun setRole(role: Role?) {
        this.role = role
    }
}