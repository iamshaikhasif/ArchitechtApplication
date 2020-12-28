package shock.com.architechtapplication.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import shock.com.architechtapplication.datasource.DataSourceManagerImpl

open class BaseViewModel : ViewModel() {

    lateinit var dataSource: DataSourceManagerImpl
    var noInternet = MutableLiveData<Boolean>()
//    var serverError = MutableLiveData<BaseResponse<String?>>()
    var forceSignOut = MutableLiveData<Boolean>()
    var showLoader = ObservableBoolean()
    var showHint = MutableLiveData<String>()

    fun setDataSourceManager(dataSource: DataSourceManagerImpl) {
        this.dataSource = dataSource
    }
}