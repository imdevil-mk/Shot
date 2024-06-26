package com.imdevil.core.netease.response

import com.imdevil.core.netease.bean.Account
import com.imdevil.core.netease.bean.AccountBinding
import com.imdevil.core.netease.bean.Profile
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
    val bindings: List<AccountBinding>?,
)
