package kr.gdg.sooksook.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import kr.gdg.sooksook.util.extensions.changeStatusBar
import kr.gdg.sooksook.view.custom.UtilDialog

abstract class BaseActivity<T: ViewDataBinding>: AppCompatActivity() {

    private var viewBinding: T? = null

    private var utilDialog: UtilDialog? = null

    abstract val getContentView: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, getContentView)

        title = ""
        changeStatusBar(android.R.color.black)

        initView()
        onClick()

        utilDialog = UtilDialog()
    }

    abstract fun initView()

    open fun onClick() {}

    fun getBinding(): T = viewBinding!!

    fun checkActive(): Boolean = !isFinishing

    fun showProgress() {
        utilDialog?.showCustomProgressDialogAboutServer(this)
        window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun hideProgress() {
        utilDialog?.closeCustomProgressDialog()
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}