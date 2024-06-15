package com.imdevil.feature.tencent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.imdevil.core.tencent.TencentNetworkDataSource
import com.imdevil.core.tencent.bean.HotKey
import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.tencent.bean.SongBrief
import com.imdevil.shot.core.data.repository.UserDataRepository
import com.imdevil.shot.core.model.data.Cookie
import com.imdevil.shot.core.network.common.model.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import okhttp3.ResponseBody
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class TencentViewModel @Inject constructor(
    application: Application,
    private val userDataRepository: UserDataRepository,
    private val tencentRepository: TencentNetworkDataSource,
) : AndroidViewModel(application) {

    val cookies: StateFlow<List<Cookie>> = userDataRepository.tCookies
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            emptyList(),
        )

    suspend fun getUserInfo(
        uin: String,
    ): ResponseBody {
        return tencentRepository.getUserInfo(uin)
    }

    suspend fun getPlaylistBriefByUser(
        uin: String,
        size: Int = 200,
    ): ApiResponse<List<PlaylistBrief>> {
        return tencentRepository.getPlaylistBriefByUser(uin, size)
    }

    suspend fun getRecommendPlaylist(): ApiResponse<List<PlaylistBrief>> {
        return tencentRepository.getRecommendPlaylist()
    }

    suspend fun getNewSongs(): ApiResponse<List<SongBrief>> {
        return tencentRepository.getNewSongs()
    }

    suspend fun getHotKeys(): ApiResponse<List<HotKey>> {
        return tencentRepository.getHotKeys()
    }

    suspend fun getPlaylist(uin: String, tid: String): ResponseBody {
        return tencentRepository.getPlaylist(uin, tid)
    }

    suspend fun getSongDetail(mid: String): ResponseBody {
        return tencentRepository.getSongDetail(mid)
    }

    suspend fun getRecommend(): ResponseBody {
        return tencentRepository.getRecommend()
    }

    suspend fun getNewAlbumArea(): ResponseBody {
        return tencentRepository.getNewAlbumArea()
    }

    suspend fun getNewAlbumInArea(): ResponseBody {
        return tencentRepository.getNewAlbumInArea()
    }

    suspend fun getLeaderBoard(): ResponseBody {
        return tencentRepository.getLeaderBoard()
    }

    suspend fun getHotCategory(): ResponseBody {
        return tencentRepository.getHotCategory()
    }

    suspend fun getPlaylistByCategory(): ResponseBody {
        return tencentRepository.getPlaylistByCategory()
    }
}
