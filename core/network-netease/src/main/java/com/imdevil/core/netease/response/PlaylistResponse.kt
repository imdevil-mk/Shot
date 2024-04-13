package com.imdevil.core.netease.response


import com.imdevil.core.netease.bean.Playlist
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaylistResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "more")
    val more: Boolean,
    @Json(name = "version")
    val version: String,
    @Json(name = "playlist")
    val playlists: List<Playlist>
)