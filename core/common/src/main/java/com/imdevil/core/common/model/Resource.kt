package com.imdevil.core.common.model

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val msg: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$msg]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Resource] is of type [Success] & holds non-null [Success.data].
 */
val Resource<*>.succeeded
    get() = this is Resource.Success && data != null

fun <T> Resource<T>.successOr(fallback: T): T {
    return (this as? Resource.Success<T>)?.data ?: fallback
}

val <T> Resource<T>.data: T?
    get() = (this as? Resource.Success)?.data

/**
 * Updates value of [liveData] if [Resource] is of type [Success]
 */
inline fun <reified T> Resource<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is Resource.Success) {
        liveData.value = data
    }
}
/**
 * Updates value of [MutableStateFlow] if [Resource] is of type [Success]
 */
inline fun <reified T> Resource<T>.updateOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is Resource.Success) {
        stateFlow.value = data
    }
}
