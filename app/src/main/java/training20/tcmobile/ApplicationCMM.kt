package training20.tcmobile

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import training20.tcmobile.mvvm.actions.HairdresserChatHistoryActions
import training20.tcmobile.mvvm.actions.HairdresserHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.*
import training20.tcmobile.mvvm.viewmodels.*

class ApplicationCMM: Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.onCreateApplication(applicationContext)

        //
        if (BuildConfig.DEBUG) {
            Debugger.debug(Role.HAIRDRESSER, "uShHjzAz60uPmdQZxEUxGj0s0MGSOk7aLrTuYf75LyA2Y8s7SMbVsFRFoT8F")
        }
        //
        val eventDispatcherModule = module {
            factory { EventDispatcher<HairdresserChatHistoryActions>() }
        }
        val repositoryModule = module {
            factory { ChatRoomRepositoryHttp() as ChatRoomRepositoryContract }
            factory { HairdresserRepositoryHttp() as HairdresserRepositoryContract }
            factory { ModelRepositoryHttp() as ModelRepositoryContract }
            factory { HairdresserHomeRepository() as HairdresserHomeRepositoryContract }
        }
        val viewModelModule = module {
            viewModel { HairdresserRegistrationFormViewModel(get(), get()) }
            viewModel { HairdresserFoundationViewModel(get()) }
            viewModel { HairdresserHomeViewModel(get(), get()) }
            viewModel { HairdresserChatHistoryViewModel(get(), get()) }
            viewModel { HairdresserHairstyleListViewModel(get()) }
            viewModel { HairdresserHairstylePostingViewModel(get()) }
            viewModel { HairdresserMenuPostingViewModel(get()) }
            viewModel { HairdresserSalonViewModel(get()) }
            viewModel { HairdresserSalonUnregisteredViewModel(get()) }
            viewModel { HairdresserSalonRegistrationViewModel(get()) }
            viewModel { HairdresserChatRoomViewModel(get()) }
            viewModel { ModelRegistrationFormViewModel(get(), get()) }
            viewModel { ModelFoundationViewModel(get()) }
            viewModel { ModelHomeViewModel(get()) }
            viewModel { ModelChatHistoryViewModel(get()) }
            viewModel { ModelNotificationsViewModel(get()) }
            viewModel { ModelMenuSearchViewModel(get()) }
            viewModel { ModelMenuViewModel(get()) }
            viewModel { ModelChatRoomViewModel(get()) }
        }
        startKoin {
            androidContext(this@ApplicationCMM)
            modules(listOf(eventDispatcherModule, repositoryModule, viewModelModule))
        }
    }
}