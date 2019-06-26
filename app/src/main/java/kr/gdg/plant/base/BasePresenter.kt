package kr.gdg.plant.base

import kr.gdg.plant.data.RemoteSource

interface BasePresenter {
    fun start()

    val remoteSource: RemoteSource
        get() = RemoteSource
}