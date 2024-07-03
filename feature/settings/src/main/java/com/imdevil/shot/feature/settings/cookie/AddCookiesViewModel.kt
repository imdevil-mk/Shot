package com.imdevil.shot.feature.settings.cookie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.imdevil.core.common.extensions.print
import com.imdevil.core.common.log.Dog
import com.imdevil.core.common.utils.WhileViewSubscribed
import com.imdevil.shot.core.data.repository.UserDataRepository
import com.imdevil.shot.core.data.viewmodel.SignInViewModelDelegate
import com.imdevil.shot.core.model.data.Cookie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCookiesViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val signInViewModelDelegate: SignInViewModelDelegate,
    application: Application,
) : AndroidViewModel(application),
    SignInViewModelDelegate by signInViewModelDelegate {

    val cookies: StateFlow<List<Cookie>> = userDataRepository.userCookies
        .stateIn(
            viewModelScope,
            WhileViewSubscribed,
            emptyList(),
        )

    fun setUserCookies(cookies: List<Cookie>) {
        Dog.d(TAG, "setUserCookies:\n ${cookies.print()}")
        viewModelScope.launch {
            userDataRepository.setTencentCookies(cookies)
        }
    }

    fun onSignIn(uin: String) {
        viewModelScope.launch {
            signIn(uin)
        }
    }

    companion object {
        private const val TAG = "AddCookiesViewModel"
    }
}