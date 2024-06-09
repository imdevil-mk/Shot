package com.imdevil.core.tencent.moshi.adapters

import com.imdevil.core.tencent.moshi.MoshiAdapters
import com.imdevil.shot.core.network.common.model.ApiResponse
import com.imdevil.shot.core.network.common.utils.findAnnotatedMoshiAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseAdapter<T>(
    private val moshi: Moshi,
    private val delegate: JsonAdapter<T>,
) : JsonAdapter<ApiResponse<T>>() {

    private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java)
    private val intAdapter: JsonAdapter<Int> = moshi.adapter(Int::class.java)

    override fun fromJson(reader: JsonReader): ApiResponse<T> {
        var code = -1
        var subCode = 0
        var msg = ""

        val peeked = reader.peekJson()
        peeked.beginObject()
        while (peeked.hasNext()) {
            when (peeked.nextName()) {
                "code" -> code = peeked.nextInt()
                "subcode" -> subCode = peeked.nextInt()
                "message" -> msg = peeked.nextString()
                else -> {
                    peeked.skipValue()
                }
            }
        }
        peeked.endObject()

        val apiResponse = if (code == 0) {
            val dataResponse: T = delegate.fromJson(reader)!!
            ApiResponse.Success(dataResponse)
        } else {
            ApiResponse.BizError(code, msg)
        }
        return apiResponse
    }

    override fun toJson(writer: JsonWriter, value: ApiResponse<T>?) {
    }

    companion object {
        fun newFactory(): Factory {
            return object : Factory {
                override fun create(
                    type: Type,
                    annotations: MutableSet<out Annotation>,
                    moshi: Moshi,
                ): JsonAdapter<*>? {
                    val wantedAdapter = annotations.findAnnotatedMoshiAdapter()
                    val wantedFactory = MoshiAdapters.get(wantedAdapter)
                    if (wantedFactory != null) {
                        println("ApiResponseAdapter#AnnotatedMoshiAdapter = $wantedAdapter")
                        val delegate =
                            wantedFactory.create(type, annotations, moshi) as JsonAdapter<*>
                        return ApiResponseAdapter(moshi, delegate)
                    }
                    if (annotations.isNotEmpty()) return null
                    if (type !is ParameterizedType) return null
                    if (type.rawType != ApiResponse::class.java) return null

                    val elementType = type.actualTypeArguments[0]
                    println("ApiResponseAdapter#elementType = $elementType")

                    val dataType = if (elementType == List::class.java) {
                        Types.newParameterizedType(List::class.java, type.actualTypeArguments[1])
                    } else {
                        elementType
                    }
                    val delegate: JsonAdapter<Any> = moshi.adapter(dataType)

                    return ApiResponseAdapter(moshi, delegate)
                }
            }
        }
    }
}
