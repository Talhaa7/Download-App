package com.example.downloadapp.data.model

data class AppListResponseItem(
    val category: String,
    val developerName: String,
    val downloads: String,
    val iconUrl: String,
    val name: String,
    val packageName: String,
    val ratingValue: Double,
)