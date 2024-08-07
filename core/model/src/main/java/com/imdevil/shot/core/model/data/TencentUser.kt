package com.imdevil.shot.core.model.data

data class TencentUser(
    val name: String,
    val uin: String,
    val euin: String,
    val avatar: String,
    val follows: Long,
    val followedSingers: Long,
    val followedUsers: Long,
    val fans: Long,
    val friends: Long,
    val visitors: Long,
    val icons: List<String>,
)

val VISITOR = TencentUser(
    "VISITOR",
    "VISITOR",
    "VISITOR",
    "VISITOR", 0, 0, 0, 0, 0, 0, emptyList()
)