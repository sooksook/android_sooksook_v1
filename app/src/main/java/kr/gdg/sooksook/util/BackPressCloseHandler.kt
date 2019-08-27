package kr.gdg.sooksook.util

import android.app.Activity
import kr.gdg.sooksook.R
import kr.gdg.sooksook.util.extensions.showToast

class BackPressCloseHandler(private val context: Activity) {
    private var backKeyPressedTime = 0L

    fun onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            context.showToast(R.string.back_pressed_message)
            backKeyPressedTime = System.currentTimeMillis()
            return
        }

        if(System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            context.finish()
        }
    }
}