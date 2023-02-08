package com.imdevil.netease.model.user


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Account(
    @Json(name = "salt")
    val salt: String?,
    @Json(name = "vipType")
    val vipType: Int?,
    @Json(name = "userName")
    val userName: String?,
    @Json(name = "type")
    val type: Int?,
    @Json(name = "paidFee")
    val paidFee: Boolean?,
    @Json(name = "ban")
    val ban: Int?,
    @Json(name = "anonimousUser")
    val anonymousUser: Boolean?,
    @Json(name = "createTime")
    val createTime: Long?,
    @Json(name = "tokenVersion")
    val tokenVersion: Int?,
    @Json(name = "id")
    val id: String,
    @Json(name = "whitelistAuthority")
    val whitelistAuthority: Int?,
    @Json(name = "baoyueVersion")
    val baoyueVersion: Int?,
    @Json(name = "viptypeVersion")
    val vipTypeVersion: Long?,
    @Json(name = "donateVersion")
    val donateVersion: Int?,
    @Json(name = "status")
    val status: Int?,
    @Json(name = "uninitialized")
    val uninitialized: Boolean?
)