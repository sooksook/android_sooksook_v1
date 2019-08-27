package kr.gdg.sooksook.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import kr.gdg.sooksook.BR
import kr.gdg.sooksook.R
import kr.gdg.sooksook.base.BaseActivity
import kr.gdg.sooksook.base.BaseRecyclerView
import kr.gdg.sooksook.common.Const
import kr.gdg.sooksook.common.Dlog
import kr.gdg.sooksook.data.RemoteSource
import kr.gdg.sooksook.databinding.ActivityMainBinding
import kr.gdg.sooksook.databinding.ItemMainBinding
import kr.gdg.sooksook.data.item.MainItem
import kr.gdg.sooksook.data.item.getMainItems
import kr.gdg.sooksook.util.extensions.setFirebaseEvent
import kr.gdg.sooksook.view.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val model: MainViewModel by lazy { MainViewModel() }

    override val getContentView: Int
        get() = R.layout.activity_main

    companion object {
        val mFirebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(MainActivity())
    }

    override fun initView() {
        val actionBar = supportActionBar
        actionBar?.hide()

        getBinding().model = model
        model.onCreate()
        setRecyclerView()
    }

    override fun onClick() {
        super.onClick()

        getBinding().mainIvBox.setOnClickListener {
            "home_searchBar".setFirebaseEvent()
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    private fun setRecyclerView() {
        val adapter = object : BaseRecyclerView.Adapter<MainItem, ItemMainBinding>(
            layoutResId = R.layout.item_main,
            bindingVariableId = BR.mainItem
        ) {}

        adapter.run {
            replaceAll(getMainItems())
            setCallback(object : BaseRecyclerView.Adapter.ACallback {
                override fun onClick(position: Int) {
                    "home_browsing_plant".setFirebaseEvent(getMainItems()[position].name)

                    startActivity(Intent(this@MainActivity, WebViewActivity::class.java).apply {
                        putExtra("url", "${Const.webViewUrl}${getMainItems()[position].name}")
                    })
                }
            })
        }

        getBinding().includeKindOfPlant.plantRv.run {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        Dlog.i("onResume")
        model.onResume()

        if (Const.arrayListData == null) {
            RemoteSource.readOnlySpreadSheet()
            RemoteSource.setCallback(object : RemoteSource.OCallback {
                override fun getDataFromSpeadSheet(isGetData: Boolean) {
                    Dlog.i("isGetData = $isGetData")
                    if (isGetData) {
//                        Handler().postDelayed({
                            startActivity(Intent(this@MainActivity, MainActivity::class.java))
                            finish()
//                        }, 1000)
                    }
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        model.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        model.onDestroy()
    }
}
