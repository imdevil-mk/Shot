package com.imdevil.shot.core.data.di

import com.imdevil.core.tencent.model.ICookie
import com.imdevil.shot.core.data.cookie.TencentCookieManager
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
}
