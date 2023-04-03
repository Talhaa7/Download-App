package com.example.downloadapp.features.adapter

import com.example.downloadapp.data.model.PublishDate

data class DownloadAppListUiModel(
    val category: String,
    val developerName: String,
    val downloads: String,
    val iconUrl: String,
    val name: String,
    val packageName: String,
    val ratingValue: Double,
)
