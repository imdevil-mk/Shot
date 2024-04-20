package com.imdevil.core.tencent.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongBrief(
    @Json(name = "songid")
    val id: String,
    @Json(name = "songmid")
    val mid: String,
    @Json(name = "songname")
    val name: String,
    @Json(name = "strMediaMid")
    val mediaId: String,
    @Json(name = "singer")
    val singers: List<SingerBrief>,
    @Json(name = "albumid")
    val albumId: String,
    @Json(name = "albummid")
    val albumMid: String,
    @Json(name = "albumname")
    val albumName: String,
)
