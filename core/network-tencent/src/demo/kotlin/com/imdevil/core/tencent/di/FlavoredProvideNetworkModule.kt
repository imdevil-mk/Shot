package com.imdevil.core.tencent.di

import android.content.Context
import com.imdevil.core.tencent.demo.DemoAssetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FlavoredProvideNetworkModule {
    @Provides
    @Singleton
    fun providesDemoAssetManager(
        @ApplicationContext context: Context,
    ): DemoAssetManager = DemoAssetManager(context.assets::open)
}