package training20.tcmobile

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import training20.tcmobile.mvvm.actions.HairdresserChatHistoryActions
import training20.tcmobile.mvvm.actions.HairdresserHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.ChatRoomRepositoryContract
import training20.tcmobile.mvvm.repositories.ChatRoomRepositoryHttp
import training20.tcmobile.mvvm.repositories.HairdresserHomeRepository
import training20.tcmobile.mvvm.repositories.HairdresserHomeRepositoryContract
import training20.tcmobile.mvvm.viewmodels.*

class ApplicationCMM: Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.onCreateApplication(applicationContext)

        //
        Debugger.debug(Role.HAIRDRESSER, "tNclJwnXL8WFA5iefUPGKoVSYv5UuYh9JVFPLyavWiJCqPfMtZdJp5iJZk70")
        //
        val eventDispatcherModule = module {
            factory { EventDispatcher<HairdresserChatHistoryActions>() }
        }
        val repositoryModule = module {
            factory { HairdresserHomeRepository() as HairdresserHomeRepositoryContract }
            factory { ChatRoomRepositoryHttp() as ChatRoomRepositoryContract }
        }
        val viewModelModule = module {
            viewModel { HairdresserFoundationViewModel(get()) }
            viewModel { HairdresserHomeViewModel(get(), get()) }
            viewModel { HairdresserChatHistoryViewModel(get(), get()) }
            viewModel { HairdresserHairstyleListViewModel(get()) }
            viewModel { HairdresserHairstylePostingViewModel(get()) }
        }
        startKoin {
            androidContext(this@ApplicationCMM)
            modules(listOf(eventDispatcherModule, repositoryModule, viewModelModule))
        }
    }
}