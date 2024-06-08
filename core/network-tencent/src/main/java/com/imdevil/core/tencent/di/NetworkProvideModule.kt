package com.imdevil.core.tencent.di

import com.imdevil.core.tencent.moshi.ApiResponseAdapter
import com.imdevil.core.tencent.moshi.PlaylistAdapter
import com.imdevil.core.tencent.moshi.PlaylistBriefAdapter
import com.imdevil.core.tencent.moshi.PlaylistBriefListAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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

    @Provides
    @Singleton
    fun providesTencentMoshi(): Moshi = Moshi.Builder()
        .add(ApiResponseAdapter.newFactory())
        .add(PlaylistBriefListAdapter.newFactory())
        .add(PlaylistBriefAdapter.newFactory())
        .add(PlaylistAdapter.newFactory())
        .addLast(KotlinJsonAdapterFactory())
        .build()
}