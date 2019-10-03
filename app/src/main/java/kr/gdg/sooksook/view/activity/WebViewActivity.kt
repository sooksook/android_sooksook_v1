package kr.gdg.sooksook.view.activity

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import kr.gdg.sooksook.base.BaseActivity
import kr.gdg.sooksook.databinding.ActivityWebviewBinding
import kr.gdg.sooksook.util.extensions.changeStatusBar
import android.content.Intent
import android.view.MenuItem
import kr.gdg.sooksook.R
import kr.gdg.sooksook.common.Dlog
import kr.gdg.sooksook.util.extensions.setActionBarHome

class WebViewActivity : BaseActivity<ActivityWebviewBinding>() {
    override val getContentView: Int
        get() = R.layout.activity_webview

    private lateinit var url: String

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        Dlog.i("initView")

        setActionBarHome(getBinding().toolbar, R.drawable.bar_grey)

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

//        getBinding().webView.loadUrl(url)
        getBinding().webViewIvSend.setOnClickListener {
            startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, url)
            }, "친구에게 공유하기"))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when(item?.itemId) {
        android.R.id.home -> {
            finish()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}