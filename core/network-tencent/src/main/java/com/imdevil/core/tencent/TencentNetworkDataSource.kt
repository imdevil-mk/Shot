package com.imdevil.core.tencent

import com.imdevil.core.tencent.bean.HotKey
import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.tencent.bean.SongBrief
import com.imdevil.core.tencent.bean.UserInfo
import com.imdevil.shot.core.network.common.model.ApiResponse
import okhttp3.ResponseBody

interface TencentNetworkDataSource {

    suspend fun getUserInfo(): ApiResponse<UserInfo>

    suspend fun getPlaylistBriefByUser(size: Int = 200): ApiResponse<List<PlaylistBrief>>

    suspend fun getRecommendPlaylist(): ApiResponse<List<PlaylistBrief>>

    suspend fun getNewSongs(): ApiResponse<List<SongBrief>>

    suspend fun getHotKeys(): ApiResponse<List<HotKey>>

    suspend fun getPlaylist(
        uin: String,
        tid: String,
    ): ResponseBody

    suspend fun getSongDetail(
        mid: String,
    ): ResponseBody

    suspend fun getRecommend(): ResponseBody
    suspend fun getNewAlbumArea(): ResponseBody
    suspend fun getNewAlbumInArea(): ResponseBody
    suspend fun getLeaderBoard(): ResponseBody
    suspend fun getHotCategory(): ResponseBody
    suspend fun getPlaylistByCategory(): ResponseBody
}
