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
import kr.gdg.sooksook.util.extensions.getDrawableById
import kr.gdg.sooksook.util.extensions.setActionBarHome

class WebViewActivity : BaseActivity<ActivityWebviewBinding>() {
    override val getContentView: Int
        get() = R.layout.activity_webview

    private lateinit var url: String

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        Dlog.i("initView")
        getBinding().activity = this
        setActionBarHome(getBinding().includeToolbar.toolbar, R.drawable.bar_grey)
        url = intent.getStringExtra("url")

        setWebView()
        setToolbarUi()
    }

    private fun setWebView() {
        getBinding().webView.apply {
            webViewClient = WebViewClient()
            clearCache(true)
            clearHistory()
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.domStorageEnabled = true
        }
        getBinding().webView.loadUrl(url)
    }

    private fun setToolbarUi() {
        getBinding().includeToolbar.includeToolbarIv.setImageDrawable(getDrawableById(R.drawable.bar_share))
        getBinding().includeToolbar.includeToolbarIv.setOnClickListener {
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