package com.imdevil.shot.core.data.repository.di

import com.imdevil.shot.core.data.repository.UserDataRepository
import com.imdevil.shot.core.data.repository.offline.OfflineUserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsUserDataRepository(
        userDataRepository: OfflineUserDataRepository,
    ): UserDataRepository
}