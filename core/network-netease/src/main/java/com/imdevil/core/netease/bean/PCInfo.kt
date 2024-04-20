package com.imdevil.core.netease.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PCInfo(
    @Json(name = "addTime")
    val addTime: Long,
    @Json(name = "album")
    val album: String,
    @Json(name = "artist")
    val artist: String,
    @Json(name = "bitrate")
    val bitrate: Int,
    @Json(name = "convertLyric")
    val convertLyric: Int,
    @Json(name = "cover")
    val cover: Long,
    @Json(name = "cue")
    val cue: Int,
    @Json(name = "fileName")
    val fileName: String,
    @Json(name = "fileSize")
    val fileSize: Int,
    @Json(name = "id")
    val id: Long,
    @Json(name = "lrcType")
    val lrcType: String,
    @Json(name = "lyric")
    val lyric: Int,
    @Json(name = "md5")
    val md5: String,
    @Json(name = "originalAudioSongId")
    val originalAudioSongId: Int,
    @Json(name = "song")
    val song: String,
    @Json(name = "songDfsId")
    val songDfsId: Long,
    @Json(name = "songId")
    val songId: Int,
    @Json(name = "status")
    val status: Int,
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "version")
    val version: Int,
)
