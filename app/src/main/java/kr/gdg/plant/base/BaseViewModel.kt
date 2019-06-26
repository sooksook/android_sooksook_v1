package kr.gdg.plant.base

abstract class BaseViewModel {
    abstract fun onCreate()
    abstract fun onResume()
    abstract fun onPause()
    abstract fun onDestroy()
}