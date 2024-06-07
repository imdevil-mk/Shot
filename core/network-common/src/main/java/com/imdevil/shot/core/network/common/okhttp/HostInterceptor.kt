package com.imdevil.shot.core.network.common.okhttp

import com.imdevil.shot.core.network.common.model.Host
import com.imdevil.shot.core.network.common.utils.getInterestedAnnotation
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class HostInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val host: Host? = chain.request().getInterestedAnnotation()
        val request = if (host == null || chain.request().url.host.contains(TEST_URL)) {
            chain.request()
        } else {
            val url: HttpUrl = chain.request().url.newBuilder().host(host.host).build()
            chain.request().newBuilder().url(url).build()
        }
        return chain.proceed(request)
    }
}

const val TEST_URL = "127.0.0.1"
