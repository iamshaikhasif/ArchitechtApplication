package shock.com.architechtapplication.base

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.koin.android.ext.android.inject
import shock.com.architechtapplication.R
import shock.com.architechtapplication.datasource.DataSourceManagerImpl
import shock.com.architechtapplication.sharePrefrences.SharedPrefDataSourceImpl

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(
    private val mViewModelClass: Class<VM>
) : SuperBaseActivity() {

    val dataSource by inject<DataSourceManagerImpl>()

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initViewModel(viewModel: VM)

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding.lifecycleOwner = this@BaseActivity
        viewModel.setDataSourceManager(dataSource)
        initViewModel(viewModel)

        viewModel.noInternet.observe(this, Observer {
            showAlertMessage(getString(R.string.no_internet_try_again))
        })

        viewModel.showLoader.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (viewModel.showLoader.get()) {
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                } else {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
            }
        })

        super.onCreate(savedInstanceState)
    }


}