package kr.gdg.sooksook.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kr.gdg.sooksook.view.custom.UtilDialog

abstract class BaseFragment: androidx.fragment.app.Fragment() {
    private var utilDialog: UtilDialog? = null

    abstract val layoutRes: Int

    abstract fun setView(view: View?, savedInstanceState: Bundle?, arguments: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView(view, savedInstanceState, arguments)

        utilDialog = UtilDialog()
    }

    fun checkActive(): Boolean = isAdded

    fun showProgress() {
        utilDialog?.showCustomProgressDialogAboutServer(context)
        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun hideProgress() {
        utilDialog?.closeCustomProgressDialog()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}