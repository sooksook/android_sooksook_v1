package kr.gdg.sooksook.util.extensions

import android.app.Activity
import androidx.annotation.ColorRes
import android.view.WindowManager

fun Activity.changeStatusBar(@ColorRes color: Int) {
    val window = window
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = this.getColorById(color)
}