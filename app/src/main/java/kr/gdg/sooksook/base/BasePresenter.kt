package kr.gdg.sooksook.base

import kr.gdg.sooksook.data.RemoteSource

interface BasePresenter {
    fun start()

    val remoteSource: RemoteSource
        get() = RemoteSource
}