package com.imdevil.feature.tencent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.shot.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TencentViewModel @Inject constructor(
    application: Application,
    private val userDataRepository: UserDataRepository,
    private val tencentRepository: TencentNetworkDataSource,
) : AndroidViewModel(application) {

}
