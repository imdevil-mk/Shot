package com.imdevil.shot.netease.ui.login

import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class NeteaseLoginRepository @Inject constructor(
    private val remoteData: NeteaseLoginDataSource,
    private val externalScope: CoroutineScope
) {

    suspend fun login(username: String, password: String) = remoteData.login(username, password)
}