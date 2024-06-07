package com.imdevil.core.tencent.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkProvideModule {
    @Provides
    @Singleton
    fun providesTencentHttpUrl(): HttpUrl = "https://c.y.qq.com".toHttpUrl()
}