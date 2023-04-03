package com.example.downloadapp.di

import com.example.downloadapp.data.DownloadAppRepositoryImp
import com.example.downloadapp.domain.DownloadAppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDownloadAppRepository(downloadAppRepositoryImp: DownloadAppRepositoryImp): DownloadAppRepository
}