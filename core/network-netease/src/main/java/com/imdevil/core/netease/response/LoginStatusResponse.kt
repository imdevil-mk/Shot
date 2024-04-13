package com.imdevil.core.netease.response


import com.imdevil.core.netease.bean.Account
import com.imdevil.core.netease.bean.Profile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginStatusResponse(
    @Json(name = "account")
    val account: Account,
    @Json(name = "profile")
    val profile: Profile
)