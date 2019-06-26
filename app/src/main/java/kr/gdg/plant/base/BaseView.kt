package kr.gdg.plant.base

interface BaseView<T> {
    var presenter: T

    fun isViewActive(): Boolean
}