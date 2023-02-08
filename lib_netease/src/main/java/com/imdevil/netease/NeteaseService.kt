package com.imdevil.netease

import com.imdevil.netease.model.ApiResponse
import com.imdevil.netease.model.user.LoginResponse
import com.imdevil.netease.model.user.LoginStatusResponse
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

    @GET("/weapi/user/level")
    fun getUserInfo(): Call<ResponseBody>

    @GET("/weapi/v3/song/detail")
    fun getDetail(id: Long)

    @NeteaseCrypto(CryptoType.EAPI)
    @NeteaseHost("interface3.music.163.com")
    @GET("/eapi/song/enhance/player/url")
    fun getSongPlayInfo(@Query("ids") id: String, @Query("br") br: Int): Call<ResponseBody>
}