package shock.com.architechtapplication

import androidx.lifecycle.MutableLiveData
import shock.com.architechtapplication.base.BaseViewModel

class HomeViewModel: BaseViewModel() {

    val number = MutableLiveData<String>()
}