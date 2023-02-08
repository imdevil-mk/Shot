package com.imdevil.shot.di

import android.app.Application
import com.imdevil.netease.NeteaseManager
import com.imdevil.netease.policy.cookie.AndroidCookieManager
import com.imdevil.netease.policy.cookie.ICookie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NeteaseModule {

    @Provides
    @Singleton
    @NeteaseCookieFile
    fun provideCookieFile(application: Application): File {
        val file = File(application.filesDir, "netease_cookie")

        if (!file.exists()) {
            file.createNewFile()
        }

        return File(application.filesDir, "netease_cookie")
    }

    @Provides
    @Singleton
    @NeteaseUserFile
    fun provideUserFile(application: Application): File {
        val file = File(application.filesDir, "netease_user")

        if (!file.exists()) {
            file.createNewFile()
        }

        return File(application.filesDir, "netease_user")
    }

    @Provides
    @Singleton
    fun provideCookieManager(
        @NeteaseCookieFile cookieFile: File,
        @NeteaseUserFile userFile: File
    ): ICookie {
        return AndroidCookieManager(cookieFile, userFile)
    }

    @Provides
    @Singleton
    fun provideNeteaseManager(cookieManager: ICookie): NeteaseManager {
        return NeteaseManager.getInstance(cookieManager)
    }

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideExternalDispatcher(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }
}