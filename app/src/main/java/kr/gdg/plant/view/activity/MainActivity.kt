package kr.gdg.plant.view.activity

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import kr.gdg.plant.BR
import kr.gdg.plant.R
import kr.gdg.plant.base.BaseActivity
import kr.gdg.plant.base.BaseRecyclerView
import kr.gdg.plant.common.Const
import kr.gdg.plant.common.Dlog
import kr.gdg.plant.data.RemoteSource
import kr.gdg.plant.databinding.ActivityMainBinding
import kr.gdg.plant.databinding.ItemMainBinding
import kr.gdg.plant.data.item.MainItem
import kr.gdg.plant.data.item.getMainItems
import kr.gdg.plant.view.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val model: MainViewModel by lazy { MainViewModel() }

    override val getContentView: Int
        get() = R.layout.activity_main

    override fun initView() {
        val actionBar = supportActionBar
        actionBar?.hide()

        getBinding().model = model
        model.onCreate()
        setRecyclerView()
    }

    override fun onClick() {
        super.onClick()

        getBinding().mainIvBox.setOnClickListener { startActivity(Intent(this, SearchActivity::class.java)) }
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
