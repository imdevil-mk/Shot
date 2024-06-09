package com.imdevil.feature.tencent.repository

import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.core.tencent.bean.HotKey
import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.tencent.bean.SongBrief
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

    override suspend fun getRecommendPlaylist(): ApiResponse<List<PlaylistBrief>> {
        return tencentNetwork.getRecommendPlaylist()
    }

    override suspend fun getNewSongs(): ApiResponse<List<SongBrief>> {
        return tencentNetwork.getNewSongs()
    }

    override suspend fun getHotKeys(): ApiResponse<List<HotKey>> {
        return tencentNetwork.getHotKeys()
    }

    override suspend fun getPlaylist(uin: String, tid: String): ResponseBody {
        return tencentNetwork.getPlaylist(uin, tid)
    }

    override suspend fun getSongDetail(mid: String): ResponseBody {
        return tencentNetwork.getSongDetail(mid)
    }

    override suspend fun getRecommend(): ResponseBody {
        return tencentNetwork.getRecommend()
    }

    override suspend fun getNewAlbumArea(): ResponseBody {
        return tencentNetwork.getNewAlbumArea()
    }

    override suspend fun getNewAlbumInArea(): ResponseBody {
        return tencentNetwork.getNewAlbumInArea()
    }

    override suspend fun getLeaderBoard(): ResponseBody {
        return tencentNetwork.getLeaderBoard()
    }

    override suspend fun getHotCategory(): ResponseBody {
        return tencentNetwork.getHotCategory()
    }

    override suspend fun getPlaylistByCategory(): ResponseBody {
        return tencentNetwork.getPlaylistByCategory()
    }
}
