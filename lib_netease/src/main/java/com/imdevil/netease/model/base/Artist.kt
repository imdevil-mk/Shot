package com.imdevil.netease.model.base


import com.imdevil.netease.policy.moshi.DefaultIfNull
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