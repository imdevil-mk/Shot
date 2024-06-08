package com.imdevil.core.tencent.bean

data class PlaylistBrief(
    val id: String,
    val title: String,
    val songSize: String = "",
    val listenCount: String,
    val cover: String,
)
