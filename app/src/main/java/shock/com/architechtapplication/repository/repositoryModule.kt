package shock.com.architechtapplication.repository

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import shock.com.architechtapplication.R
import shock.com.architechtapplication.datasource.DataSourceManagerImpl
import shock.com.architechtapplication.sharePrefrences.SharedPrefDataSourceImpl

val repositoryModule = module{

    single {
        androidContext()
            .getSharedPreferences(androidContext()
                .resources.getString(R.string.pref), Context.MODE_PRIVATE)
    }

    single { SharedPrefDataSourceImpl(get()) }
    single { DataSourceManagerImpl(get()) }
}