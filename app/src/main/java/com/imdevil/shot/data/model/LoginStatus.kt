package com.imdevil.shot.data.model

sealed class LoginStatus<out R> {
    data class LoggedIn(val userProfile: UserProfile) : LoginStatus<UserProfile>()
    data class Error(val msg: String) : LoginStatus<Nothing>()
    object Logging : LoginStatus<Nothing>()
    object Logout : LoginStatus<Nothing>()
    object Expired : LoginStatus<Nothing>()
}