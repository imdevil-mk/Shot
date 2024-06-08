package com.imdevil.shot.core.network.common.model

sealed class ApiResponse<T> {
    data class Success<T>(
        val data: T,
    ) : ApiResponse<T>()

    data class BizError<T>(
        val code: Int,
        val msg: String,
    ) : ApiResponse<T>()

    data class OtherError<T>(
        val throwable: Throwable,
    ) : ApiResponse<T>()
}

fun <T> ApiResponse<T>.onSuccess(action: (t: T) -> Unit): ApiResponse<T> {
    when (this) {
        is ApiResponse.Success -> {
            action(this.data)
        }

        else -> {
        }
    }
    return this
}

fun <T> ApiResponse<T>.onBizError(action: (code: Int, msg: String) -> Unit): ApiResponse<T> {
    when (this) {
        is ApiResponse.BizError -> {
            action(this.code, this.msg)
        }

        else -> {
        }
    }
    return this
}

fun <T> ApiResponse<T>.onOtherError(action: (throwable: Throwable) -> Unit): ApiResponse<T> {
    when (this) {
        is ApiResponse.OtherError -> {
            action(throwable)
        }

        else -> {
        }
    }
    return this
}

