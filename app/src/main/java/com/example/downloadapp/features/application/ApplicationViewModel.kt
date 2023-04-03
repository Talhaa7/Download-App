package com.example.downloadapp.features.application

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*
import com.example.downloadapp.domain.DownloadAppRepository
import com.example.downloadapp.features.adapter.AppListAdapter
import com.example.downloadapp.features.adapter.DownloadAppListUiModel
import com.example.downloadapp.utils.Resource
import com.example.downloadapp.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(
    private val downloadAppRepository: DownloadAppRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _appList = MutableLiveData<List<DownloadAppListUiModel>> ()

    val appList : LiveData<List<DownloadAppListUiModel>>
        get() = _appList

    private var _errorString = SingleLiveEvent<String>()
    val errorString: LiveData<String>
        get() = _errorString



    init {
        getAppList()

    }

    fun getAppList() {
        when (savedStateHandle.get<String>("lightsType")) {
            "flashlight" -> getFlashlightsAppList()
            "coloredLight" -> getColorLightsAppList()
            "sosAlert" -> getSosAlertsAppList()
        }
    }

    private fun getFlashlightsAppList() {
        viewModelScope.launch {
            downloadAppRepository.getFlashlightsAppList()
                .collect{ resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _isLoading.value = true
                        }
                        is Resource.Success -> {
                            _isLoading.value = false
                            resource.data?.let {
                                _appList.value = it
                            }
                        }
                        is Resource.Error -> {
                            _isLoading.value = false
                            _errorString.value = "error"
                        }
                    }
                }
        }
    }

    private fun getColorLightsAppList() {
        viewModelScope.launch {
            downloadAppRepository.getColorLightsAppList()
                .collect{ resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _isLoading.value = true
                        }
                        is Resource.Success -> {
                            _isLoading.value = false
                            resource.data?.let {
                                _appList.value = it
                            }
                        }
                        is Resource.Error -> {
                            _isLoading.value = false
                            _errorString.value = "error"
                        }
                    }
                }
        }
    }

    private fun getSosAlertsAppList() {
        viewModelScope.launch {
            downloadAppRepository.getSosAlertsAppList()
                .collect{ resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _isLoading.value = true
                        }
                        is Resource.Success -> {
                            _isLoading.value = false
                            resource.data?.let {
                                _appList.value = it
                            }
                        }
                        is Resource.Error -> {
                            _isLoading.value = false
                            _errorString.value = "error"
                        }
                    }
                }
        }
    }

    fun filterFromSearchTab(p0:String?, model: List<DownloadAppListUiModel>, adapter: AppListAdapter) {
        val searched = model.filter { downloadAppListUiModel ->
            downloadAppListUiModel.name.contains(p0!!)
        }
        adapter.submitList(searched)

    }
    fun sortedByDescending() : List<DownloadAppListUiModel> {
        return appList.value?.sortedByDescending {
            it.ratingValue
        } ?: emptyList()
    }
}