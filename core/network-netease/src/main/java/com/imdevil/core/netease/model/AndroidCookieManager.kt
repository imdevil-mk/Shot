package com.imdevil.core.netease.model

import java.io.File

class AndroidCookieManager(
    private val file: File,
) : ICookie {

    private val cookies = mutableMapOf<String, String>()

    init {
        recovery()
    }

    override fun saveCookies(loginCookies: Map<String, String>) {
        file.printWriter().use { out ->
            loginCookies.forEach {
                out.println("${it.key}=${it.value}")
            }
        }

        cookies.clear()

        recovery()
    }

    override fun hasCookies() = cookies.isNotEmpty()

    override fun hasCookie(key: String) = cookies.containsKey(key)

    override fun get(key: String): String? = cookies[key]

    override fun add(key: String, value: String) {
        cookies[key] = value
    }

    override fun toEncodeString() = cookies.toEncodeString()

    private fun recovery() {
        file.readLines().forEach { line ->
            line.split("=").also { list ->
                cookies[list[0]] = list[1]
            }
        }
    }
}
