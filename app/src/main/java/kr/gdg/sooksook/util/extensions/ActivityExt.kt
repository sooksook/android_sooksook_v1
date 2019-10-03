package kr.gdg.sooksook.util.extensions

import android.app.Activity
import androidx.annotation.ColorRes
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun Activity.changeStatusBar(@ColorRes color: Int) {
    val window = window
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = this.getColorById(color)
}

fun AppCompatActivity.actionBarHide() {
    supportActionBar?.hide()
}

fun AppCompatActivity.setActionBarHome(toolbar: Toolbar, @DrawableRes res: Int? = null) {
    setSupportActionBar(toolbar)
    supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        setHomeButtonEnabled(true)
        res?.let {
            setHomeAsUpIndicator(it)
        } ?: run {
            setHomeAsUpIndicator(null)
        }
    }
}