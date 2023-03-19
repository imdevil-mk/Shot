package com.imdevil.netease.model.base


import com.imdevil.netease.policy.moshi.DefaultIfNull
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@DefaultIfNull
@JsonClass(generateAdapter = true)
data class Album(
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "pic")
    val pic: String = "",
    @Json(name = "picUrl")
    val picUrl: String = "",
)