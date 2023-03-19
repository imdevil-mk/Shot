package com.imdevil.netease

import android.util.Log
import cn.hutool.crypto.SecureUtil
import com.imdevil.netease.model.ApiResponse
import com.imdevil.netease.model.response.LoginResponse
import com.imdevil.netease.policy.adapters.CatchingCallAdapterFactory
import com.imdevil.netease.policy.adapters.LoginCallAdapterFactory
import com.imdevil.netease.policy.cookie.ICookie
import com.imdevil.netease.policy.interceptor.NeteaseHostInterceptor
import com.imdevil.netease.policy.interceptor.NeteaseInterceptor
import com.imdevil.netease.policy.moshi.DefaultIfNullFactory
import com.imdevil.netease.policy.moshi.MoshiApiResponseTypeAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG = "NeteaseManager"

class NeteaseManager private constructor(
    private val service: NeteaseService,
) : NeteaseService by service {


    override suspend fun logWithEmail(
        id: String,
        pwd: String,
        remember: Boolean
    ): ApiResponse<LoginResponse> {
        Log.d(TAG, "logWithEmail: [$id, $pwd]")
        return service.logWithEmail(id, SecureUtil.md5(pwd), remember)
    }

    companion object {
        fun getInstance(cookieManager: ICookie) = NeteaseManager(getService(cookieManager))

        private fun getService(cookieManager: ICookie): NeteaseService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(NeteaseHostInterceptor())
                .addInterceptor(NeteaseInterceptor(cookieManager))
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

            val moshi = Moshi.Builder()
                .add(MoshiApiResponseTypeAdapterFactory())
                .add(DefaultIfNullFactory())
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://music.163.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(LoginCallAdapterFactory(cookieManager))
                .addCallAdapterFactory(CatchingCallAdapterFactory())
                .build()

            return retrofit.create(NeteaseService::class.java)
        }
    }
}