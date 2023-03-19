package com.imdevil.netease.model.base


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrialPrivilege(
    /*
    @Json(name = "cannotListenReason")
    val cannotListenReason: Any?,
    @Json(name = "listenType")
    val listenType: Any?,
    */

    @Json(name = "resConsumable")
    val resConsumable: Boolean,
    @Json(name = "userConsumable")
    val userConsumable: Boolean
)