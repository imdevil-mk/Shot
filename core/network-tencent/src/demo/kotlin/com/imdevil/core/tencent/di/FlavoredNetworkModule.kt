package com.imdevil.core.tencent.di

import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.core.tencent.demo.DemoTencentNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {

    @Binds
    fun binds(impl: DemoTencentNetworkDataSource): TencentNetworkDataSource
}