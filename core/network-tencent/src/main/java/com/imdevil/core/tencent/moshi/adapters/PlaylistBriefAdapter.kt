package com.imdevil.core.tencent.moshi.adapters

import com.imdevil.core.tencent.bean.PlaylistBrief
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.internal.Util
import java.lang.reflect.Type
import kotlin.String

class PlaylistBriefAdapter(
    moshi: Moshi,
) : JsonAdapter<PlaylistBrief>() {
    private val options: JsonReader.Options = JsonReader.Options.of(
        "tid", "content_id",
        "diss_name", "title",
        "diss_cover", "cover",
        "song_cnt",
        "listen_num",
    )

    private val stringAdapter: JsonAdapter<String> = moshi.adapter(
        String::class.java, emptySet(),
        "id"
    )

    override fun fromJson(reader: JsonReader): PlaylistBrief {
        var id: String? = null
        var title: String? = null
        var cover: String? = null
        var songSize: String? = null
        var listenCount: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            val index = reader.selectName(options)
            when (index) {
                0 -> id =
                    stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("id", "tid", reader)

                1 -> id = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "id",
                    "content_id",
                    reader
                )

                2 -> title = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "title",
                    "diss_name",
                    reader
                )

                3 -> title = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "title",
                    "title",
                    reader
                )

                4 -> cover = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "cover",
                    "diss_cover",
                    reader
                )

                5 -> cover = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "cover",
                    "cover",
                    reader
                )

                6 -> songSize = stringAdapter.fromJson(reader) ?: "0"

                7 -> listenCount = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "listenCount",
                    "listen_num",
                    reader
                )

                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        return PlaylistBrief(
            id = id ?: throw Util.missingProperty("id", "tid", reader),
            title = title ?: throw Util.missingProperty("title", "diss_name", reader),
            cover = cover ?: throw Util.missingProperty("cover", "diss_cover", reader),
            songSize = songSize ?: "0",
            listenCount = listenCount ?: throw Util.missingProperty(
                "listenCount",
                "listen_num",
                reader
            )
        )
    }

    override fun toJson(writer: JsonWriter, brief: PlaylistBrief?): Unit {
        if (brief == null) {
            throw NullPointerException("brief was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("id")
        stringAdapter.toJson(writer, brief.id)
        writer.name("title")
        stringAdapter.toJson(writer, brief.title)
        writer.name("songSize")
        stringAdapter.toJson(writer, brief.songSize)
        writer.name("listenCount")
        stringAdapter.toJson(writer, brief.listenCount)
        writer.name("cover")
        stringAdapter.toJson(writer, brief.cover)
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
                    if (type != PlaylistBrief::class.java) return null
                    return PlaylistBriefAdapter(moshi)
                }
            }
        }
    }
}