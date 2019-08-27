package kr.gdg.sooksook.util.extensions

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import com.google.firebase.analytics.FirebaseAnalytics
import kr.gdg.sooksook.view.activity.MainActivity

// html 추가
fun String.toSpanned(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY) else @Suppress("DEPRECATION") Html.fromHtml(this)

fun String.setFirebaseEvent(params: String? = null) {
    val bundleTagging = Bundle().apply {
        params?.let {
            putString(FirebaseAnalytics.Param.ITEM_ID, "id-$it")
            putString(FirebaseAnalytics.Param.ITEM_NAME, it)
            putString(FirebaseAnalytics.Param.CONTENT_TYPE, "device")
        } ?: run {

        }
    }
    MainActivity.mFirebaseAnalytics.logEvent(this, bundleTagging)
}