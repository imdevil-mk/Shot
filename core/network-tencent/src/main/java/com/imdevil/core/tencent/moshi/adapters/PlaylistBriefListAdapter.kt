package com.imdevil.core.tencent.moshi.adapters

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

                "recommend_playlist" -> {
                    list.addAll(processRecommendPlaylist(reader))
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

    // recommend_playlist -> data -> v_hot
    private fun processRecommendPlaylist(reader: JsonReader): List<PlaylistBrief> {
        val lists = mutableListOf<PlaylistBrief>()
        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            when (name) {
                "data" -> {
                    reader.beginObject()
                    while (reader.hasNext()) {
                        val name1 = reader.nextName()
                        when (name1) {
                            "v_hot" -> {
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
