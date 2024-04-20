package com.imdevil.core.tencent.okhttp

import com.imdevil.core.tencent.model.ICookie
import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor(private val cookieManger: ICookie) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Cookie", cookieManger.getCookie())
            .build()
        return chain.proceed(request)
    }
}
