package com.imdevil.netease

import com.imdevil.netease.model.ApiResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import java.io.File

fun getRes(name: String): File {
    val loader = ClassLoader.getSystemClassLoader()
    return File(loader.getResource(name).toURI())
}

fun getFileOrCreate(name: String): File {
    val file = File("src/test/resources/$name")
    if (!file.exists()) {
        file.createNewFile()
    }
    return file
}

fun getJson(name: String) = getRes(name).readText()


fun <T> ApiResponse<T>.log(action: T.() -> Unit) {
    when (this) {
        is ApiResponse.BizError -> println(this.msg)
        is ApiResponse.OtherError -> println(this.throwable)
        is ApiResponse.Success -> this.data.action()
    }
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ApiResponse<T>.logAndSave(moshi: Moshi, name: String) {
    when (this) {
        is ApiResponse.BizError -> println(this.msg)
        is ApiResponse.OtherError -> println(this.throwable)
        is ApiResponse.Success -> {
            println(this.data)

            val adapter = moshi.adapter<T>()
            adapter.toJson(this.data).saveAsJson(name)
        }
    }
}

fun String.saveAsJson(fileName: String) {
    val jsonFile = getFileOrCreate(fileName)
    jsonFile.writeText(this)
}