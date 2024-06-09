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

fun <T> ApiResponse<T>.onSuccess(success: (t: T) -> Unit): ApiResponse<T> {
    when (this) {
        is ApiResponse.Success -> {
            success(this.data)
        }

        else -> {
        }
    }
    return this
}

fun <T> ApiResponse<T>.onFail(fail: () -> Unit): ApiResponse<T> {
    when (this) {
        is ApiResponse.BizError -> {
            fail()
        }

        is ApiResponse.OtherError -> {
            fail()
        }

        else -> {
        }
    }
    return this
}

fun <T> ApiResponse<T>.onBizError(error: (code: Int, msg: String) -> Unit): ApiResponse<T> {
    when (this) {
        is ApiResponse.BizError -> {
            error(this.code, this.msg)
        }

        else -> {
        }
    }
    return this
}

fun <T> ApiResponse<T>.onOtherError(error: (throwable: Throwable) -> Unit): ApiResponse<T> {
    when (this) {
        is ApiResponse.OtherError -> {
            error(throwable)
        }

        else -> {
        }
    }
    return this
}

