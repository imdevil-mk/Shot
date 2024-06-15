package com.imdevil.feature.tencent.feature.recommend

import com.imdevil.core.ui.recyclerview.IRecyclerData

/**
 * A sealed hierarchy describing the state of the feed of news resources.
 */
sealed interface RecommendFeedUiState {
    /**
     * The feed is still loading.
     */
    data object Loading : RecommendFeedUiState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data class Success(
        /**
         * The list of news resources contained in this feed.
         */
        val feeds: List<IRecyclerData>,
    ) : RecommendFeedUiState
}