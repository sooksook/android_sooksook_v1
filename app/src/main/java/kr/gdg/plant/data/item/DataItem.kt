package kr.gdg.plant.data.item

import kr.gdg.plant.common.Const

data class DataItem(
    val imageUrl: Int? = null,
    val name: String
)

private var countOfResults: Int = 0

fun getDataItems(name: String? = null) : MutableList<DataItem> {
    val items = mutableListOf<DataItem>()
    countOfResults = 0

    val arrayListData = Const.arrayListData

    arrayListData?.let { data ->
        for (i in data.indices) {
            name?.let {
                if (data[i].name!!.contains(it)) {
                    val item = DataItem(data[i].imageUrl, data[i].name!!)
                    items.add(item)
                    countOfResults++
                }
            } ?: run {
                val item = DataItem(data[i].imageUrl, data[i].name!!)
                items.add(item)
                countOfResults++
            }
        }
    }

    return items
}

fun getCountOfResults() = countOfResults