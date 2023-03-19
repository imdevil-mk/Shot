package com.imdevil.shot.datastore

import androidx.datastore.core.DataStore
import com.imdevil.shot.common.base.Dog
import com.imdevil.shot.data.model.UserProfile
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class NeteaseUserProfileDataSource @Inject constructor(
    private val neteaseUserProfile: DataStore<NeteaseUserProfile>,
) {
    val userProfile = neteaseUserProfile.data.map {
        UserProfile(
            it.uid,
            it.name,
            it.avatar,
        )
    }

    suspend fun update(userProfile: UserProfile) {
        try {
            neteaseUserProfile.updateData {
                it.copy {
                    uid = userProfile.uid
                    name = userProfile.name
                    avatar = userProfile.avatar
                }
            }
        } catch (ioException: IOException) {
            Dog.e("NeteaseUserProfile", "Failed to update user profile", ioException)
        }
    }
}