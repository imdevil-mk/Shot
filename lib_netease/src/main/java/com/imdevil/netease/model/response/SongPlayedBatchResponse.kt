package com.imdevil.netease.model.response


import com.imdevil.netease.model.base.SongPlayable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongPlayedBatchResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "data")
    val songs: List<SongPlayable>
)