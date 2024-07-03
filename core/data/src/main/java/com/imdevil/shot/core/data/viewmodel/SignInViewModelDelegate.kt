package com.imdevil.shot.core.data.viewmodel

import com.imdevil.core.common.Dispatcher
import com.imdevil.core.common.ShotDispatchers
import com.imdevil.core.common.di.ApplicationScope
import com.imdevil.core.common.log.Dog
import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.shot.core.data.repository.UserDataRepository
import com.imdevil.shot.core.model.data.TencentUser
import com.imdevil.shot.core.network.common.model.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SignInViewModelDelegate {
    val userInfo: StateFlow<TencentUser>

    val signInEvent: SharedFlow<SignInEvent>

    suspend fun signIn(uin: String)

    suspend fun emitSignOutRequest()

    val userName: String?
}

private const val TAG = "SignInViewModelDelegate"

internal class NetworkSignInViewModelDelegate @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val network: TencentNetworkDataSource,
    @Dispatcher(ShotDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    @Dispatcher(ShotDispatchers.MAIN) private val mainDispatcher: CoroutineDispatcher,
    @ApplicationScope val applicationScope: CoroutineScope
) : SignInViewModelDelegate {

    override val userInfo = userDataRepository.userInfo

    private val _signInEvent: MutableSharedFlow<SignInEvent> = MutableSharedFlow()
    override val signInEvent: SharedFlow<SignInEvent> = _signInEvent

    override suspend fun signIn(uin: String): Unit = withContext(ioDispatcher) {
        Dog.d(TAG, "signIn: $uin")
        _signInEvent.emit(SignInEvent.LOADING)
        when (val userInfoResponse = network.getUserInfo()) {
            is ApiResponse.Success -> {
                Dog.d(TAG, "signIn: ${userInfoResponse.data}")
                userDataRepository.setTencentUser(
                    TencentUser(
                        userInfoResponse.data.name,
                        uin,
                        userInfoResponse.data.uin,
                        userInfoResponse.data.avatar,
                        userInfoResponse.data.follows,
                        userInfoResponse.data.followedSingers,
                        userInfoResponse.data.followedUsers,
                        userInfoResponse.data.fans,
                        userInfoResponse.data.friends,
                        userInfoResponse.data.visitors,
                        userInfoResponse.data.icons,
                    )
                )
                _signInEvent.emit(SignInEvent.SUCCESS)
            }

            is ApiResponse.BizError -> {
                _signInEvent.emit(SignInEvent.ERROR(userInfoResponse.msg))
            }

            is ApiResponse.OtherError -> {
                _signInEvent.emit(SignInEvent.ERROR(userInfoResponse.throwable.toString()))
            }
        }
    }

    override suspend fun emitSignOutRequest() {

    }

    override val userName: String
        get() = userInfo.value.name
}

sealed interface SignInEvent {
    data object NONE : SignInEvent
    data object LOADING : SignInEvent
    data object SUCCESS : SignInEvent
    data class ERROR(val msg: String) : SignInEvent
}