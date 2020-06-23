package training20.tcmobile.mvvm.viewmodels


import training20.tcmobile.mvvm.actions.HairdresserMenuPostingActions
import training20.tcmobile.mvvm.event.EventDispatcher


// viewを操作してはいけない
// 施術内容を表示させる ←イマココ
// 入力内容を持つところ

class HairdresserMenuPostingViewModel(
    eventDispatcher: EventDispatcher<HairdresserMenuPostingActions>
): BackableViewModel<HairdresserMenuPostingActions>(eventDispatcher)
{

     // 以下、受け取るべきデータ
    val title = ""
    val model_gender  = ""
    // val tools = ""
    val date = ""
    val time = ""
    val time_required = ""
    val fee = ""
    val comments = ""


    fun initParams() { //初期表示
        /*
        1. ログインしている美容師さんの名前、所属サロン情報、プロフィール画像をげと
        2. (本当は)施術内容をげっとする
         */
    }
}
