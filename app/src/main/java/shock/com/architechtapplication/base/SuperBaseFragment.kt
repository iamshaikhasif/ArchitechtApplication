package shock.com.architechtapplication.base

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import shock.com.architechtapplication.R

open class SuperBaseFragment :  Fragment(), BaseView {
    override fun showProgress() {}

    override fun hideProgress() {}

    override fun onUnknownError() {
        Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
    }

    override fun showShortMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showLongMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onBackButtonPressed(): Boolean {
        return true
    }

    protected fun replaceFragment(
        @IdRes containerId: Int,
        fragmentInstance: Fragment,
        tag: String,
        addToBackStack: Boolean = true
    ) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(containerId, fragmentInstance, tag)
        if (addToBackStack) {
            transaction.addToBackStack(fragmentInstance.javaClass.canonicalName)
        }
        transaction.commitAllowingStateLoss()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected fun Activity?.isNoMore(): Boolean {
        return this?.let {
            isFinishing || isDestroyed
        } ?: true
    }

    protected fun navigate(id: Int) {
        val currentId = view?.findNavController()?.currentDestination
        view?.findNavController()?.navigate(id)
    }

    fun showAlertMessage(msg: String) {
        val dialog = Dialog(requireActivity(), R.style.Dialog)
        dialog.setContentView(R.layout.error_msg_box)
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        val msgText = dialog.findViewById(R.id.tvErrorMsg) as TextView
        msgText.text = msg
        val saveBtn = dialog.findViewById(R.id.tvSave) as Button
        saveBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}