package com.imdevil.shot.core.data.repository

import com.imdevil.core.common.Dispatcher
import com.imdevil.core.common.ShotDispatchers
import com.imdevil.core.common.di.ApplicationScope
import com.imdevil.core.common.utils.WhileViewSubscribed
import com.imdevil.shot.core.model.data.Cookie
import com.imdevil.shot.core.model.data.TencentUser
import com.imdevil.shot.core.model.data.UserPrefs
import com.imdevil.shot.core.model.data.VISITOR
import com.imdevil.shot.datastore.UserDataDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val userDataDataSource: UserDataDataSource,
    @Dispatcher(ShotDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    @Dispatcher(ShotDispatchers.MAIN) private val mainDispatcher: CoroutineDispatcher,
    @ApplicationScope val applicationScope: CoroutineScope
) {

    val userPrefs: Flow<UserPrefs> = userDataDataSource.userPrefs

    val userCookies: StateFlow<List<Cookie>> = userDataDataSource.userCookies
        .stateIn(applicationScope, WhileViewSubscribed, emptyList())

    val userInfo: StateFlow<TencentUser> = userDataDataSource.userInfo
        .stateIn(applicationScope, WhileViewSubscribed, VISITOR)

    suspend fun setTencentCookies(newCookies: List<Cookie>) {
        userDataDataSource.setTencentCookies(newCookies)
    }

    suspend fun setTencentUser(user: TencentUser) {
        userDataDataSource.setTencentUser(user)
    }

    suspend fun setTheme(theme: Int) {
        userDataDataSource.setTheme(theme)
    }

    companion object {
        private const val TAG = "UserDataRepository"
    }
}
