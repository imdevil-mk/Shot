package com.imdevil.netease.policy.cookie

import java.io.File
import kotlin.jvm.optionals.getOrNull

class AndroidCookieManager(
    private val file: File,
    private val userFile: File,
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

    override fun saveUserProfile(profile: String) {
        userFile.printWriter().use { out ->
            out.println(profile)
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun getUserProfile(): String? {
        val profile = userFile.readLines().stream().reduce { t, u ->
            t + u
        }.getOrNull()
        return profile
    }

    private fun recovery() {
        file.readLines().forEach { line ->
            line.split("=").also { list ->
                cookies[list[0]] = list[1]
            }
        }
    }
}