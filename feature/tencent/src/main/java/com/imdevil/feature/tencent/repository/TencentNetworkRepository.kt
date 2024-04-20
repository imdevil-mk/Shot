package com.imdevil.feature.tencent.repository

import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.shot.core.network.common.model.ApiResponse
import okhttp3.ResponseBody
import javax.inject.Inject

class TencentNetworkRepository @Inject constructor(
    private val tencentNetwork: TencentNetworkDataSource,
) : TencentRepository {

    override suspend fun getUserInfo(uin: String): ResponseBody {
        return tencentNetwork.getUserInfo(uin)
    }

    override suspend fun getPlaylistBriefByUser(
        uin: String,
        size: Int,
    ): ApiResponse<List<PlaylistBrief>> {
        return tencentNetwork.getPlaylistBriefByUser(uin, size)
    }

    override suspend fun getPlaylist(uin: String, tid: String): ResponseBody {
        return tencentNetwork.getPlaylist(uin, tid)
    }

    override suspend fun getSongDetail(mid: String): ResponseBody {
        return tencentNetwork.getSongDetail(mid)
    }
}
