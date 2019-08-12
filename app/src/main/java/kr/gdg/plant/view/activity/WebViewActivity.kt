package kr.gdg.plant.view.activity

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import kr.gdg.plant.base.BaseActivity
import kr.gdg.plant.common.Dlog
import kr.gdg.plant.databinding.ActivityWebviewBinding
import kr.gdg.plant.util.extensions.changeStatusBar
import android.content.Intent

class WebViewActivity : BaseActivity<ActivityWebviewBinding>() {
    override val getContentView: Int
        get() = kr.gdg.plant.R.layout.activity_webview

    private lateinit var url: String

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        title = ""
        changeStatusBar(android.R.color.black)

        url = intent.getStringExtra("url")

        getBinding().webView.run {
            webViewClient = WebViewClient()
            clearCache(true)
            clearHistory()
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.domStorageEnabled = true
            loadUrl(url)
        }

        getBinding().webViewIvSend.setOnClickListener { onClickActionSend() }
    }

    private fun onClickActionSend() {
        startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, url)
        }, "친구에게 공유하기"))
    }
}