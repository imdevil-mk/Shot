package com.imdevil.shot.data.model

sealed interface LoginStatus {
    object Login : LoginStatus
    object Expired : LoginStatus
    object Logout : LoginStatus
}