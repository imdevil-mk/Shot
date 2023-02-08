package com.imdevil.shot.netease.ui.login

import com.imdevil.netease.NeteaseManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NeteaseLoginDataSource @Inject constructor(
    private val api: NeteaseManager,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun login(username: String, password: String) = withContext(ioDispatcher) {
        api.logWithEmail(username, password, true)
    }

    suspend fun fetchLoginStatus(uid: String) = withContext(ioDispatcher) {
        api.loginStatus(uid)
    }
}


