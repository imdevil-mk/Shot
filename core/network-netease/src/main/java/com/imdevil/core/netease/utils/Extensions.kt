package com.imdevil.core.netease.utils

import cn.hutool.core.text.StrBuilder
import java.math.BigInteger
import java.security.MessageDigest


fun Map<String, String>.toBody(): String {
    val str = StrBuilder("[")
    keys.forEachIndexed { index, name ->
        str.append("(")
        str.append(name)
        str.append(", ")
        str.append(this[name])
        str.append(")")
        if (index < this.size - 1) {
            str.append(", ")
        }
    }
    str.append("]")
    return str.toString()
}


fun Map<String, String>.print(tag: String) {
    println(tag)
    println("{")
    for ((k, v) in this) {
        println("    $k: $v")
    }
    println("}")
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}