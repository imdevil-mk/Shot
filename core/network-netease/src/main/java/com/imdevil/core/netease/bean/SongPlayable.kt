package com.imdevil.core.netease.bean


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongPlayable(
    /*
    @Json(name = "canExtend")
    val canExtend: Boolean,
    @Json(name = "code")
    val code: Int,
    @Json(name = "effectTypes")
    val effectTypes: Any?,
    @Json(name = "expi")
    val expi: Int,
    @Json(name = "freeTrialInfo")
    val freeTrialInfo: Any?,
    @Json(name = "gain")
    val gain: Double,
    @Json(name = "md5")
    val md5: String,
    @Json(name = "payed")
    val payed: Int,
    @Json(name = "peak")
    val peak: Double,
    @Json(name = "podcastCtrp")
    val podcastCtrp: Any?,
    @Json(name = "rightSource")
    val rightSource: Int,
    @Json(name = "uf")
    val uf: Any?,
    @Json(name = "urlSource")
    val urlSource: Int,
    */

    @Json(name = "id")
    val id: Int,
    @Json(name = "size")
    val size: Int,
    @Json(name = "time")
    val time: Int,
    @Json(name = "br")
    val br: Int,
    @Json(name = "url")
    val url: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "level")
    val level: String,
    @Json(name = "encodeType")
    val encodeType: String,
    @Json(name = "fee")
    val fee: Int,
    @Json(name = "flag")
    val flag: Int,
    @Json(name = "freeTrialPrivilege")
    val freeTrialPrivilege: TrialPrivilege,
    @Json(name = "freeTimeTrialPrivilege")
    val freeTimeTrialPrivilege: TrialPrivilege,
)