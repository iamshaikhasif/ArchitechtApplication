package shock.com.architechtapplication.datasource

import shock.com.architechtapplication.sharePrefrences.SharedPrefDataSourceImpl
import java.io.Serializable

class DataSourceManagerImpl(private val sharedPrefDataSourceImpl: SharedPrefDataSourceImpl) : DataSourceManager {
    override fun <T : Any> putData(data: T, key: String) = sharedPrefDataSourceImpl.putData(data, key)
    override fun <T : Any> getData(key: String, defaultValue: T?): Serializable? = sharedPrefDataSourceImpl.getData(key, defaultValue)
}