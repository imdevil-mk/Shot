package com.imdevil.shot.netease.ui.login

import com.imdevil.netease.model.ApiResponse
import com.imdevil.netease.policy.cookie.ICookie
import com.imdevil.shot.data.Resource
import com.imdevil.shot.data.model.LoginStatus
import com.imdevil.shot.data.model.UserProfile
import com.imdevil.shot.data.model.toUserProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class NeteaseLoginRepository @Inject constructor(
    private val remoteData: NeteaseLoginDataSource,
    private val localData: ICookie,
    private val externalScope: CoroutineScope
) {

    //private val _loginStatus = MutableStateFlow(getLoginStatus())
    //private val loginStatus: StateFlow<Boolean> = _loginStatus

    suspend fun login(username: String, password: String): Resource<UserProfile> {
        return when (val response = remoteData.login(username, password)) {
            is ApiResponse.Success -> {
                val userProfile = response.data.toUserProfile()
                Resource.Success(userProfile)
            }
            is ApiResponse.BizError -> Resource.Error(response.msg)
            is ApiResponse.OtherError -> Resource.Error(response.throwable.toString())
        }
    }

    suspend fun fetchLoginStatus(uid: String): Resource<UserProfile> {
        return when (val response = remoteData.fetchLoginStatus(uid)) {
            is ApiResponse.Success -> {
                val userProfile = response.data.toUserProfile(uid)
                Resource.Success(userProfile)
            }
            is ApiResponse.BizError -> Resource.Error(response.msg)
            is ApiResponse.OtherError -> Resource.Error(response.throwable.toString())
        }
    }

    suspend fun getLoginStatus(): LoginStatus {
        val savedUser = localData.getUserProfile()
        return LoginStatus.Logout
    }
}