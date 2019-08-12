package kr.gdg.plant.view.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kr.gdg.plant.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.gdg.plant.BR
import kr.gdg.plant.R
import kr.gdg.plant.base.BaseRecyclerView
import kr.gdg.plant.common.Const
import kr.gdg.plant.common.Dlog
import kr.gdg.plant.data.item.*
import kr.gdg.plant.databinding.ActivitySearchBinding
import kr.gdg.plant.databinding.ItemDataBinding
import kr.gdg.plant.databinding.ItemSearchResultBinding
import kr.gdg.plant.util.extensions.toSpanned
import kr.gdg.plant.view.viewmodel.SearchViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    private val model: SearchViewModel by lazy { SearchViewModel() }
    private val imm: InputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

    override val getContentView: Int
        get() = R.layout.activity_search

    override fun initView() {
        getBinding().model = model
        model.onCreate()

        imm.showSoftInput(getBinding().searchEt, 0)

        getBinding().searchEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    getBinding().includeKindOfPlant.plantCv.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                    setRvAboutResult(it.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        getBinding().searchSv.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY > 0)
                imm.hideSoftInputFromWindow(getBinding().searchEt.windowToken, 0)
        }

        setRecyclerViewInit()
        setRvAboutRecentlyText()
    }

    private fun setRecyclerViewInit() {
        getBinding().includeKindOfPlant.plantTvTitle.visibility = View.GONE
        val adapter = object : BaseRecyclerView.Adapter<DataItem, ItemDataBinding>(
            layoutResId = R.layout.item_data,
            bindingVariableId = BR.dataItem
        ) {}

        adapter.run {
            replaceAll(getDataItems(""))
            setCallback(object : BaseRecyclerView.Adapter.ACallback {
                override fun onClick(position: Int) {
                    startActivity(Intent(this@SearchActivity, WebViewActivity::class.java).apply {
                        putExtra("url", "${Const.webViewUrl}${getDataItems()[position].name}")
                    })
                }
            })
        }

        getBinding().includeKindOfPlant.plantRv.run {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@SearchActivity)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy != 0)
                        imm.hideSoftInputFromWindow(getBinding().searchEt.windowToken, 0)
                }
            })
        }
    }

    private fun setRvAboutRecentlyText() {
        val adapter = object : BaseRecyclerView.Adapter<SearchResultItem, ItemSearchResultBinding>(
            layoutResId = R.layout.item_search_result,
            bindingVariableId = BR.searchItem
        ) {}

        adapter.run {
            replaceAll(getSearchResultItems())
            setCallback(object : BaseRecyclerView.Adapter.ACallback {
                override fun onClick(position: Int) {

                }
            })
        }

        getBinding().searchRvResultText.run {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@SearchActivity)
        }
    }

    private fun setRvAboutResult(name: String) {
        val items = getDataItems(name)
        for(i in items.indices)
            Dlog.i("결과 : items[$i] = ${items[i].name}, ${items[i].imageUrl}")
        if(getCountOfResults() == 0) {
            getBinding().searchCvResultDataNo.visibility = View.VISIBLE
            getBinding().includeKindOfPlant.plantCv.visibility = View.GONE

            val data = ("<a><font color='#4a4a4a'><b>\"$name\"</b></font></a>가 없다면\n문의하기를 눌러주세요").toSpanned()
            getBinding().searchTvTitle.text = data
            getBinding().searchBtContact.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SEND).apply {
                    type = "plaine/text"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("sooksookcontact@gmail.com"))
                })
            }
        } else {
            getBinding().searchCvResultDataNo.visibility = View.GONE
            getBinding().includeKindOfPlant.plantCv.visibility = View.VISIBLE

            val adapter = object : BaseRecyclerView.Adapter<DataItem, ItemDataBinding>(
                layoutResId = R.layout.item_data,
                bindingVariableId = BR.dataItem
            ) {}

            adapter.run {
                replaceAll(items)
                setCallback(object : BaseRecyclerView.Adapter.ACallback {
                    override fun onClick(position: Int) {
                        startActivity(Intent(this@SearchActivity, WebViewActivity::class.java).apply {
                            putExtra("url", "${Const.webViewUrl}${items[position].name}")
                        })
                    }
                })
            }

            getBinding().includeKindOfPlant.plantRv.run {
                this.adapter = adapter
                this.layoutManager = LinearLayoutManager(this@SearchActivity)

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy != 0)
                            imm.hideSoftInputFromWindow(getBinding().searchEt.windowToken, 0)
                    }
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Dlog.i("onResume")
        model.onResume()
    }

    override fun onPause() {
        super.onPause()
        Dlog.i("onPause")
        model.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Dlog.i("onDestroy")
        model.onDestroy()
    }
}