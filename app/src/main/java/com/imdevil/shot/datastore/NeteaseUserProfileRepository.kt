package com.imdevil.shot.datastore

import com.imdevil.shot.data.model.UserProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NeteaseUserProfileRepository @Inject constructor(
    private val dataSource: NeteaseUserProfileDataSource,
) {

    val userProfile: Flow<UserProfile> = dataSource.userProfile

    suspend fun update(userProfile: UserProfile) = dataSource.update(userProfile)
}