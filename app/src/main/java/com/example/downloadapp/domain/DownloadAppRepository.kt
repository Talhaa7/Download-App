package com.example.downloadapp.domain

import com.example.downloadapp.features.adapter.DownloadAppListUiModel
import com.example.downloadapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DownloadAppRepository {
    suspend fun getFlashlightsAppList() : Flow<Resource<List<DownloadAppListUiModel>>>
    suspend fun getColorLightsAppList() : Flow<Resource<List<DownloadAppListUiModel>>>
    suspend fun getSosAlertsAppList() : Flow<Resource<List<DownloadAppListUiModel>>>

}