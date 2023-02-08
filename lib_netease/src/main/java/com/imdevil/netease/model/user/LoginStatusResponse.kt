package com.imdevil.netease.model.user


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginStatusResponse(
    @Json(name = "account")
    val account: Account,
    @Json(name = "profile")
    val profile: Profile
)