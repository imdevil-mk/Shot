package com.imdevil.shot.core.data.di

import com.imdevil.core.common.Dispatcher
import com.imdevil.core.common.ShotDispatchers
import com.imdevil.core.common.di.ApplicationScope
import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.shot.core.data.repository.UserDataRepository
import com.imdevil.shot.core.data.viewmodel.NetworkSignInViewModelDelegate
import com.imdevil.shot.core.data.viewmodel.SignInViewModelDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SignInViewModelDelegateModule {

    @Singleton
    @Provides
    fun provideSignInViewModelDelegate(
        userDataRepository: UserDataRepository,
        network: TencentNetworkDataSource,
        @Dispatcher(ShotDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @Dispatcher(ShotDispatchers.MAIN) mainDispatcher: CoroutineDispatcher,
        @ApplicationScope applicationScope: CoroutineScope
    ): SignInViewModelDelegate {
        return NetworkSignInViewModelDelegate(
            userDataRepository, network, ioDispatcher, mainDispatcher, applicationScope
        )
    }
}
