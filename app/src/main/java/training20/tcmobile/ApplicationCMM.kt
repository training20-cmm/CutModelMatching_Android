package training20.tcmobile

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import training20.tcmobile.auth.AuthManager
import training20.tcmobile.auth.AuthManagerRealm
import training20.tcmobile.mvvm.actions.HairdresserChatHistoryActions
import training20.tcmobile.mvvm.actions.HairdresserHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.*
import training20.tcmobile.mvvm.viewmodels.*

class ApplicationCMM: Application() {

    companion object {
        const val wsServerOrigin = "ws://192.168.3.2:8090"
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.onCreateApplication(applicationContext)

        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfiguration)
        val model = AuthManagerRealm().currentModel()
        println(model)
        Realm.getDefaultInstance().executeTransaction {
            Realm.getDefaultInstance().deleteAll()
        }
        val authManagerModule = module {
            factory { AuthManagerRealm() as AuthManager }
        }

        val eventDispatcherModule = module {
            factory { EventDispatcher<HairdresserChatHistoryActions>() }
        }
        val repositoryModule = module {
            factory { ChatRoomRepositoryHttp() as ChatRoomRepositoryContract }
            factory { HairdresserRepositoryHttp() as HairdresserRepositoryContract }
            factory { ModelRepositoryHttp() as ModelRepositoryContract }
            factory { HairdresserHomeRepository() as HairdresserHomeRepositoryContract }
            factory { SalonRepositoryHttp() as SalonRepositoryContract }
            factory { MenuRepositoryHttp() as MenuRepositoryContract }
            factory { ReservationRepositoryHttp() as ReservationRepositoryContract }
            factory { MenuTagCategoryRepositoryHttp() as MenuTagCategoryRepositoryContract }
        }
        val viewModelModule = module {
            viewModel { HairdresserRegistrationFormViewModel(get(), get()) }
            viewModel { HairdresserFoundationViewModel(get()) }
            viewModel { HairdresserHomeViewModel(get(), get(), get()) }
            viewModel { HairdresserChatHistoryViewModel(get(), get()) }
            viewModel { HairdresserHairstyleListViewModel(get()) }
            viewModel { HairdresserHairstylePostingViewModel(get()) }
            viewModel { HairdresserMenuPostingViewModel(get(), get(), get()) }
            viewModel { HairdresserSalonViewModel(get(), get()) }
            viewModel { HairdresserSalonUnregisteredViewModel(get()) }
            viewModel { HairdresserSalonRegistrationViewModel(get(), get()) }
            viewModel { HairdresserChatRoomViewModel(get(), get(), get()) }
            viewModel { ModelRegistrationFormViewModel(get(), get()) }
            viewModel { ModelFoundationViewModel(get()) }
            viewModel { ModelHomeViewModel(get(), get()) }
            viewModel { ModelChatHistoryViewModel(get(), get()) }
            viewModel { ModelNotificationsViewModel(get()) }
            viewModel { ModelMenuSearchViewModel(get()) }
            viewModel { ModelMenuViewModel(get(), get(), get()) }
            viewModel { ModelChatRoomViewModel(get(), get(), get()) }
            viewModel { ModelReservationConfirmationViewModel(get())}
            viewModel { ModelSearchResultViewModel(get(), get()) }
        }
        startKoin {
            androidContext(this@ApplicationCMM)
            modules(listOf(authManagerModule, eventDispatcherModule, repositoryModule, viewModelModule))
        }
        if (BuildConfig.DEBUG) {
//            Debugger.debug(Role.HAIRDRESSER, "9eQS4Udx5DH8sVRRLOysfqYwzFOSEZIszc9axGnxlDnFCSoWabsD3n61YE06")
            Debugger.debug(Role.MODEL, "e4h86Hck3vcDrh9abcLpjlvZfNj4tt4EfNxZkeaDf88lsCz4sssSU1tX9INq")
        }
    }
}