package com.imdevil.core.netease.bean


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Quality(
    @Json(name = "br")
    val br: Int,
    @Json(name = "fid")
    val fid: Int,
    @Json(name = "size")
    val size: Int,
    @Json(name = "vd")
    val vd: Double
)