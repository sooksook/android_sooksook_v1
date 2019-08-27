//package kr.gdg.sooksook.view.activity
//
//import android.content.Intent
//import androidx.databinding.DataBindingUtil
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.GridLayoutManager
//import kr.gdg.sooksook.BR
//import kr.gdg.sooksook.R
//import kr.gdg.sooksook.base.BaseRecyclerView
//import kr.gdg.sooksook.common.Const
//import kr.gdg.sooksook.common.Dlog
//import kr.gdg.sooksook.databinding.ActivityTestBinding
//import kr.gdg.sooksook.databinding.ItemMainBinding
//import kr.gdg.sooksook.data.item.MainItem
//import kr.gdg.sooksook.data.item.getMainItems
//import kr.gdg.sooksook.view.viewmodel.MainViewModel
//
//class TestActivity: AppCompatActivity() {
//    private val model: MainViewModel by lazy { MainViewModel() }
//    private lateinit var binding: ActivityTestBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Dlog.i("onCreate")
//
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
//        binding.model = model
//        setRecyclerView()
//        model.onCreate()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        model.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        model.onPause()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        model.onDestroy()
//    }
//
//
//    private fun setRecyclerView() {
//        val adapter = object : BaseRecyclerView.Adapter<MainItem, ItemMainBinding>(
//            layoutResId = R.layout.item_main,
//            bindingVariableId = BR.mainItem
//        ) {}
//
//        adapter.run {
//            replaceAll(getMainItems())
//            setCallback(object : BaseRecyclerView.Adapter.ACallback {
//                override fun onClick(position: Int) {
//                    startActivity(Intent(this@TestActivity, WebViewActivity::class.java).apply {
//                        putExtra("url", "${Const.webViewUrl}${getMainItems()[position].name}")
//                    })
//                }
//            })
//        }
//
//        binding.includeKindOfPlant.plantRv.run {
//            this.adapter = adapter
//            this.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this@TestActivity, 2)
//        }
//    }
//}