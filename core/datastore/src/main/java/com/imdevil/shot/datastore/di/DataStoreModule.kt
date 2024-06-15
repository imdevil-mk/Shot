package com.imdevil.shot.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.imdevil.core.common.Dispatcher
import com.imdevil.core.common.ShotDispatchers.IO
import com.imdevil.core.common.di.ApplicationScope
import com.imdevil.shot.core.datastore.TencentUserProfile
import com.imdevil.shot.core.datastore.UserPreferences
import com.imdevil.shot.datastore.preference.UserPreferenceSerializer
import com.imdevil.shot.datastore.user.TencentUserProfileSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {
    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        userPreferenceSerializer: UserPreferenceSerializer,
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferenceSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
        ) {
            context.dataStoreFile("user_preferences.pb")
        }

    @Provides
    @Singleton
    fun providesTencentUserProfileDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        userProfileSerializer: TencentUserProfileSerializer,
    ): DataStore<TencentUserProfile> =
        DataStoreFactory.create(
            serializer = userProfileSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
        ) {
            context.dataStoreFile("tencent_user_profile.pb")
        }
}
