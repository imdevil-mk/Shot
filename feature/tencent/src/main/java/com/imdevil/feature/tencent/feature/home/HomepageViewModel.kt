package com.imdevil.feature.tencent.feature.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.imdevil.shot.core.data.viewmodel.SignInViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    signInViewModelDelegate: SignInViewModelDelegate,
    @ApplicationContext context: Context,
) : ViewModel(),
    SignInViewModelDelegate by signInViewModelDelegate {

}