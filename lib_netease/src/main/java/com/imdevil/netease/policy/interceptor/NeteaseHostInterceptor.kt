package com.imdevil.netease.policy.interceptor

import com.imdevil.netease.policy.annotations.NeteaseHost
import com.imdevil.netease.policy.getInterestedAnnotation
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class NeteaseHostInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val hostAnnotation: NeteaseHost? = chain.request().getInterestedAnnotation()

        val host = hostAnnotation?.host ?: "music.163.com"

        val url: HttpUrl = chain.request().url.newBuilder().host(host).build()

        val newRequest = chain.request().newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }

}