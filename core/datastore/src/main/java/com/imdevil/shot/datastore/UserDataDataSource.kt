package com.imdevil.shot.datastore

import androidx.datastore.core.DataStore
import com.imdevil.shot.core.datastore.ProtoCookie
import com.imdevil.shot.core.datastore.TencentUserProfile
import com.imdevil.shot.core.datastore.UserPreferences
import com.imdevil.shot.core.datastore.copy
import com.imdevil.shot.core.model.data.Cookie
import com.imdevil.shot.core.model.data.TencentUser
import com.imdevil.shot.core.model.data.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataDataSource @Inject constructor(
    private val tencentUserProfile: DataStore<TencentUserProfile>,
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userData = userPreferences.data.map {
        UserData(
            it.theme,
        )
    }

    val tCookies = tencentUserProfile.data.map {
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

    val tUser = tencentUserProfile.data.map {
        TencentUser(
            it.name,
        )
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
        tencentUserProfile.updateData {
            it.copy {
                this.name = user.name
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
}
