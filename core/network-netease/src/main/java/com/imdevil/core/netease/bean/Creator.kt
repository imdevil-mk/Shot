package com.imdevil.core.netease.bean


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Creator(
    /*
    @Json(name = "accountStatus")
    val accountStatus: Int,
    @Json(name = "anchor")
    val anchor: Boolean,
    @Json(name = "authStatus")
    val authStatus: Int,
    @Json(name = "authenticationTypes")
    val authenticationTypes: Int,
    @Json(name = "authority")
    val authority: Int,
    @Json(name = "avatarDetail")
    val avatarDetail: Any?,
    @Json(name = "avatarImgId")
    val avatarImgId: Long,
    @Json(name = "avatarImgIdStr")
    val avatarImgIdStr: String,
    @Json(name = "avatarImgId_str")
    val avatarImgIdstr: String,
    @Json(name = "backgroundImgId")
    val backgroundImgId: Long,
    @Json(name = "backgroundImgIdStr")
    val backgroundImgIdStr: String,
    @Json(name = "defaultAvatar")
    val defaultAvatar: Boolean,
    @Json(name = "djStatus")
    val djStatus: Int,
    @Json(name = "expertTags")
    val expertTags: Any?,
    @Json(name = "experts")
    val experts: Any?,
    @Json(name = "followed")
    val followed: Boolean,
    @Json(name = "mutual")
    val mutual: Boolean,
    @Json(name = "remarkName")
    val remarkName: Any?,
    @Json(name = "userType")
    val userType: Int,
    @Json(name = "vipType")
    val vipType: Int,
    */

    @Json(name = "userId")
    val userId: String,
    @Json(name = "nickname")
    val nickname: String,
    @Json(name = "signature")
    val signature: String,
    @Json(name = "avatarUrl")
    val avatarUrl: String,
    @Json(name = "backgroundUrl")
    val backgroundUrl: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "province")
    val province: String,
    @Json(name = "city")
    val city: String,
    @Json(name = "birthday")
    val birthday: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "detailDescription")
    val detailDescription: String,
)