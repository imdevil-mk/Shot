package com.imdevil.core.netease.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Playlist(
    /*
    @Json(name = "adType")
    val adType: Int,
    @Json(name = "anonimous")
    val anonimous: Boolean,
    @Json(name = "artists")
    val artists: Any?,
    @Json(name = "backgroundCoverId")
    val backgroundCoverId: Int,
    @Json(name = "backgroundCoverUrl")
    val backgroundCoverUrl: Any?,
    @Json(name = "cloudTrackCount")
    val cloudTrackCount: Int,
    @Json(name = "commentThreadId")
    val commentThreadId: String,
    @Json(name = "copied")
    val copied: Boolean,
    @Json(name = "coverImgId")
    val coverImgId: Long,
    @Json(name = "coverImgId_str")
    val coverImgIdStr: Any?,
    @Json(name = "description")
    val description: Any?,
    @Json(name = "englishTitle")
    val englishTitle: Any?,
    @Json(name = "highQuality")
    val highQuality: Boolean,
    @Json(name = "newImported")
    val newImported: Boolean,
    @Json(name = "opRecommend")
    val opRecommend: Boolean,
    @Json(name = "ordered")
    val ordered: Boolean,
    @Json(name = "playCount")
    val playCount: Int,
    @Json(name = "privacy")
    val privacy: Int,
    @Json(name = "recommendInfo")
    val recommendInfo: Any?,
    @Json(name = "shareStatus")
    val shareStatus: Any?,
    @Json(name = "sharedUsers")
    val sharedUsers: Any?,
    @Json(name = "specialType")
    val specialType: Int,
    @Json(name = "status")
    val status: Int,
    @Json(name = "subscribed")
    val subscribed: Boolean,
    @Json(name = "subscribedCount")
    val subscribedCount: Int,
    @Json(name = "subscribers")
    val subscribers: List<Any>,
    @Json(name = "tags")
    val tags: List<Any>,
    @Json(name = "titleImage")
    val titleImage: Int,
    @Json(name = "titleImageUrl")
    val titleImageUrl: Any?,
    @Json(name = "totalDuration")
    val totalDuration: Int,
    @Json(name = "tracks")
    val tracks: Any?,
    @Json(name = "updateFrequency")
    val updateFrequency: Any?,
     */

    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "coverImgUrl")
    val coverImgUrl: String,
    @Json(name = "userId")
    val userId: String,
    @Json(name = "creator")
    val creator: Creator,
    @Json(name = "trackCount")
    val trackCount: String,
    @Json(name = "trackNumberUpdateTime")
    val trackNumberUpdateTime: Long,
    @Json(name = "trackUpdateTime")
    val trackUpdateTime: Long,
    @Json(name = "updateTime")
    val updateTime: Long,
    @Json(name = "createTime")
    val createTime: Long,
)
