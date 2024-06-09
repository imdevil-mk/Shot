package com.imdevil.core.tencent.moshi.adapters

import com.imdevil.core.tencent.bean.SongBrief
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonAdapter.Factory
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

class NewSongsJsonAdapter(
    private val moshi: Moshi,
    private val delegate: JsonAdapter<List<SongBrief>>,
) : JsonAdapter<List<SongBrief>>() {

    override fun fromJson(reader: JsonReader): List<SongBrief> {
        val list = mutableListOf<SongBrief>()

        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            when (name) {
                "new_songs" -> {
                    list.addAll(processHotSongs(reader))
                }

                else -> {
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        return list
    }

    // hot_songs -> data -> vec_SongBrief
    private fun processHotSongs(reader: JsonReader): List<SongBrief> {
        val lists = mutableListOf<SongBrief>()
        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            when (name) {
                "data" -> {
                    reader.beginObject()
                    while (reader.hasNext()) {
                        val name1 = reader.nextName()
                        when (name1) {
                            "songlist" -> {
                                (delegate.fromJson(reader))?.let {
                                    lists.addAll(it)
                                }
                            }

                            else -> {
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

        return lists
    }

    override fun toJson(writer: JsonWriter, value: List<SongBrief>?) {
        writer.beginObject()
        writer.name("hot_keys")
        delegate.toJson(writer, value)
        writer.endObject()
    }

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        fun newFactory(): Factory {
            return Factory { type, annotations, moshi ->
                val delegate = moshi.adapter<List<SongBrief>>()
                NewSongsJsonAdapter(moshi, delegate)
            }
        }
    }
}