package com.imdevil.shot.netease.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdevil.shot.data.Resource
import com.imdevil.shot.data.model.LoginStatus
import com.imdevil.shot.data.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NeteaseLoginViewModel @Inject constructor(
    private val neteaseLoginRepository: NeteaseLoginRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "NeteaseLoginViewModel"
    }

    private val _loginStatus = MutableStateFlow<Resource<LoginStatus>>(Resource.Loading)
    val loginStatus: StateFlow<Resource<LoginStatus>> = _loginStatus

    private val _userProfile = MutableStateFlow<Resource<UserProfile>>(Resource.Loading)
    val userProfile: StateFlow<Resource<UserProfile>> = _userProfile


    fun login(username: String, password: String) {
        viewModelScope.launch {
            when(neteaseLoginRepository.login(username, password)) {
                is Resource.Success -> {

                }
                else -> {

                }
            }
        }
    }

    fun fetchLoginStatus(uid: String) {
        viewModelScope.launch {
            val status = neteaseLoginRepository.fetchLoginStatus(uid)
        }
    }
}