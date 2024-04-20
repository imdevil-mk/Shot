package com.imdevil.core.tencent.moshi

import com.imdevil.core.tencent.bean.PlaylistBrief
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class PlaylistBriefListAdapter(
    private val moshi: Moshi,
    private val delegate: JsonAdapter<List<PlaylistBrief>>,
) : JsonAdapter<List<PlaylistBrief>>() {

    private val options: JsonReader.Options = JsonReader.Options.of("disslist")

    override fun fromJson(reader: JsonReader): List<PlaylistBrief> {
        var list = emptyList<PlaylistBrief>()

        reader.beginObject()
        /*while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> {
                    (delegate.fromJson(reader))?.let {
                        list = it
                    }
                }
                -1 -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }*/
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "data" -> {
                    reader.beginObject()
                    while (reader.hasNext()) {
                        when (reader.selectName(options)) {
                            0 -> {
                                (delegate.fromJson(reader))?.let {
                                    list = it
                                }
                            }

                            -1 -> {
                                reader.skipName()
                                reader.skipValue()
                            }
                        }
                    }
                    reader.endObject()
                }

                else -> {
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        return list
    }

    override fun toJson(writer: JsonWriter, value: List<PlaylistBrief>?) {
        writer.beginObject()
        writer.name("data")
        delegate.toJson(writer, value)
        writer.endObject()
    }

    companion object {
        fun newFactory(): Factory {
            return object : Factory {
                override fun create(
                    type: Type,
                    annotations: MutableSet<out Annotation>,
                    moshi: Moshi,
                ): JsonAdapter<*>? {
                    if (annotations.isNotEmpty()) return null
                    if (type !is ParameterizedType) return null
                    if (type.rawType != List::class.java) return null
                    val elementType = type.actualTypeArguments[0]
                    if (elementType != PlaylistBrief::class.java) return null
                    println("PlaylistBriefListAdapter#elementType = $elementType")
                    val delegate = moshi.nextAdapter<List<PlaylistBrief>>(this, type, annotations)
                    return PlaylistBriefListAdapter(moshi, delegate)
                }
            }
        }
    }
}
