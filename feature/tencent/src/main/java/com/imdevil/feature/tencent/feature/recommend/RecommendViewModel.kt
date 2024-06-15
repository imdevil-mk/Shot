package com.imdevil.feature.tencent.feature.recommend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.imdevil.core.ui.recyclerview.ViewList
import com.imdevil.feature.tencent.model.UiPlaylistBrief
import com.imdevil.feature.tencent.model.mapToUiPlaylistBrief
import com.imdevil.feature.tencent.model.mapToUiSongBrief
import com.imdevil.shot.core.data.repository.CompositeUserRecommendRepository
import com.imdevil.shot.core.network.common.model.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val appContext: Application,
    private val userRecommendRepository: CompositeUserRecommendRepository,
) : AndroidViewModel(appContext) {

    init {
        viewModelScope.launch {
            userRecommendRepository.fetchPlaylists()
            userRecommendRepository.fetchNewSongs()
        }
    }

    val recommendFeed = combine(
        userRecommendRepository.observeRecommendPlaylist(),
        userRecommendRepository.observeNewSongs(),
    ) { playlists, songs ->
        if (playlists is ApiResponse.Success && songs is ApiResponse.Success) {
            RecommendFeedUiState.Success(buildList {
                add(
                    ViewList(
                        UiPlaylistBrief::class.java,
                        playlists.data.map { it.mapToUiPlaylistBrief() })
                )
                addAll(songs.data.map { it.mapToUiSongBrief() })
            })
        } else {
            RecommendFeedUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RecommendFeedUiState.Loading,
    )
}