package com.imdevil.shot.core.network.tencent

import com.imdevil.core.tencent.model.ICookie
import com.imdevil.shot.core.model.data.Cookie
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import java.io.File

@OptIn(ExperimentalStdlibApi::class)
class JvmTestCookieManager : ICookie {

    private var userCookie: String

    init {
        val cj = getJson("qq_cookie.json")
        val moshi = Moshi.Builder()
            .build()
        val adapter: JsonAdapter<List<Cookie>> = moshi.adapter()
        val cookieList = adapter.fromJson(cj)

        val sb = StringBuilder()
        cookieList?.forEachIndexed { index, cookie ->
            sb.append("${cookie.name}=${cookie.value}")
            if (index < cookieList.size - 1) {
                sb.append(";")
            }
        }
        userCookie = sb.toString()
    }

    override fun getCookie(): String {
        return userCookie
    }
}

fun getRes(name: String): File {
    val loader = ClassLoader.getSystemClassLoader()
    return File(loader.getResource(name).toURI())
}

fun getJson(name: String) = getRes(name).readText()
