package com.imdevil.shot.netease.ui.user

import com.imdevil.netease.NeteaseManager
import com.imdevil.netease.model.ApiResponse
import com.imdevil.netease.model.user.LoginStatusResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NeteaseDataSource @Inject constructor(
    private val api: NeteaseManager,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun checkLoginStatus(): ApiResponse<LoginStatusResponse> = withContext(ioDispatcher) {
        api.loginStatus("48919251")
    }
}