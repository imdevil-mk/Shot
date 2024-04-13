package com.imdevil.core.netease.response


import com.imdevil.core.netease.bean.Privilege
import com.imdevil.core.netease.bean.Song
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