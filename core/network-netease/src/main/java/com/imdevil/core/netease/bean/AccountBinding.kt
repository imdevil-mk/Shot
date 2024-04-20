package com.imdevil.core.netease.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountBinding(
    @Json(name = "url")
    val url: String?,
    @Json(name = "userId")
    val userId: Int?,
    @Json(name = "expired")
    val expired: Boolean?,
    @Json(name = "bindingTime")
    val bindingTime: Long?,
    @Json(name = "refreshTime")
    val refreshTime: Int?,
    @Json(name = "tokenJsonStr")
    val tokenJsonStr: String?,
    @Json(name = "expiresIn")
    val expiresIn: Int?,
    @Json(name = "id")
    val id: Long?,
    @Json(name = "type")
    val type: Int?,
)
