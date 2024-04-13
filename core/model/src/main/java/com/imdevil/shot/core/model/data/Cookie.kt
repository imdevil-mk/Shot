package com.imdevil.shot.core.model.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cookie(
    @Json(name = "name")
    val name: String?,
    @Json(name = "value")
    val value: String?,
    @Json(name = "domain")
    val domain: String?,
    @Json(name = "expirationDate")
    val expirationDate: String? = "",
    @Json(name = "path")
    val path: String?,

    @Json(name = "hostOnly")
    val hostOnly: Boolean = false,
    @Json(name = "httpOnly")
    val httpOnly: Boolean = false,
    @Json(name = "sameSite")
    val sameSite: String? = "",
    @Json(name = "secure")
    val secure: Boolean = false,
    @Json(name = "session")
    val session: Boolean = false,
    @Json(name = "storeId")
    val storeId: String? = "",
) {
    override fun toString(): String {
        return "${name}=${value} Domain=${domain} Path=${path} Expires=${expirationDate}"
    }
}
