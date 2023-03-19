package com.imdevil.netease.model.base


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Privilege(
    /*
    @Json(name = "cp")
    val cp: Int,
    @Json(name = "cs")
    val cs: Boolean,
    @Json(name = "dl")
    val dl: Int,
    @Json(name = "dlLevel")
    val dlLevel: String,
    @Json(name = "downloadMaxBrLevel")
    val downloadMaxBrLevel: String,
    @Json(name = "downloadMaxbr")
    val downloadMaxbr: Int,
    @Json(name = "flLevel")
    val flLevel: String,
    @Json(name = "flag")
    val flag: Int,
    @Json(name = "paidBigBang")
    val paidBigBang: Boolean,
    @Json(name = "plLevel")
    val plLevel: String,
    @Json(name = "playMaxBrLevel")
    val playMaxBrLevel: String,
    @Json(name = "playMaxbr")
    val playMaxbr: Int,
    @Json(name = "preSell")
    val preSell: Boolean,
    @Json(name = "realPayed")
    val realPayed: Int,
    @Json(name = "rscl")
    val rscl: Any?,
    @Json(name = "sp")
    val sp: Int,
    @Json(name = "st")
    val st: Int,
    @Json(name = "subp")
    val subp: Int,
    @Json(name = "toast")
    val toast: Boolean,
    */

    @Json(name = "id")
    val id: Int,
    @Json(name = "fee")
    val fee: Int,
    @Json(name = "payed")
    val payed: Int,
    @Json(name = "fl")
    val fl: Int,
    @Json(name = "pl")
    val pl: Int,
    @Json(name = "maxBrLevel")
    val maxBrLevel: String,
    @Json(name = "maxbr")
    val maxbr: Int,
    @Json(name = "freeTrialPrivilege")
    val freeTrialPrivilege: TrialPrivilege,
    @Json(name = "pc")
    val pc: PCInfo? = null,
    @Json(name = "chargeInfoList")
    val chargeInfoList: List<ChargeInfo>? = null,
)