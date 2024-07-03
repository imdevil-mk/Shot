package com.imdevil.core.tencent.moshi

import com.squareup.moshi.JsonReader

fun readJsonObject(reader: JsonReader, builder: ProcessBuilder.() -> Unit) {
    val processBuilder = ProcessBuilder()
    builder(processBuilder)

    reader.beginObject()
    while (reader.hasNext()) {
        val action = processBuilder.processMaps[reader.nextName()]
        if (action == null) {
            reader.skipValue()
        } else {
            action(reader)
        }
    }
    reader.endObject()
}

fun readJsonArray(reader: JsonReader, builder: ProcessBuilder.() -> Unit) {
    val processBuilder = ProcessBuilder()
    builder(processBuilder)

    reader.beginArray()
    while (reader.hasNext()) {
        readJsonObject(reader, builder)
    }
    reader.endArray()
}

class ProcessBuilder(
    val processMaps: MutableMap<String, (actionReader: JsonReader) -> Unit> = mutableMapOf()
) {

    fun readValue(name: String, action: (actionReader: JsonReader) -> Unit) {
        processMaps[name] = action
    }

    fun readChildObject(name: String, builder: ProcessBuilder.() -> Unit) {
        processMaps[name] = { reader: JsonReader -> readJsonObject(reader, builder) }
    }

    fun readChildList(name: String, builder: ProcessBuilder.() -> Unit) {
        processMaps[name] = { reader: JsonReader -> readJsonArray(reader, builder) }
    }
}