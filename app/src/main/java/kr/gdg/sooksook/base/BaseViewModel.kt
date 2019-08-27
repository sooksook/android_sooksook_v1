package kr.gdg.sooksook.base

abstract class BaseViewModel {
    abstract fun onCreate()
    abstract fun onResume()
    abstract fun onPause()
    abstract fun onDestroy()
}