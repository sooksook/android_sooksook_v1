package kr.gdg.plant.util

import android.app.Activity
import kr.gdg.plant.R
import kr.gdg.plant.util.extensions.showToast

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