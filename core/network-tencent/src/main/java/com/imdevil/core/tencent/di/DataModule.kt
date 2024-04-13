package com.imdevil.core.tencent.di

import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.core.tencent.retrofit.RetrofitTencentNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsTencentNetworkDataSource(
        networkDataSource: RetrofitTencentNetwork,
    ): TencentNetworkDataSource
}