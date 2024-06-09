package com.imdevil.core.tencent.demo

import com.imdevil.core.common.Dispatcher
import com.imdevil.core.common.ShotDispatchers
import com.imdevil.core.tencent.JvmUnitTestDemoAssetManager
import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.core.tencent.bean.HotKey
import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.tencent.bean.SongBrief
import com.imdevil.core.tencent.moshi.MoshiAdapters
import com.imdevil.core.tencent.moshi.findApiResponseAdapter
import com.imdevil.shot.core.network.common.model.ApiResponse
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.asResponseBody
import okio.buffer
import okio.source
import javax.inject.Inject

class DemoTencentNetworkDataSource @Inject constructor(
    @Dispatcher(ShotDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val moshi: Moshi,
    private val assets: DemoAssetManager = JvmUnitTestDemoAssetManager,
) : TencentNetworkDataSource {
    private fun makeResponseBody(file: String): ResponseBody {
        return assets.open(file).source().buffer().asResponseBody()
    }

    override suspend fun getUserInfo(uin: String): ResponseBody {
        return makeResponseBody("user_info.json")
    }

    override suspend fun getPlaylistBriefByUser(
        uin: String,
        size: Int
    ): ApiResponse<List<PlaylistBrief>> {
        val adapter =
            moshi.findApiResponseAdapter<List<PlaylistBrief>>(MoshiAdapters.USER_CREATE_PLAYLIST_JSON_ADAPTER)
        val data = adapter.fromJson(assets.open("user_create_playlist.json").source().buffer())
        return data ?: ApiResponse.OtherError(IllegalStateException("Moshi Fail"))
    }

    override suspend fun getRecommendPlaylist(): ApiResponse<List<PlaylistBrief>> {
        val adapter =
            moshi.findApiResponseAdapter<List<PlaylistBrief>>(MoshiAdapters.RECOMMEND_PLAYLIST_JSON_ADAPTER)
        val data = adapter.fromJson(assets.open("recommend_playlist.json").source().buffer())
        return data ?: ApiResponse.OtherError(IllegalStateException("Moshi Fail"))
    }

    override suspend fun getNewSongs(): ApiResponse<List<SongBrief>> {
        val adapter =
            moshi.findApiResponseAdapter<List<SongBrief>>(MoshiAdapters.NEW_SONGS_JSON_ADAPTER)
        val data = adapter.fromJson(assets.open("new_songs.json").source().buffer())
        return data ?: ApiResponse.OtherError(IllegalStateException("Moshi Fail"))
    }

    override suspend fun getHotKeys(): ApiResponse<List<HotKey>> {
        val adapter =
            moshi.findApiResponseAdapter<List<HotKey>>(MoshiAdapters.HOT_KEYS_JSON_ADAPTER)
        val data = adapter.fromJson(assets.open("hot_keys.json").source().buffer())
        return data ?: ApiResponse.OtherError(IllegalStateException("Moshi Fail"))
    }

    override suspend fun getPlaylist(uin: String, tid: String): ResponseBody {
        return makeResponseBody("playlist.json")
    }

    override suspend fun getSongDetail(mid: String): ResponseBody {
        return makeResponseBody("playlist.json")
    }

    override suspend fun getRecommend(): ResponseBody {
        return makeResponseBody("recommend.json")
    }

    override suspend fun getNewAlbumArea(): ResponseBody {
        return makeResponseBody("new_album_area.json")
    }

    override suspend fun getNewAlbumInArea(): ResponseBody {
        return makeResponseBody("new_album_in_area.json")
    }

    override suspend fun getLeaderBoard(): ResponseBody {
        return makeResponseBody("leader_board.json")
    }

    override suspend fun getHotCategory(): ResponseBody {
        return makeResponseBody("hot_category.json")
    }

    override suspend fun getPlaylistByCategory(): ResponseBody {
        return makeResponseBody("playlist_by_category.json")
    }
}