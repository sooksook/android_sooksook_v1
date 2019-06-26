package kr.gdg.plant.data.item

import kr.gdg.plant.common.Const

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