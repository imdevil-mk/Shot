package com.imdevil.core.tencent.bean

data class UserInfo(
    val name: String,
    val uin: String,
    val avatar: String,
    val follows: Long,
    val followedSingers: Long,
    val followedUsers: Long,
    val fans: Long,
    val friends: Long,
    val visitors: Long,
    val icons: List<String>,
)