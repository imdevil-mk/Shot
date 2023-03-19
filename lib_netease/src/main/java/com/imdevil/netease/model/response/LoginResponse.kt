package com.imdevil.netease.model.response


import com.imdevil.netease.model.user.Account
import com.imdevil.netease.model.user.AccountBinding
import com.imdevil.netease.model.user.Profile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "loginType")
    val loginType: Int,
    @Json(name = "account")
    val account: Account,
    @Json(name = "token")
    val token: String?,
    @Json(name = "profile")
    val profile: Profile,
    @Json(name = "bindings")
    val bindings: List<AccountBinding>?
)