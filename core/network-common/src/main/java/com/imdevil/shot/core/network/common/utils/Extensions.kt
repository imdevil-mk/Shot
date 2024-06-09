package com.imdevil.shot.core.network.common.utils

import com.imdevil.shot.core.network.common.model.MoshiAdapter
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

fun Array<out Annotation>.findAnnotatedMoshiAdapter(): String {
    this.forEach {
        if (it is MoshiAdapter) {
            return it.adapterName
        }
    }
    return ""
}

fun MutableSet<out Annotation>.findAnnotatedMoshiAdapter(): String {
    this.forEach {
        if (it is MoshiAdapter) {
            return it.adapterName
        }
    }
    return ""
}