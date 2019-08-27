package kr.gdg.sooksook.util.extensions

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics

// region toast message
fun Context.showToast(message: String) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }

fun Context.showToast(@StringRes messageRes: Int) { Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show() }

fun Context.showToastLong(message: String) { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }

fun Context.showToastLong(@StringRes messageRes: Int) { Toast.makeText(this, messageRes, Toast.LENGTH_LONG).show() }
// endregion

// region Dialog or Message
fun Context.alertDialog(@StringRes messageId: Int, listener: DialogInterface.OnClickListener? = null) {
    alertDialog(getString(messageId), listener)
}

fun Context.alertDialog(message: String, listener: DialogInterface.OnClickListener? = null) {
    AlertDialog.Builder(this)
        .setCancelable(false)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok, listener)
        .show()
}

fun Context.confirmDialog(@StringRes messageId: Int,
                          okListener: DialogInterface.OnClickListener? = null,
                          cancelListener: DialogInterface.OnClickListener? = null) {
    confirmDialog(getString(messageId), okListener, cancelListener)
}

fun Context.confirmDialog(message: String,
                          okListener: DialogInterface.OnClickListener? = null,
                          cancelListener: DialogInterface.OnClickListener? = null) {
    AlertDialog.Builder(this)
        .setCancelable(false)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok, okListener)
        .setNegativeButton(android.R.string.cancel, cancelListener)
        .show()
}

fun Context.confirmDialogCustom(@StringRes messageId: Int, okText: String, noText: String,
                                okListener: DialogInterface.OnClickListener? = null,
                                cancelListener: DialogInterface.OnClickListener? = null) {
    confirmDialogCustom(getString(messageId), okText, noText, okListener, cancelListener)
}

fun Context.confirmDialogCustom(message: String, okText: String, noText: String,
                                okListener: DialogInterface.OnClickListener? = null,
                                cancelListener: DialogInterface.OnClickListener? = null) {
    AlertDialog.Builder(this)
        .setCancelable(false)
//        .setTitle("AlertDialog Title")
        .setMessage(message)
        .setPositiveButton(okText, okListener)
        .setNegativeButton(noText, cancelListener)
        .create().show()
}
// endregion

fun Context.setFirebaseEvent(event: String, params: String? = null) {
    val bundleTagging = Bundle().apply {
        params?.let {
            putString(FirebaseAnalytics.Param.ITEM_ID, "id-$it")
            putString(FirebaseAnalytics.Param.ITEM_NAME, it)
            putString(FirebaseAnalytics.Param.CONTENT_TYPE, "device")
        } ?: run {

        }
    }

    val mFirebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    mFirebaseAnalytics.logEvent(event, bundleTagging)
}