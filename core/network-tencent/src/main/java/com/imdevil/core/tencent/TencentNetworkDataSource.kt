package com.imdevil.core.tencent

import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.shot.core.network.common.model.ApiResponse
import okhttp3.ResponseBody

interface TencentNetworkDataSource {

    suspend fun getUserInfo(
        uin: String,
    ): ResponseBody

    suspend fun getPlaylistBriefByUser(
        uin: String,
        size: Int = 200,
    ): ApiResponse<List<PlaylistBrief>>

    suspend fun getPlaylist(
        uin: String,
        tid: String,
    ): ResponseBody

    suspend fun getSongDetail(
        mid: String
    ): ResponseBody
}