package com.imdevil.core.tencent.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaylistBrief(
    @Json(name = "tid")
    val id: String,
    @Json(name = "diss_name")
    val title: String,
    @Json(name = "diss_cover")
    val avatar: String,
    @Json(name = "song_cnt")
    val songSize: Int,
    @Json(name = "listen_num")
    val listenCount: Long,
)
