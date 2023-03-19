package com.imdevil.netease.policy.moshi

import com.imdevil.netease.model.ApiResponse
import com.squareup.moshi.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class MoshiApiResponseTypeAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val rawType = type.rawType
        if (rawType != ApiResponse::class.java) return null

        val dataType: Type = (type as? ParameterizedType)?.actualTypeArguments?.firstOrNull() ?: return null

        val dataTypeAdapter = moshi.adapter<Any>(dataType, annotations)

        return ApiResponseTypeAdapter(rawType, dataTypeAdapter)
    }
}

class ApiResponseTypeAdapter(
    private val outerType: Type,
    private val dataTypeAdapter: JsonAdapter<Any>
) : JsonAdapter<ApiResponse<Any>>() {
    override fun fromJson(reader: JsonReader): ApiResponse<Any> {

        var code: Int? = null
        var msg: String? = null
        var data: Any? = null

        val peek = reader.peekJson()
        peek.beginObject()
        while (peek.hasNext()) {
            when (peek.nextName()) {
                "code" -> {
                    code = peek.nextString().toIntOrNull()
                }
                "message" -> {
                    msg = peek.nextString()
                }
                else -> peek.skipValue()
            }
        }

        if (code == 200) {
            data = dataTypeAdapter.fromJson(reader)
        } else {
            reader.skipValue()
        }

        return if (code != 200) {
            ApiResponse.BizError(
                code ?: -1,
                msg ?: "N/A",
            )
        } else if (data != null){
            ApiResponse.Success(data)
        } else {
            ApiResponse.OtherError(IllegalStateException())
        }
    }

    override fun toJson(writer: JsonWriter, value: ApiResponse<Any>?) {
        toJson(value)
    }
}