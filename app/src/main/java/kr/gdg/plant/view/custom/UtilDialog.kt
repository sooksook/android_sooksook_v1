package kr.gdg.plant.view.custom

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import androidx.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.LayoutInflater
import android.view.Window
import android.widget.ImageView
import kotlinx.android.synthetic.main.dialog_loading_progress_server.view.*
import kr.gdg.plant.R
import kr.gdg.plant.databinding.DialogLoadingProgressServerBinding
import kr.gdg.plant.util.extensions.getColorById

class UtilDialog {
    companion object {
        private var mInstance: UtilDialog? = null
        val instance: UtilDialog
            get() {
                if (mInstance == null)
                    mInstance = UtilDialog()

                return mInstance!!
            }
    }

    private var customDialog: Dialog? = null
    private var hdAutoClose: Handler? = null

    @Synchronized
    fun showCustomProgressDialogAboutServer(context: Context?) {
        if (customDialog == null) {
            customDialog = Dialog(context!!).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
//            customDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            customDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val dialog = DataBindingUtil.inflate<DialogLoadingProgressServerBinding>(LayoutInflater.from(context), R.layout.dialog_loading_progress_server, null, false)

//            val inflater = LayoutInflater.from(context)
//            val dialog = inflater.inflate(R.layout.dialog_loading_progress_server, null)

            dialog.loadingPbCircle.indeterminateDrawable.setColorFilter(context.getColorById(R.color.color_4A4A4A), android.graphics.PorterDuff.Mode.MULTIPLY)

            customDialog!!.setContentView(dialog.root)
        }

        if (context == null) {
            return
        }

        if (!(context as Activity).isFinishing) {
            customDialog?.run {
                setCancelable(false)
                show()
            }
        }
    }

    @Synchronized
    fun closeCustomProgressDialog() {
        try {
            if (hdAutoClose != null)
                hdAutoClose!!.removeMessages(1001)

            if (customDialog != null) {
                if (customDialog!!.isShowing)
                    customDialog!!.dismiss()
                customDialog = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}