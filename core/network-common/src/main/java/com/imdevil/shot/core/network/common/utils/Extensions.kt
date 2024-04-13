package com.imdevil.shot.core.network.common.utils

import okhttp3.Request
import retrofit2.Invocation

inline fun <reified T : Annotation> Request.getInterestedAnnotation(): T? {
    val method = this.tag(Invocation::class.java)?.method()
    method?.annotations?.forEach {
        if (it is T) {
            return it
        }
    }
    return null
}