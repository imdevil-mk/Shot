package com.imdevil.shot.core.network.tencent

import com.imdevil.core.common.extensions.print
import com.imdevil.core.tencent.bean.HotKey
import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.tencent.bean.SongBrief
import com.imdevil.core.tencent.bean.UserInfo
import com.imdevil.core.tencent.di.NetworkProvideModule
import com.imdevil.core.tencent.moshi.MoshiAdapters
import com.imdevil.core.tencent.moshi.findApiResponseAdapter
import com.imdevil.shot.core.network.common.model.ApiResponse
import com.imdevil.shot.core.network.common.model.onOtherError
import com.imdevil.shot.core.network.common.model.onSuccess
import com.squareup.moshi.Moshi
import org.junit.Assert
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class MoshiAdapterTest {
    private lateinit var moshi: Moshi

    @Before
    fun prepare() {
        moshi = NetworkProvideModule.providesTencentMoshi()
    }

    @Test
    fun testFailApiResponse() {
        val adapter =
            moshi.findApiResponseAdapter<UserInfo>(MoshiAdapters.USER_INFO_JSON_ADAPTER)

        println(adapter.toJson(ApiResponse.OtherError(IllegalStateException("IllegalStateException"))))
        println(adapter.toJson(ApiResponse.BizError(1001, "error test")))

    }

    @Test
    fun testGetUserInfoApiResponse() {
        val json = getJson("user_info.json")
        val adapter =
            moshi.findApiResponseAdapter<UserInfo>(MoshiAdapters.USER_INFO_JSON_ADAPTER)
        val response =
            adapter.fromJson(json) ?: ApiResponse.OtherError(IllegalStateException("Moshi Fail"))
        response.onSuccess {
            println(adapter.toJson(ApiResponse.Success(it)))
            println(it.name)
            println(it.uin)
            println(it.icons.size)
            Assert.assertEquals(it.name, "imdevil")
        }.onOtherError {
            println(it)
            fail()
        }
    }

    @Test
    fun testGetUserCreatePlaylistApiResponse() {
        val json = getJson("user_create_playlist.json")

        val adapter =
            moshi.findApiResponseAdapter<List<PlaylistBrief>>(MoshiAdapters.USER_CREATE_PLAYLIST_JSON_ADAPTER)
        val response =
            adapter.fromJson(json) ?: ApiResponse.OtherError(IllegalStateException("Moshi Fail"))
        response.onSuccess {
            println(it.size)
            println(it.print())
            Assert.assertEquals(it.size, 11)
            Assert.assertEquals(it[0].id, "0")
        }.onOtherError {
            println(it)
            fail()
        }
    }

    @Test
    fun testGetRecommendPlaylistBriefApiResponse() {
        val json = getJson("recommend_playlist.json")

        val adapter =
            moshi.findApiResponseAdapter<List<PlaylistBrief>>(MoshiAdapters.RECOMMEND_PLAYLIST_JSON_ADAPTER)
        val response =
            adapter.fromJson(json) ?: ApiResponse.OtherError(IllegalStateException("Moshi Fail"))
        response.onSuccess {
            println(it.size)
            println(it.print())
            Assert.assertEquals(it.size, 11)
            Assert.assertEquals(it[0].id, "8075336924")
        }.onOtherError {
            println(it)
            fail()
        }
    }

    @Test
    fun testGetNewSongsApiResponse() {
        val json = getJson("new_songs.json")

        val adapter =
            moshi.findApiResponseAdapter<List<SongBrief>>(MoshiAdapters.NEW_SONGS_JSON_ADAPTER)
        val response =
            adapter.fromJson(json) ?: ApiResponse.OtherError(IllegalStateException("Moshi Fail"))
        response.onSuccess {
            println(it.size)
            println(it.print())
            Assert.assertEquals(it.size, 76)
            Assert.assertEquals(it[0].id, "493371050")
        }.onOtherError {
            println(it)
            fail()
        }
    }

    @Test
    fun testGetHotKeysApiResponse() {
        val json = getJson("hot_keys.json")

        val adapter =
            moshi.findApiResponseAdapter<List<HotKey>>(MoshiAdapters.HOT_KEYS_JSON_ADAPTER)
        val response =
            adapter.fromJson(json) ?: ApiResponse.OtherError(IllegalStateException("Moshi Fail"))
        response.onSuccess {
            println(it.size)
            println(it.print())
            Assert.assertEquals(it.size, 30)
            Assert.assertEquals(it[0].id, "465608024")
        }.onOtherError {
            println(it)
            fail()
        }
    }
}