package kr.gdg.plant.data

import retrofit2.Call
import retrofit2.http.GET

interface SpeadApiService {
    // App version 체크 -> 확인함
    @GET("/api/v1/version")
    fun getVersion(): Call<SpreadDataResponse>
}