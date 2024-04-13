package com.imdevil.feature.tencent.cookie

import com.imdevil.core.common.di.ApplicationScope
import com.imdevil.core.tencent.model.ICookie
import com.imdevil.shot.core.data.repository.UserDataRepository
import com.imdevil.shot.core.model.data.Cookie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TencentCookieManager @Inject constructor(
    private val userDataRepository: UserDataRepository,
    @ApplicationScope scope: CoroutineScope,
) : ICookie {

    private val cookies = mutableListOf<Cookie>()

    init {
        scope.launch {
            userDataRepository.tCookies.collect {
                cookies.clear()
                cookies.addAll(it)
            }
        }
    }

    override fun getCookie(): String {
        val sb = StringBuilder()
        cookies.forEachIndexed { index, cookie ->
            sb.append("${cookie.name}=${cookie.value}")
            if (index < cookies.size - 1) {
                sb.append(";")
            }
        }
        return sb.toString()
    }
}