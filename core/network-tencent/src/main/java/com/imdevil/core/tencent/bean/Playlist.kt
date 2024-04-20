package com.imdevil.core.tencent.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.util.Date

@JsonClass(generateAdapter = true)
data class Playlist(
    @Json(name = "dissid")
    val id: String,
    @Json(name = "disstid")
    val tid: String,
    @Json(name = "dissname")
    val name: String,
    @Json(name = "dir_show")
    val dirShow: Int,
    @Json(name = "logo")
    val avatar: String,
    @Json(name = "songnum")
    val songCount: Int,
    @Json(name = "ctime")
    val createTime: Long,
    @Json(name = "nickname")
    val creatorName: String,
    @Json(name = "headurl")
    val creatorAvatar: String,
    @Json(name = "songlist")
    val songBriefList: List<SongBrief>,
) {
    public fun toSingleLine(): String {
        val humanCreateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(createTime))
        return "$name ${creatorName}创建于$humanCreateTime，拥有${songCount}首歌"
    }
}
