package com.imdevil.netease.policy.cookie

import cn.hutool.core.net.URLEncodeUtil
import cn.hutool.core.text.StrBuilder
import java.net.URLEncoder

interface ICookie {

    // save to file
    fun saveCookies(loginCookies: Map<String, String>)

    fun hasCookies(): Boolean
    fun hasCookie(key: String): Boolean
    fun get(key: String): String?

    // no need save to file, just in runtime for request
    fun add(key: String, value: String)

    fun toEncodeString(): String
}

const val ANONYMOUS_TOKEN =
    "bf8bfeabb1aa84f9c8c3906c04a04fb864322804c83f5d607e91a04eae463c9436bd1a17ec353cf780b396507a3f7464e8a60f4bbc019437993166e004087dd32d1490298caf655c2353e58daa0bc13cc7d5c198250968580b12c1b8817e3f5c807e650dd04abd3fb8130b7ae43fcc5b"

const val OS = "os"
const val APP_VERSION = "appver"
const val MUSIC_U = "MUSIC_U"
const val MUSIC_A = "MUSIC_A"
const val REMEMBER_ME = "__remember_me"
const val NMTID = "NMTID"
const val NTES_NUID = "_ntes_nuid"
const val CSRF = "__csrf"
const val COOKIE = "Cookie"
const val OS_VERSION = "osver"
const val DEVICE_ID = "deviceId"
const val VERSION_CODE = "versioncode"
const val MOBILE_NAME = "mobliename"
const val BUILD_VERSION = "buildver"
const val RESOLUTION = "resolution"
const val CHANNEL = "channel"
const val REQUEST_ID = "requestId"

val INTERESTED_COOKIES = listOf(
    OS_VERSION,
    DEVICE_ID,
    APP_VERSION,
    VERSION_CODE,
    MOBILE_NAME,
    BUILD_VERSION,
    RESOLUTION,
    CSRF,
    OS,
    CHANNEL,
    REQUEST_ID,
    MUSIC_U,
    MUSIC_A,
)


fun Map<String, String>.toEncodeString(): String {
    val str = StrBuilder()
    keys.forEachIndexed { index, name ->
        str.append(URLEncodeUtil.encode(name))
        str.append("=")
        str.append(URLEncodeUtil.encode(this[name]))
        if (index < this.size - 1) {
            str.append("; ")
        }
    }
    return str.toString()
}

fun Map<String, String>.urlEncode(): String {
    val str = StrBuilder()
    keys.forEachIndexed { index, name ->
        str.append(URLEncoder.encode(name, "utf-8"))
        str.append("=")
        str.append(URLEncoder.encode(this[name], "utf-8"))
        if (index < this.size - 1) {
            str.append("&")
        }
    }
    return str.toString()
}