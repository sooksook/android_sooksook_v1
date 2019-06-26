package kr.gdg.plant.view.activity

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import kr.gdg.plant.R
import kr.gdg.plant.base.BaseActivity
import kr.gdg.plant.common.Dlog
import kr.gdg.plant.databinding.ActivityWebviewBinding
import kr.gdg.plant.util.extensions.changeStatusBar

class WebViewActivity : BaseActivity<ActivityWebviewBinding>() {
    override val getContentView: Int
        get() = R.layout.activity_webview

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        Dlog.i("initView")

        title = ""
        changeStatusBar(android.R.color.black)

        getBinding().webView.webViewClient = WebViewClient()
        getBinding().webView.clearCache(true)
        getBinding().webView.clearHistory()
        getBinding().webView.settings.javaScriptEnabled = true
        getBinding().webView.settings.javaScriptCanOpenWindowsAutomatically = true
        getBinding().webView.settings.domStorageEnabled = true
        getBinding().webView.loadUrl(intent.getStringExtra("url"))
    }
}