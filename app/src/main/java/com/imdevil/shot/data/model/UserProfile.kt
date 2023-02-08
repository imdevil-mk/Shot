package com.imdevil.shot.data.model

import com.imdevil.netease.model.user.LoginResponse
import com.imdevil.netease.model.user.LoginStatusResponse

data class UserProfile(
    val uid: String,
    val name: String,
    val avatar: String,
)

fun LoginStatusResponse.toUserProfile(uid: String): UserProfile {
    return UserProfile(
        uid,
        this.profile.nickname,
        this.profile.avatarUrl,
    )
}

fun LoginResponse.toUserProfile(): UserProfile {
    return UserProfile(
        this.account.id,
        this.profile.nickname,
        this.profile.avatarUrl,
    )
}
