package com.imdevil.core.tencent.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkResponse<T>(
    @Json(name = "code")
    val code: Int,
    @Json(name = "subcode")
    val subCode: Int,
    @Json(name = "message")
    val msg: String,
    @Json(name = "data")
    val data: T
)