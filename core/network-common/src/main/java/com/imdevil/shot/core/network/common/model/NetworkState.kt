package com.imdevil.shot.core.network.common.model

sealed interface NetworkState {

    /**
     * The feed is still loading.
     */
    data object Loading : NetworkState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data class Complete<T>(
        /**
         * The list of news resources contained in this feed.
         */
        val response: ApiResponse<T>,
    ) : NetworkState
}