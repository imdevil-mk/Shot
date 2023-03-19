package com.imdevil.netease.model.response


import com.imdevil.netease.model.base.Playlist
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