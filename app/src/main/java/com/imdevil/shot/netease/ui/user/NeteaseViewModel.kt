package com.imdevil.shot.netease.ui.user

import androidx.lifecycle.ViewModel
import com.imdevil.shot.data.model.UserProfile
import com.imdevil.shot.datastore.NeteaseUserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NeteaseViewModel @Inject constructor(
    private val neteaseRepository: NeteaseRepository,
    private val userRepository: NeteaseUserProfileRepository
) : ViewModel() {

    val userProfile: Flow<UserProfile> = userRepository.userProfile
}