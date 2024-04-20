package com.imdevil.core.netease.bean

import com.imdevil.shot.core.network.common.moshi.DefaultIfNull
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@DefaultIfNull
@JsonClass(generateAdapter = true)
data class Artist(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String = "",
)
