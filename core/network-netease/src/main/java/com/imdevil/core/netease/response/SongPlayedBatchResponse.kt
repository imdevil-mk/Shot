package com.imdevil.core.netease.response


import com.imdevil.core.netease.bean.SongPlayable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongPlayedBatchResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "data")
    val songs: List<SongPlayable>
)