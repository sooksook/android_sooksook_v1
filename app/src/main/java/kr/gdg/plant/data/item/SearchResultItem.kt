package kr.gdg.plant.data.item

data class SearchResultItem(val name: String)

fun getSearchResultItems() : MutableList<SearchResultItem> {
    val items = mutableListOf<SearchResultItem>()

    for (i in 0 until 5) {
        val item = SearchResultItem("name_$i")
        items.add(item)
    }

    return items
}