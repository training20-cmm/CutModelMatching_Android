package training20.tcmobile

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import training20.tcmobile.repositories.HairdresserRepository
import training20.tcmobile.repositories.HairdresserRepositoryHttp
import training20.tcmobile.repositories.ModelRepository
import training20.tcmobile.repositories.ModelRepositoryHttp

class ApplicationCMM: Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.onCreateApplication(applicationContext)
        setupCoin()
    }

    private fun setupCoin() {
        val repositoryModule = module {
            factory {HairdresserRepositoryHttp() as HairdresserRepository}
            factory {ModelRepositoryHttp() as ModelRepository}
        }
        startKoin(this, listOf(
            repositoryModule
        ))
    }
}