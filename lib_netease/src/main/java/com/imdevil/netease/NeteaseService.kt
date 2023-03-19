package com.imdevil.netease

import com.imdevil.netease.model.ApiResponse
import com.imdevil.netease.model.response.*
import com.imdevil.netease.policy.annotations.CryptoType
import com.imdevil.netease.policy.annotations.NeteaseCrypto
import com.imdevil.netease.policy.annotations.NeteaseHost
import com.imdevil.netease.policy.annotations.NeteaseLogin
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface NeteaseService {

    @NeteaseLogin
    @GET("/weapi/login")
    suspend fun logWithEmail(
        @Query("username") id: String,
        @Query("password") pwd: String,
        @Query("rememberLogin") remember: Boolean
    ): ApiResponse<LoginResponse>

    @GET("/weapi/w/nuser/account/get")
    suspend fun loginStatus(@Query("uid") uid: String): ApiResponse<LoginStatusResponse>

    @GET("/api/user/playlist")
    suspend fun getPlaylists(
        @Query("uid") uid: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("includeVideo") includeVideo: Boolean,
    ): ApiResponse<PlaylistResponse>

    @GET("/api/v6/playlist/detail")
    suspend fun getPlaylistDetail(
        @Query("id") id: String,
        @Query("n") limit: Int = 10000,
        @Query("s") maxSubscribers: Int = 8,
    ): ApiResponse<PlaylistDetailResponse>

    /*
    c: [{"id":124124124},{"id":124124124}]
     */
    @GET("/api/v3/song/detail")
    suspend fun getSongDetailBatch(@Query("c") id: String): ApiResponse<SongDetailBatchResponse>

    /*
    ids: [405998841,33894312]
     */
    @NeteaseCrypto(CryptoType.EAPI)
    @NeteaseHost("interface3.music.163.com")
    @GET("/eapi/song/enhance/player/url")
    suspend fun getSongPlayInfo(
        @Query("ids") id: String,
        @Query("br") br: Int = 999000
    ): ApiResponse<SongPlayedBatchResponse>

    @GET("/api/nuser/account/get")
    fun getAccount(): Call<ResponseBody>

    @GET("/weapi/subcount")
    fun getSubCount(): Call<ResponseBody>

    @GET("/weapi/user/level")
    fun getUserInfo(): Call<ResponseBody>
}