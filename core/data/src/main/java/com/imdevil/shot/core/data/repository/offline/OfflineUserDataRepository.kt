package com.imdevil.shot.core.data.repository.offline

import com.imdevil.shot.core.data.repository.UserDataRepository
import com.imdevil.shot.core.model.data.Cookie
import com.imdevil.shot.core.model.data.TencentUser
import com.imdevil.shot.core.model.data.UserData
import com.imdevil.shot.datastore.UserDataDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineUserDataRepository @Inject constructor(
    private val userDataDataSource: UserDataDataSource,
) : UserDataRepository {

    override val userData: Flow<UserData> = userDataDataSource.userData

    override val tCookies: Flow<List<Cookie>> = userDataDataSource.tCookies

    override val tUser: Flow<TencentUser> = userDataDataSource.tUser

    override suspend fun setTencentCookies(newCookies: List<Cookie>) {
        userDataDataSource.setTencentCookies(newCookies)
    }

    override suspend fun setTencentUser(user: TencentUser) {
        userDataDataSource.setTencentUser(user)
    }

    override suspend fun setTheme(theme: Int) {
        userDataDataSource.setTheme(theme)
    }
}
