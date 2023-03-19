package com.imdevil.shot.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.imdevil.shot.datastore.NeteaseUserProfile
import com.imdevil.shot.datastore.NeteaseUserProfileSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        neteaseUserProfileSerializer: NeteaseUserProfileSerializer
    ): DataStore<NeteaseUserProfile> =
        DataStoreFactory.create(
            serializer = neteaseUserProfileSerializer,
        ) {
            context.dataStoreFile("netease_user_profile.pb")
        }
}