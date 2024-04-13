package com.imdevil.shot.core.data.repository

import com.imdevil.shot.core.model.data.Cookie
import com.imdevil.shot.core.model.data.TencentUser
import com.imdevil.shot.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>
    val tCookies: Flow<List<Cookie>>
    val tUser: Flow<TencentUser>

    suspend fun setTencentCookies(newCookies: List<Cookie>)
    suspend fun setTencentUser(user: TencentUser)
    suspend fun setTheme(theme: Int)
}