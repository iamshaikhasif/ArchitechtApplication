package shock.com.architechtapplication.utils

import android.app.Application
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import shock.com.architechtapplication.datasource.DataSourceManagerImpl
import shock.com.architechtapplication.repository.repositoryModule

class ArchitechtApplication : Application() {
    private val dataSource by inject<DataSourceManagerImpl>()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ArchitechtApplication)
            modules(listOf(repositoryModule))
//            modules(listOf(networkModule, viewModelModule, repositoryModuleSubscription))
        }
    }
}