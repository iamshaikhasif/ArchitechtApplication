package shock.com.architechtapplication.base

import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.koin.android.ext.android.inject
import shock.com.architechtapplication.datasource.DataSourceManagerImpl

abstract class BaseFragments<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    SuperBaseFragment() {

    val dataSource by inject<DataSourceManagerImpl>()
    var handler: Handler? = null
    var runnable: Runnable? = null
    var parentFirstTime: Boolean = true

    lateinit var viewModel: VM
    open lateinit var binding: DB

    private fun init(inflater: LayoutInflater, container: ViewGroup) {
        viewModel.noInternet.observe(this, Observer {
//            showAlert(getString(R.string.no_internet), getString(R.string.no_internet_try_again))
        })

        viewModel.showLoader.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (viewModel.showLoader.get()) {
                    activity?.window?.setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                } else {
                    activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
            }
        })
    }

    fun showDialogFragment(dialogFragment: DialogFragment, tag: String) {
        val ft = childFragmentManager.beginTransaction()
        val prev = childFragmentManager.findFragmentByTag(tag)
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        dialogFragment.show(ft, tag)
    }

    open fun init() {}

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initViewModel(viewModel: VM)

    private fun getViewM(): VM =
        activity?.application?.let { ViewModelProvider.AndroidViewModelFactory(it).create(mViewModelClass) }!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()
        viewModel.setDataSourceManager(dataSource)
    }

    open fun getUiniqueId(): String {
        return Settings.Secure.getString(activity?.contentResolver,
            Settings.Secure.ANDROID_ID);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (handler != null && runnable != null) {
            handler?.removeCallbacks(runnable!!)
            handler = null
            runnable = null
        }
        handler = Handler(requireActivity().mainLooper)
        runnable = Runnable {
            parentFirstTime = false
        }
        handler?.postDelayed(runnable!!, 250)
        init(inflater, container!!)
        init()
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }



}