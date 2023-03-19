package com.imdevil.shot.netease.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdevil.netease.model.ApiResponse
import com.imdevil.shot.data.model.LoginStatus
import com.imdevil.shot.data.model.UserProfile
import com.imdevil.shot.data.model.toUserProfile
import com.imdevil.shot.datastore.NeteaseUserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NeteaseLoginViewModel @Inject constructor(
    private val neteaseLoginRepository: NeteaseLoginRepository,
    private val userProfileRepository: NeteaseUserProfileRepository
) : ViewModel() {

    companion object {
        private const val TAG = "NeteaseLoginViewModel"
    }

    private val _loginStatus = MutableStateFlow<LoginStatus<UserProfile>>(LoginStatus.Logout)
    val loginStatus: StateFlow<LoginStatus<UserProfile>> = _loginStatus

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginStatus.value = LoginStatus.Logging
        when (val result = neteaseLoginRepository.login(username, password)) {
            is ApiResponse.Success -> {
                val user = result.data.toUserProfile()
                userProfileRepository.update(user)
                _loginStatus.value = LoginStatus.LoggedIn(user)
            }
            is ApiResponse.BizError -> {
                _error.emit(result.msg)
                _loginStatus.value = LoginStatus.Error(result.msg)
            }
            is ApiResponse.OtherError -> {
                _error.emit(result.throwable.toString())
                _loginStatus.value = LoginStatus.Error(result.throwable.toString())
            }
        }
    }
}