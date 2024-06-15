package com.imdevil.shot.core.data.repository

import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.tencent.bean.SongBrief
import com.imdevil.shot.core.network.common.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompositeUserRecommendRepository @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val tencentDataSource: TencentNetworkDataSource,
) {

    private val playlistsCache: MutableStateFlow<ApiResponse<List<PlaylistBrief>>> =
        MutableStateFlow(ApiResponse.Success(emptyList()))
    private val newSongsCache: MutableStateFlow<ApiResponse<List<SongBrief>>> =
        MutableStateFlow(ApiResponse.Success(emptyList()))

    fun observeRecommendPlaylist(): Flow<ApiResponse<List<PlaylistBrief>>> = flow {
        emitAll(playlistsCache)
    }

    fun observeNewSongs(): Flow<ApiResponse<List<SongBrief>>> = flow {
        emitAll(newSongsCache)
    }

    suspend fun fetchPlaylists() {
        playlistsCache.emit(tencentDataSource.getRecommendPlaylist())
    }

    suspend fun fetchNewSongs() {
        newSongsCache.emit(tencentDataSource.getNewSongs())
    }
}