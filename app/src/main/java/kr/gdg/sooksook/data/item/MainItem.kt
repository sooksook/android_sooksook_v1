package kr.gdg.sooksook.data.item

import kr.gdg.sooksook.common.Const

data class MainItem(val imageUrl: Int, val name: String)

fun getMainItems() : MutableList<MainItem> {
    val items = mutableListOf<MainItem>()

    Const.arrayListData?.let {
        for (i in it.indices) {
            val item = MainItem(it[i].imageUrl!!, it[i].name!!)
            items.add(item)
        }
    }

    return items
}