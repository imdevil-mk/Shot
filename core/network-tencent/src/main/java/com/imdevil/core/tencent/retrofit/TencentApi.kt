package com.imdevil.core.tencent.retrofit

import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.shot.core.network.common.model.ApiResponse
import com.imdevil.shot.core.network.common.model.Host
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface TencentApi {

    /**
     * 获取用户主页信息
     */
    @GET("/rsc/fcgi-bin/fcg_get_profile_homepage.fcg")
    suspend fun getUserInfo(
        @Query("userid") userid: String,
        @Query("cid") cid: Int = 205360838,
        @Query("reqfrom") reqfrom: Int = 1,
        /*
        @Query("hostuin") hostuin: String,
        @Query("loginUin") loginUin: String,
        @Query("hostUin") hostUin: String = "0",
        */
        @Query("cv") cv: Int = 4747474,
        @Query("ct") ct: Int = 24,
        @Query("reqtype") reqtype: Int = 0,
        @Query("g_tk") g_tk: Int = 5381,
        @Query("format") format: String = "json",
        @Query("inCharset") inCharset: String = "utf8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("notice") notice: String = "0",
        @Query("platform") platform: String = "yqq.json",
        @Query("needNewCode") needNewCode: String = "0",
    ): ResponseBody

    /**
     * 获取用户创建的歌单列表
     */
    @Headers("Referer: https://y.qq.com/portal/profile.html")
    @GET("/rsc/fcgi-bin/fcg_user_created_diss")
    suspend fun getPlaylistBriefByUser(
        @Query("hostuin") uin: String,
        @Query("size") size: Int = 200,
        @Query("hostUin") hostUin: String = "0",
        @Query("sin") sin: Int = 0,
        @Query("g_tk") g_tk: Int = 5381,
        @Query("loginUin") loginUin: String = "0",
        @Query("format") format: String = "json",
        @Query("inCharset") inCharset: String = "utf8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("notice") notice: String = "0",
        @Query("platform") platform: String = "yqq.json",
        @Query("needNewCode") needNewCode: String = "0",
    ): ApiResponse<List<PlaylistBrief>>

    /**
     * 获取歌单详细信息
     */
    @Headers("Referer: https://y.qq.com/portal/profile.html")
    @GET("/qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg")
    suspend fun getPlaylist(
        @Query("loginUin") uin: String,
        @Query("disstid") tid: String,
        @Query("format") format: String = "json",
        @Query("type") sin: Int = 1,
        @Query("utf8") size: Int = 1,
    ): ResponseBody

    /**
     * 获取歌曲详细信息
     */
    @Host("u.y.qq.com")
    @GET("/cgi-bin/musicu.fcg")
    suspend fun getSongDetail(
        @Query("data") data: String,
    ): ResponseBody
}