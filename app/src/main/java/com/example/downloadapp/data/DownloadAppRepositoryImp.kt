package com.example.downloadapp.data

import com.example.downloadapp.domain.DownloadAppRepository
import com.example.downloadapp.features.adapter.DownloadAppListUiModel
import com.example.downloadapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DownloadAppRepositoryImp @Inject constructor(private val downloadAppService: DownloadAppService): DownloadAppRepository {


    override suspend fun getFlashlightsAppList(): Flow<Resource<List<DownloadAppListUiModel>>> {

        return flow {
            emit(Resource.Loading(true))
            val flashlightAppList =downloadAppService.getFlashlightsAppList()
            emit(Resource.Success(
                data = flashlightAppList.map {
                    DownloadAppListUiModel(
                        it.category,
                        it.developerName,
                        it.downloads,
                        it.iconUrl,
                        it.name,
                        it.packageName,
                        it.ratingValue
                    )
                }
            ))
        }
    }

    override suspend fun getColorLightsAppList(): Flow<Resource<List<DownloadAppListUiModel>>> {

        return flow {
            emit(Resource.Loading(true))
            val colorLightAppList =downloadAppService.getColorLightsAppList()
            emit(Resource.Success(
                data = colorLightAppList.map {
                    DownloadAppListUiModel(
                        it.category,
                        it.developerName,
                        it.downloads,
                        it.iconUrl,
                        it.name,
                        it.packageName,
                        it.ratingValue
                    )
                }
            ))
        }
    }

    override suspend fun getSosAlertsAppList(): Flow<Resource<List<DownloadAppListUiModel>>> {

        return flow {
            emit(Resource.Loading(true))
            val sosAlertAppList =downloadAppService.getSosAlertsAppList()
            emit(Resource.Success(
                data = sosAlertAppList.map {
                    DownloadAppListUiModel(
                        it.category,
                        it.developerName,
                        it.downloads,
                        it.iconUrl,
                        it.name,
                        it.packageName,
                        it.ratingValue
                    )
                }
            ))
        }
    }
}