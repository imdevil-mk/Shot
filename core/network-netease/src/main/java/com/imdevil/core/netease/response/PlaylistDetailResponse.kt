package com.imdevil.core.netease.response


import com.imdevil.core.netease.bean.PlaylistDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaylistDetailResponse(
    /*
    @Json(name = "code")
    val code: Int,
    @Json(name = "fromUserCount")
    val fromUserCount: String,
    @Json(name = "fromUsers")
    val fromUsers: Any?,
    @Json(name = "relatedVideos")
    val relatedVideos: Any?,
    @Json(name = "resEntrance")
    val resEntrance: Any?,
    @Json(name = "sharedPrivilege")
    val sharedPrivilege: Any?,
    @Json(name = "songFromUsers")
    val songFromUsers: Any?,
    @Json(name = "urls")
    val urls: Any?,
    */

    @Json(name = "playlist")
    val detail: PlaylistDetail,
)