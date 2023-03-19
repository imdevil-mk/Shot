package com.imdevil.netease.model.response


import com.imdevil.netease.model.base.Privilege
import com.imdevil.netease.model.base.Song
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongDetailBatchResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "privileges")
    val privileges: List<Privilege>,
    @Json(name = "songs")
    val songs: List<Song>
)