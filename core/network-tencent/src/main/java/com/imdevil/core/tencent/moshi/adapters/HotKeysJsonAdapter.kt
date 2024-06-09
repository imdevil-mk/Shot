package com.imdevil.core.tencent.moshi.adapters

import com.imdevil.core.tencent.bean.HotKey
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonAdapter.Factory
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

class HotKeysJsonAdapter(
    private val moshi: Moshi,
    private val delegate: JsonAdapter<List<HotKey>>,
) : JsonAdapter<List<HotKey>>() {

    override fun fromJson(reader: JsonReader): List<HotKey> {
        val list = mutableListOf<HotKey>()

        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            when (name) {
                "hot_songs" -> {
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

    // hot_songs -> data -> vec_hotkey
    private fun processHotSongs(reader: JsonReader): List<HotKey> {
        val lists = mutableListOf<HotKey>()
        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            when (name) {
                "data" -> {
                    reader.beginObject()
                    while (reader.hasNext()) {
                        val name1 = reader.nextName()
                        when (name1) {
                            "vec_hotkey" -> {
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

    override fun toJson(writer: JsonWriter, value: List<HotKey>?) {
        writer.beginObject()
        writer.name("hot_keys")
        delegate.toJson(writer, value)
        writer.endObject()
    }

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        fun newFactory(): Factory {
            return Factory { type, annotations, moshi ->
                val delegate = moshi.adapter<List<HotKey>>()
                HotKeysJsonAdapter(moshi, delegate)
            }
        }
    }
}