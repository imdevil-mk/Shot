package com.imdevil.core.tencent.retrofit

import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.core.tencent.bean.HotKey
import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.tencent.bean.SongBrief
import com.imdevil.core.tencent.model.ICookie
import com.imdevil.core.tencent.okhttp.CookieInterceptor
import com.imdevil.shot.core.network.common.model.ApiResponse
import com.imdevil.shot.core.network.common.okhttp.HostInterceptor
import com.imdevil.shot.core.network.common.retrofit.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
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
    private val cookieManager: ICookie,
    private val httpUrl: HttpUrl,
    private val moshi: Moshi,
) : TencentNetworkDataSource {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .protocols(listOf(Protocol.HTTP_1_1))
        .addInterceptor(HostInterceptor())
        .addInterceptor(CookieInterceptor(cookieManager))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(httpUrl)
        .addCallAdapterFactory(ApiResponseCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val api: TencentApi = retrofit.create(TencentApi::class.java)

    override suspend fun getUserInfo(uin: String): ResponseBody {
        return api.getUserInfo(uin)
    }

    override suspend fun getPlaylistBriefByUser(
        uin: String,
        size: Int,
    ): ApiResponse<List<PlaylistBrief>> {
        return api.getPlaylistBriefByUser(uin, size)
    }

    override suspend fun getRecommendPlaylist(): ApiResponse<List<PlaylistBrief>> {
        return api.getRecommendPlaylist()
    }

    override suspend fun getNewSongs(): ApiResponse<List<SongBrief>> {
        return api.getNewSongs()
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

    override suspend fun getHotKeys(): ApiResponse<List<HotKey>> {
        return api.getHotKeys()
    }

    override suspend fun getPlaylist(uin: String, tid: String): ResponseBody {
        return api.getPlaylist(uin, tid)
    }

    override suspend fun getRecommend(): ResponseBody {
        return api.getRecommend()
    }

    override suspend fun getNewAlbumArea(): ResponseBody {
        return api.getNewAlbumArea()
    }

    override suspend fun getNewAlbumInArea(): ResponseBody {
        return api.getNewAlbumInArea()
    }

    override suspend fun getLeaderBoard(): ResponseBody {
        return api.getLeaderBoard()
    }

    override suspend fun getHotCategory(): ResponseBody {
        return api.getHotCategory()
    }

    override suspend fun getPlaylistByCategory(): ResponseBody {
        return api.getPlaylistByCategory()
    }
}
