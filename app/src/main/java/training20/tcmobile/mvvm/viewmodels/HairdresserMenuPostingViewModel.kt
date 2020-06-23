package training20.tcmobile.mvvm.viewmodels


import training20.tcmobile.mvvm.actions.HairdresserMenuPostingActions
import training20.tcmobile.mvvm.event.EventDispatcher


// viewを操作してはいけない
// 施術内容を表示させる ←イマココ
// 入力内容を持つところ

class response_sample(
    val id:Int,
    val name:String
)

class HairdresserMenuPostingViewModel(
    eventDispatcher: EventDispatcher<HairdresserMenuPostingActions>
): BackableViewModel<HairdresserMenuPostingActions>(eventDispatcher)
{
    fun start() {
        //　リポジトリからデータ撮ってくる処理
        val id = ""
        val name = ""
        val res = arrayOf(response_sample(1, "施術1"),
            response_sample(2, "施術2"), response_sample(3, "施術3"),
            response_sample(4, "施術4"), response_sample(5, "施術5"))
    }

    fun dataCatch(response:Array<response_sample>) {
        // 処理省略
        eventDispatcher.dispatchEvent { onTreatmentChanged() }
    }
}
