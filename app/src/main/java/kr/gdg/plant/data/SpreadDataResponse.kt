package kr.gdg.plant.data

import androidx.annotation.IntegerRes

data class SpreadDataResponse(
    val id: Int,
    val name: String? = null,
    val category: String? = null,
    val water: String? = null,
    val sunlight: String? = null,
    val wind: String? = null,
    val soil: String? = null,
    val temperature: String? = null,
    val characteristic: String? = null,
    val tip1: String? = null,
    val tip2: String? = null,
    val tip3: String? = null,
    val tip4: String? = null,
    val tip5: String? = null,
    @IntegerRes var imageUrl: Int? = null
)