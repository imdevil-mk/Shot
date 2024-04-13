package com.imdevil.core.tencent.retrofit

import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.tencent.model.ICookie
import com.imdevil.core.tencent.moshi.ApiResponseAdapter
import com.imdevil.core.tencent.moshi.PlaylistAdapter
import com.imdevil.core.tencent.moshi.PlaylistBriefListAdapter
import com.imdevil.core.tencent.okhttp.CookieInterceptor
import com.imdevil.shot.core.network.common.model.ApiResponse
import com.imdevil.shot.core.network.common.okhttp.HostInterceptor
import com.imdevil.shot.core.network.common.retrofit.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitTencentNetwork @Inject constructor(
    private val cookieManager: ICookie
) : TencentNetworkDataSource {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .protocols(listOf(Protocol.HTTP_1_1))
        .addInterceptor(HostInterceptor())
        .addInterceptor(CookieInterceptor(cookieManager))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val moshi: Moshi = Moshi.Builder()
        .add(ApiResponseAdapter.newFactory())
        .add(PlaylistBriefListAdapter.newFactory())
        .add(PlaylistAdapter.newFactory())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://c.y.qq.com")
        .addCallAdapterFactory(ApiResponseCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val api: TencentApi = retrofit.create(TencentApi::class.java)

    override suspend fun getUserInfo(uin: String): ResponseBody {
        return api.getUserInfo(uin)
    }

    override suspend fun getPlaylistBriefByUser(
        uin: String,
        size: Int
    ): ApiResponse<List<PlaylistBrief>> {
        return api.getPlaylistBriefByUser(uin, size)
    }

    override suspend fun getPlaylist(uin: String, tid: String): ResponseBody {
        return api.getPlaylist(uin, tid)
    }

    override suspend fun getSongDetail(mid: String): ResponseBody {
        val param = """
            {
              "songinfo": {
                "method": "get_song_detail_yqq",
                "module": "music.pf_song_detail_svr",
                "param": {
                  "song_mid": "$mid"
                }
              }
            }
        """.trimIndent()
        return api.getSongDetail(param)
    }
}