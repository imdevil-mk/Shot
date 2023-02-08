package com.imdevil.netease.policy.interceptor

import cn.hutool.json.JSONObject
import com.imdevil.netease.crypto.Crypto
import com.imdevil.netease.policy.*
import com.imdevil.netease.policy.annotations.CryptoType
import com.imdevil.netease.policy.annotations.NeteaseCrypto
import com.imdevil.netease.policy.cookie.*
import com.imdevil.netease.utils.print
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.time.LocalDateTime
import kotlin.math.floor

class NeteaseInterceptor(
    private val cookieManager: ICookie,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originRequest = chain.request()


        val headers = mutableMapOf(
            "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.30 Safari/537.36",
            "Content-Type" to "application/x-www-form-urlencoded",
            "Referer" to "https://music.163.com"
        )

        if (cookieManager.hasCookies()) {
            cookieManager.add(REMEMBER_ME, "true")
            cookieManager.add(NMTID, "7148eea966782cd04072c1c4034b1ed8")
            cookieManager.add(NTES_NUID, "d0f4cadf1fab71444f755a6d38d1f445")
            if (!cookieManager.hasCookie(MUSIC_A) && !cookieManager.hasCookie(MUSIC_U)) {
                cookieManager.add(MUSIC_A, ANONYMOUS_TOKEN)
            }
            headers[COOKIE] = cookieManager.toEncodeString()
        } else {
            headers[COOKIE] = "__remember_me=true; NMTID=xxx"
        }
        if (!cookieManager.hasCookie(OS)) {
            cookieManager.add(OS, "android")
        }
        if (!cookieManager.hasCookie(APP_VERSION)) {
            cookieManager.add(APP_VERSION, "2.9.7")
        }
        headers.print("origin headers =")

        val params = originRequest.url.queryParameterNames
        val paramsMap = mutableMapOf<String, String>()
        params.forEach { name ->
            paramsMap[name] = originRequest.url.queryParameter(name)!!
        }
        paramsMap.print("origin params")

        val result = when (chain.request().getInterestedAnnotation<NeteaseCrypto>()?.type
            ?: CryptoType.WEAPI) {
            CryptoType.WEAPI -> {
                paramsMap["csrf_token"] = cookieManager.get(CSRF) ?: ""
                paramsMap.print("we_api params")
                Crypto.weApi(Json.encodeToString(paramsMap))
            }
            CryptoType.EAPI -> {
                val cryptoHeader = mutableMapOf(
                    OS_VERSION to (cookieManager.get(OS_VERSION) ?: ""),
                    DEVICE_ID to (cookieManager.get(DEVICE_ID) ?: ""),
                    APP_VERSION to (cookieManager.get(APP_VERSION) ?: "8.7.01"),
                    VERSION_CODE to (cookieManager.get(VERSION_CODE) ?: "140"),
                    MOBILE_NAME to (cookieManager.get(MOBILE_NAME) ?: ""),
                    BUILD_VERSION to LocalDateTime.now().toString().substring(0, 10),
                    RESOLUTION to (cookieManager.get(RESOLUTION) ?: "1920x1080"),
                    CSRF to (cookieManager.get(CSRF) ?: ""),
                    OS to (cookieManager.get(OS) ?: "android"),
                    CHANNEL to (cookieManager.get(CHANNEL) ?: ""),
                    REQUEST_ID to "${LocalDateTime.now()}_${
                        floor(Math.random() * 1000).toString().padStart(4, '0')
                    }"
                )
                if (cookieManager.hasCookie(MUSIC_U)) {
                    cryptoHeader[MUSIC_U] = cookieManager.get(MUSIC_U) ?: ""
                }
                if (cookieManager.hasCookie(MUSIC_A)) {
                    cryptoHeader[MUSIC_A] = cookieManager.get(MUSIC_A) ?: ""
                }
                headers[COOKIE] = cryptoHeader.toEncodeString()
                headers.print("EAPI headers =")

                val jsonObject = JSONObject(paramsMap)
                jsonObject.put("header", cryptoHeader)
                println("E_API params $jsonObject")

                Crypto.eApi(
                    originRequest.url.encodedPath.replace("/eapi", "/api"),
                    jsonObject.toString()
                )
            }
        }
        result.print("final query =")

        val body = result.urlEncode()

        val newRequest = Request.Builder()
            .headers(headers.toHeaders())
            .url(originRequest.url)
            .post(body.toRequestBody())
            .build()

        return chain.proceed(newRequest)
    }
}