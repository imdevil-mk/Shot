package com.imdevil.core.netease

import cn.hutool.crypto.SecureUtil
import com.imdevil.core.netease.model.ICookie
import com.imdevil.core.netease.moshi.MoshiApiResponseTypeAdapterFactory
import com.imdevil.core.netease.okhttp.NeteaseInterceptor
import com.imdevil.core.netease.rerofit.LoginCallAdapterFactory
import com.imdevil.core.netease.response.LoginResponse
import com.imdevil.shot.core.network.common.model.ApiResponse
import com.imdevil.shot.core.network.common.moshi.DefaultIfNullFactory
import com.imdevil.shot.core.network.common.okhttp.HostInterceptor
import com.imdevil.shot.core.network.common.retrofit.CatchingCallAdapterFactory
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
        return service.logWithEmail(id, SecureUtil.md5(pwd), remember)
    }

    companion object {
        fun getInstance(cookieManager: ICookie) = NeteaseManager(getService(cookieManager))

        private fun getService(cookieManager: ICookie): NeteaseService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HostInterceptor())
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