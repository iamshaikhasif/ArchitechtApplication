package shock.com.architechtapplication.sharePrefrences

import java.io.Serializable

interface SharedPrefDataSourceInterface {
    fun <T: Any> putData(data: T, key: String)
    fun <T: Any> getData(key: String, defaultValue: T?): Serializable?
}
