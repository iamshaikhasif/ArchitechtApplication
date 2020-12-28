package shock.com.architechtapplication.base

import android.app.Dialog
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import shock.com.architechtapplication.R

open class SuperBaseActivity : AppCompatActivity(), BaseView{

    override fun showProgress() {}

    override fun hideProgress() {}

    override fun onUnknownError() {
        if (isFinishing || isDestroyed) {
            return
        }
        Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
    }

    override fun showShortMessage(msg: String) {
        if (isFinishing || isDestroyed) {
            return
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showLongMessage(msg: String) {
        if (isFinishing || isDestroyed) {
            return
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onBackButtonPressed(): Boolean {
        return true
    }

    fun replaceFragment(@IdRes containerId: Int, fragmentInstance: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragmentInstance)
        if (addToBackStack) {
            transaction.addToBackStack(fragmentInstance.javaClass.canonicalName)
        }
        transaction.commitAllowingStateLoss()
    }

    fun showAlertMessage(msg: String) {
        val dialog = Dialog(this, R.style.Dialog)
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