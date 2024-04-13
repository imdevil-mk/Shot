package com.imdevil.shot.feature.settings.cookie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.imdevil.core.common.extensions.print
import com.imdevil.core.common.log.Dog
import com.imdevil.shot.core.data.repository.UserDataRepository
import com.imdevil.shot.core.model.data.Cookie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class AddCookiesViewModel @Inject constructor(
    application: Application,
    private val userDataRepository: UserDataRepository,
) : AndroidViewModel(application) {

    val cookies: StateFlow<List<Cookie>> = userDataRepository.tCookies
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            emptyList()
        )

    fun setUserCookies(cookies: List<Cookie>) {
        Dog.d(TAG, "setUserCookies:\n ${cookies.print()}")
        viewModelScope.launch {
            userDataRepository.setTencentCookies(cookies)
        }
    }

    companion object {
        private const val TAG = "AddCookiesViewModel"
    }
}