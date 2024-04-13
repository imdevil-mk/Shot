package com.imdevil.core.netease.bean


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChargeInfo(
    /*
    @Json(name = "chargeMessage")
    val chargeMessage: Any?,
    @Json(name = "chargeUrl")
    val chargeUrl: Any?,
     */

    @Json(name = "rate")
    val rate: Int,
    @Json(name = "chargeType")
    val chargeType: Int,
)