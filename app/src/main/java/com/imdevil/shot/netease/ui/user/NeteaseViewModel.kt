package com.imdevil.shot.netease.ui.user

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NeteaseViewModel @Inject constructor(
    private val neteaseRepository: NeteaseRepository,
) : ViewModel() {

    private val _needLogin = MutableStateFlow(true)
    val needLogin: StateFlow<Boolean> = _needLogin


}