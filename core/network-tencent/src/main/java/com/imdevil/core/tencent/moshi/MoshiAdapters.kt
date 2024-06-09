package com.imdevil.core.tencent.moshi

import com.imdevil.core.tencent.moshi.adapters.HotKeysJsonAdapter
import com.imdevil.core.tencent.moshi.adapters.NewSongsJsonAdapter
import com.imdevil.core.tencent.moshi.adapters.RecommendPlaylistJsonAdapter
import com.imdevil.core.tencent.moshi.adapters.UserCreatePlaylistJsonAdapter
import com.imdevil.shot.core.network.common.model.ApiResponse
import com.imdevil.shot.core.network.common.model.MoshiAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.internal.NonNullJsonAdapter
import com.squareup.moshi.internal.NullSafeJsonAdapter
import kotlin.reflect.KType
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

object MoshiAdapters {
    const val USER_CREATE_PLAYLIST_JSON_ADAPTER = "UserCreatePlaylistJsonAdapter"
    const val RECOMMEND_PLAYLIST_JSON_ADAPTER = "RecommendPlaylistJsonAdapter"
    const val HOT_KEYS_JSON_ADAPTER = "HotKeysJsonAdapter"
    const val NEW_SONGS_JSON_ADAPTER = "NewSongsJsonAdapter"

    private val adapters = mapOf<String, JsonAdapter.Factory>(
        USER_CREATE_PLAYLIST_JSON_ADAPTER to UserCreatePlaylistJsonAdapter.newFactory(),
        RECOMMEND_PLAYLIST_JSON_ADAPTER to RecommendPlaylistJsonAdapter.newFactory(),
        HOT_KEYS_JSON_ADAPTER to HotKeysJsonAdapter.newFactory(),
        NEW_SONGS_JSON_ADAPTER to NewSongsJsonAdapter.newFactory(),
    )

    fun get(name: String) = adapters[name]
}

inline fun <reified T> Moshi.findApiResponseAdapter(name: String): JsonAdapter<ApiResponse<T>> {
    return this.adapter<ApiResponse<T>>(MoshiAdapter(name))
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> Moshi.adapter(annotation: Annotation): JsonAdapter<T> {
    val ktype: KType = typeOf<T>()
    val adapter = adapter<T>(ktype.javaType, setOf(annotation))
    return if (adapter is NullSafeJsonAdapter || adapter is NonNullJsonAdapter) {
        adapter
    } else if (ktype.isMarkedNullable) {
        adapter.nullSafe()
    } else {
        adapter.nonNull()
    }
}