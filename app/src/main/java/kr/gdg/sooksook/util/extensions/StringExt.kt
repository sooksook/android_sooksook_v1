package kr.gdg.sooksook.util.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned

// html 추가
fun String.toSpanned(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY) else @Suppress("DEPRECATION") Html.fromHtml(this)