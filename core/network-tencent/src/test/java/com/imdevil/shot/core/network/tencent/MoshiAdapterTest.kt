package com.imdevil.shot.core.network.tencent

import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.tencent.di.NetworkProvideModule
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import org.junit.Before
import org.junit.Test

class MoshiAdapterTest {
    private lateinit var moshi: Moshi

    @Before
    fun prepare() {
        moshi = NetworkProvideModule.providesTencentMoshi()
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testGetUserCreatePlaylist() {
        val json = getJson("user_create_playlist.json")

        val adapter = moshi.adapter<List<PlaylistBrief>>()
        val list = adapter.fromJson(json) ?: emptyList()

        list.forEach {
            println(it)
        }

        println(adapter.toJson(list))

        assert(list.isNotEmpty())
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testPlaylistBriefAdapter() {
        val json = getJson("recommend_playlist.json")

        val adapter = moshi.adapter<List<PlaylistBrief>>()
        val list = adapter.fromJson(json) ?: emptyList()

        list.forEach {
            println(it)
        }

        println(adapter.toJson(list))

        assert(list.isNotEmpty())
    }
}