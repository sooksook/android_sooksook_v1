package kr.gdg.plant.util.extensions

val Any.TAG: String
    get() = this::class.simpleName ?: this.toString()

inline fun <T> T.returnIf(check: () -> Boolean) : T? = if(check()) this else null