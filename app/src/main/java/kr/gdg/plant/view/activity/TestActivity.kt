//package kr.gdg.plant.view.activity
//
//import android.content.Intent
//import androidx.databinding.DataBindingUtil
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.GridLayoutManager
//import kr.gdg.plant.BR
//import kr.gdg.plant.R
//import kr.gdg.plant.base.BaseRecyclerView
//import kr.gdg.plant.common.Const
//import kr.gdg.plant.common.Dlog
//import kr.gdg.plant.databinding.ActivityTestBinding
//import kr.gdg.plant.databinding.ItemMainBinding
//import kr.gdg.plant.data.item.MainItem
//import kr.gdg.plant.data.item.getMainItems
//import kr.gdg.plant.view.viewmodel.MainViewModel
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