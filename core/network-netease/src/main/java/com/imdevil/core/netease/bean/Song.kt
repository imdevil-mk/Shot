package com.imdevil.core.netease.bean


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Song(
    /*
    @Json(name = "a")
    val a: Any?,
    @Json(name = "awardTags")
    val awardTags: Any?,
    @Json(name = "cd")
    val cd: String,
    @Json(name = "cf")
    val cf: String,
    @Json(name = "cp")
    val cp: Int,
    @Json(name = "crbt")
    val crbt: Any?,
    @Json(name = "djId")
    val djId: Int,
    @Json(name = "dt")
    val dt: Int,
    @Json(name = "entertainmentTags")
    val entertainmentTags: Any?,
    @Json(name = "fee")
    val fee: Int,
    @Json(name = "ftype")
    val ftype: Int,
    @Json(name = "hr")
    val hr: Any?,
    @Json(name = "mark")
    val mark: Int,
    @Json(name = "mst")
    val mst: Int,
    @Json(name = "mv")
    val mv: Int,
    @Json(name = "no")
    val no: Int,
    @Json(name = "noCopyrightRcmd")
    val noCopyrightRcmd: Any?,
    @Json(name = "originCoverType")
    val originCoverType: Int,
    @Json(name = "originSongSimpleData")
    val originSongSimpleData: Any?,
    @Json(name = "pop")
    val pop: Double,
    @Json(name = "pst")
    val pst: Int,
    @Json(name = "resourceState")
    val resourceState: Boolean,
    @Json(name = "rt")
    val rt: String,
    @Json(name = "rtUrl")
    val rtUrl: Any?,
    @Json(name = "rtUrls")
    val rtUrls: List<Any>,
    @Json(name = "rtype")
    val rtype: Int,
    @Json(name = "rurl")
    val rurl: Any?,
    @Json(name = "s_id")
    val sId: Int,
    @Json(name = "single")
    val single: Int,
    @Json(name = "songJumpInfo")
    val songJumpInfo: Any?,
    @Json(name = "st")
    val st: Int,
    @Json(name = "t")
    val t: Int,
    @Json(name = "tagPicList")
    val tagPicList: Any?,
    @Json(name = "v")
    val v: Int,
    @Json(name = "version")
    val version: Int,
    */

    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "alia")
    val alias: List<String>,
    @Json(name = "publishTime")
    val publishTime: Long,
    @Json(name = "al")
    val album: Album,
    @Json(name = "ar")
    val artists: List<Artist>,
    @Json(name = "h")
    val hQuality: Quality?,
    @Json(name = "l")
    val lQuality: Quality?,
    @Json(name = "m")
    val mQuality: Quality?,
    @Json(name = "sq")
    val sqQuality: Quality?,
    @Json(name = "copyright")
    val copyright: Int,
)