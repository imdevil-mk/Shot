package com.imdevil.core.tencent.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingerBrief(
    @Json(name = "id")
    val id: String,
    @Json(name = "mid")
    val mid: String,
    @Json(name = "name")
    val name: String,
)
