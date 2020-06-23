package training20.tcmobile.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.fragment_hairdresser_menu_posting.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserMenuPostingBinding
import training20.tcmobile.mvvm.actions.HairdresserMenuPostingActions
import training20.tcmobile.mvvm.viewmodels.HairdresserMenuPostingViewModel
import training20.tcmobile.ui.recyclerview.adapters.HairdresserMenuPostingRecyclerViewAdpter


//　viewを操作するところ
// イベント(onclickとか)はこっち

class HairdresserMenuPostingFragment :
    BackableFragment<HairdresserMenuPostingActions, FragmentHairdresserMenuPostingBinding, HairdresserMenuPostingViewModel>(),
    HairdresserMenuPostingActions
{
    override val viewModel: HairdresserMenuPostingViewModel by inject()

    private val airdresserMenuPostingRecyclerViewAdapter = HairdresserMenuPostingRecyclerViewAdpter(viewModel)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 最初に画面表示するためのものを書いていこう
        // チェックボックスを表示させるための処理
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        val menupostingRecycler = view.orderRecycle_view

        return inflater.inflate(R.layout.fragment_hairdresser_menu_posting, container, false)
    }

    // flexboxLayoutのセットアップ
    fun layoutSetup() {
        val flexboxLayoutManager = FlexboxLayoutManager(activity)
        flexboxLayoutManager.flexDirection = FlexDirection.COLUMN
        flexboxLayoutManager.flexWrap = FlexWrap.WRAP
        flexboxLayoutManager.justifyContent = JustifyContent.SPACE_AROUND
        flexboxLayoutManager.alignItems = AlignItems.STRETCH
    }

    //　写真選択&表示するメソッドとりあえず置いた
    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        intent.type = "image/*"
        startActivityForResult(intent, READ_REQUEST_CODE)

    }

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?){
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode != RESULT_OK) {
            return
        }
        when(requestCode) {
            READ_REQUEST_CODE -> {
                try { //　画像を画面に表示する
                    resultData?.data?.also{

                    }
                }catch (e: Exception){
                    //エラー発生時のハンドリング。
                }
            }
        }
    }


    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserMenuPostingBinding = FragmentHairdresserMenuPostingBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserMenuPostingViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
        viewModel.start()
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserMenuPostingBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    //　ViewModelから情報を取得したお知らせをもらう
    // もらったら画面を再構成する(チェックボックス表示のため)
    override fun onTreatmentChanged() {
        airdresserMenuPostingRecyclerViewAdapter.notifyDataSetChanged()
    }
}