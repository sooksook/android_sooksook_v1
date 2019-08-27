package kr.gdg.sooksook.base

interface BaseView<T> {
    var presenter: T

    fun isViewActive(): Boolean
}