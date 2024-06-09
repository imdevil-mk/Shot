package com.imdevil.shot.core.network.tencent

import com.imdevil.core.tencent.di.NetworkProvideModule
import com.imdevil.core.tencent.retrofit.RetrofitTencentNetwork
import com.imdevil.shot.core.network.common.model.onFail
import com.imdevil.shot.core.network.common.model.onSuccess
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class TencentApiMockTest {
    private val server = MockWebServer()
    private lateinit var subject: RetrofitTencentNetwork
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun prepare() {
        subject = RetrofitTencentNetwork(
            JvmTestCookieManager(),
            server.url("/"),
            NetworkProvideModule.providesTencentMoshi()
        )
    }

    @Test
    fun testGetUserInfo() {
        server.enqueue(MockResponse().setBody(getJson("user_info.json")))

        runBlocking {
            subject.getUserInfo("")
        }
    }

    @Test
    fun testGetUserCreatePlaylist() = runTest {
        server.enqueue(MockResponse().setBody(getJson("user_create_playlist.json")))
        subject.getPlaylistBriefByUser("516959708").onSuccess { playlistBriefs ->
            playlistBriefs.forEach {
                println(it)
            }
            assertEquals(playlistBriefs.size, 11)
            assertEquals(playlistBriefs[1].id, "8992878732")
        }.onFail {
            fail()
        }
    }

    @Test
    fun testGetRecommendPlaylist() = runTest {
        server.enqueue(MockResponse().setBody(getJson("recommend_playlist.json")))
        subject.getRecommendPlaylist().onSuccess { playlistBriefs ->
            playlistBriefs.forEach {
                println(it)
            }
            assertEquals(playlistBriefs.size, 11)
            assertEquals(playlistBriefs[0].id, "8075336924")
        }.onFail {
            fail()
        }
    }

    @Test
    fun testGetNewSongs() = runTest {
        server.enqueue(MockResponse().setBody(getJson("new_songs.json")))
        subject.getNewSongs().onSuccess { newSongs ->
            newSongs.forEach {
                println(it)
            }
            assertEquals(newSongs.size, 76)
            assertEquals(newSongs[0].id, "493371050")
        }.onFail {
            fail()
        }
    }

    @Test
    fun testGetHotKeys() = runTest {
        server.enqueue(MockResponse().setBody(getJson("hot_keys.json")))
        subject.getHotKeys().onSuccess { hotSongs ->
            hotSongs.forEach {
                println(it)
            }
            assertEquals(hotSongs.size, 30)
            assertEquals(hotSongs[0].id, "465608024")
        }.onFail {
            fail()
        }
    }
}
