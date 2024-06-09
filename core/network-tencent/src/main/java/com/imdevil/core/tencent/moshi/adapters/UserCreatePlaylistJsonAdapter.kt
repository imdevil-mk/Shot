package com.imdevil.core.tencent.moshi.adapters

import com.imdevil.core.tencent.bean.PlaylistBrief
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import java.lang.reflect.Type

class UserCreatePlaylistJsonAdapter(
    private val moshi: Moshi,
    private val delegate: JsonAdapter<List<PlaylistBrief>>,
) : JsonAdapter<List<PlaylistBrief>>() {

    override fun fromJson(reader: JsonReader): List<PlaylistBrief> {
        val list = mutableListOf<PlaylistBrief>()

        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            when (name) {
                "data" -> {
                    list.addAll(processUserCreatePlaylist(reader))
                    break
                }

                else -> {
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        return list
    }

    // data -> disslist
    private fun processUserCreatePlaylist(reader: JsonReader): List<PlaylistBrief> {
        val lists = mutableListOf<PlaylistBrief>()
        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            when (name) {
                "disslist" -> {
                    (delegate.fromJson(reader))?.let {
                        lists.addAll(it)
                    }
                    break
                }

                else -> {
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        return lists
    }

    override fun toJson(writer: JsonWriter, value: List<PlaylistBrief>?) {
        writer.beginObject()
        writer.name("playlist_briefs")
        delegate.toJson(writer, value)
        writer.endObject()
    }

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        fun newFactory(): Factory {
            return object : Factory {
                override fun create(
                    type: Type,
                    annotations: MutableSet<out Annotation>,
                    moshi: Moshi,
                ): JsonAdapter<*> {
                    val delegate = moshi.adapter<List<PlaylistBrief>>()
                    return UserCreatePlaylistJsonAdapter(moshi, delegate)
                }
            }
        }
    }
}