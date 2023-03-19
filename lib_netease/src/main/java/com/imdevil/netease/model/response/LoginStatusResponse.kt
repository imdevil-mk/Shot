package com.imdevil.netease.model.response


import com.imdevil.netease.model.user.Account
import com.imdevil.netease.model.user.Profile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginStatusResponse(
    @Json(name = "account")
    val account: Account,
    @Json(name = "profile")
    val profile: Profile
)