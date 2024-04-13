package com.imdevil.feature.tencent.di

import com.imdevil.core.tencent.model.ICookie
import com.imdevil.feature.tencent.cookie.TencentCookieManager
import com.imdevil.feature.tencent.repository.TencentNetworkRepository
import com.imdevil.feature.tencent.repository.TencentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsTencentCookieManager(
        tencentCookie: TencentCookieManager,
    ): ICookie

    @Binds
    fun bindsTencentRepository(
        tencentRepository: TencentNetworkRepository,
    ): TencentRepository
}