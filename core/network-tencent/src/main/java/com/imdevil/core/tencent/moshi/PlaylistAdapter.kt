package com.imdevil.core.tencent.moshi

import com.imdevil.core.tencent.bean.Playlist
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

class PlaylistAdapter(
    private val moshi: Moshi,
    private val delegate: JsonAdapter<Playlist>
) : JsonAdapter<Playlist>() {

    private val options: JsonReader.Options = JsonReader.Options.of("cdlist")

    override fun fromJson(reader: JsonReader): Playlist? {
        val list = mutableListOf<Playlist>()

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        delegate.fromJson(reader)?.let { list.add(it) }
                    }
                    reader.endArray()
                }

                -1 -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        return if (list.isEmpty()) null else list[0]
    }

    override fun toJson(writer: JsonWriter, value: Playlist?) {
        TODO("Not yet implemented")
    }

    companion object {
        fun newFactory(): Factory {
            return object : Factory {
                override fun create(
                    type: Type,
                    annotations: MutableSet<out Annotation>,
                    moshi: Moshi
                ): JsonAdapter<*>? {
                    if (annotations.isNotEmpty()) return null
                    if (type != Playlist::class.java) return null
                    println("PlaylistAdapter#hit")
                    val delegate = moshi.nextAdapter<Playlist>(this, type, annotations)
                    println("PlaylistAdapter delegate = $delegate")
                    return PlaylistAdapter(moshi, delegate)
                }
            }
        }
    }
}