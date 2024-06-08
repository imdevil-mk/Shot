package com.imdevil.core.common.extensions

fun <T> List<T>.print(): String {
    val sb = StringBuilder()
    this.forEachIndexed { index, t ->
        sb.append(t)
        if (index < size - 1) {
            sb.append("\n")
        }
    }
    return sb.toString()
}
