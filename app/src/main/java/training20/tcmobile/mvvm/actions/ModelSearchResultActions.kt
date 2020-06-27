package training20.tcmobile.mvvm.actions

interface ModelSearchResultActions: Action {

    fun onMenuSearchSuccess()

    fun showMenu(menuId: Int)
    fun showMenuSearch()
}