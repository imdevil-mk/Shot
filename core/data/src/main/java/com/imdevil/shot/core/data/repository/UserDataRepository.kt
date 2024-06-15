package com.imdevil.shot.core.data.repository

import com.imdevil.shot.core.model.data.Cookie
import com.imdevil.shot.core.model.data.TencentUser
import com.imdevil.shot.core.model.data.UserData
import com.imdevil.shot.datastore.UserDataDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val userDataDataSource: UserDataDataSource,
) {

    val userData: Flow<UserData> = userDataDataSource.userData

    val tCookies: Flow<List<Cookie>> = userDataDataSource.tCookies

    val tUser: Flow<TencentUser> = userDataDataSource.tUser

    suspend fun setTencentCookies(newCookies: List<Cookie>) {
        userDataDataSource.setTencentCookies(newCookies)
    }

    suspend fun setTencentUser(user: TencentUser) {
        userDataDataSource.setTencentUser(user)
    }

    suspend fun setTheme(theme: Int) {
        userDataDataSource.setTheme(theme)
    }
}
