package com.imdevil.shot.datastore

import androidx.datastore.core.DataStore
import com.imdevil.core.common.log.Dog
import com.imdevil.shot.core.datastore.ProtoCookie
import com.imdevil.shot.core.datastore.TencentUserProfile
import com.imdevil.shot.core.datastore.UserPreferences
import com.imdevil.shot.core.datastore.copy
import com.imdevil.shot.core.model.data.Cookie
import com.imdevil.shot.core.model.data.TencentUser
import com.imdevil.shot.core.model.data.UserPrefs
import com.imdevil.shot.core.model.data.VISITOR
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataDataSource @Inject constructor(
    private val tencentUserProfile: DataStore<TencentUserProfile>,
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userPrefs = userPreferences.data.map {
        UserPrefs(
            it.theme,
        )
    }

    val userCookies = tencentUserProfile.data.map {
        Dog.d(TAG, "userCookies: ${it.cookiesList.size}")
        it.cookiesList.map { pc ->
            Cookie(
                pc.name,
                pc.value,
                pc.domain,
                pc.expirationDate,
                pc.path,
            )
        }
    }

    val userInfo = tencentUserProfile.data.map {
        Dog.d(TAG, "userInfo: ${it.name}")
        if (it.name.isNullOrEmpty()) {
            VISITOR
        } else {
            TencentUser(
                it.name,
                it.uin,
                it.euin,
                it.avtar,
                it.follows,
                it.followedSingers,
                it.followedUsers,
                it.fans,
                it.friends,
                it.visitors,
                it.iconsList
            )
        }
    }

    suspend fun setTencentCookies(newCookies: List<Cookie>) {
        tencentUserProfile.updateData {
            it.copy {
                this.cookies.addAll(
                    newCookies.map { cookie ->
                        ProtoCookie.newBuilder()
                            .setName(cookie.name)
                            .setValue(cookie.value)
                            .setDomain(cookie.domain)
                            .setExpirationDate(cookie.expirationDate)
                            .setPath(cookie.path)
                            .build()
                    },
                )
            }
        }
    }

    suspend fun setTencentUser(user: TencentUser) {
        Dog.d(TAG, "setTencentUser: $user")
        tencentUserProfile.updateData {
            it.copy {
                this.name = user.name
                this.uin = user.uin
                this.euin = user.euin
                this.avtar = user.avatar
                this.follows = user.follows
                this.followedSingers = user.followedSingers
                this.followedUsers = user.followedUsers
                this.fans = user.fans
                this.friends = user.friends
                this.visitors = user.visitors
                this.icons.addAll(user.icons)
            }
        }
    }

    suspend fun setTheme(theme: Int) {
        userPreferences.updateData {
            it.copy {
                this.theme = theme
            }
        }
    }

    companion object {
        private const val TAG = "UserDataDataSource"
    }
}
