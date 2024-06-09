package com.imdevil.core.tencent.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotKey(
    @Json(name = "direct_id")
    val id: String,
    @Json(name = "title")
    val name: String,
    @Json(name = "cover_pic_url")
    val cover: String,
)