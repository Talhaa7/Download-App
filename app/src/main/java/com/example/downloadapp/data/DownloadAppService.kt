package com.example.downloadapp.data

import com.example.downloadapp.data.model.AppListResponse
import retrofit2.http.GET

interface DownloadAppService {

    companion object {
        const val BASE_URL = "https://m104e.wiremockapi.cloud/"
    }

    @GET("v1/flashlights")
    suspend fun getFlashlightsAppList(): AppListResponse

    @GET("v1/colorlights")
    suspend fun getColorLightsAppList(): AppListResponse

    @GET("v1/sosalerts")
    suspend fun getSosAlertsAppList(): AppListResponse
}