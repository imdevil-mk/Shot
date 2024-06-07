package com.imdevil.shot.core.network.tencent

import com.imdevil.core.tencent.retrofit.RetrofitTencentNetwork
import com.imdevil.shot.core.network.common.model.ApiResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TencentApiMockTest {
    private val server = MockWebServer()
    private lateinit var subject: RetrofitTencentNetwork

    @Before
    fun prepare() {
        subject = RetrofitTencentNetwork(JvmTestCookieManager(), server.url("/"))
    }

    @Test
    fun testGetUserInfo() {
        server.enqueue(MockResponse().setBody(getJson("user_info.json")))

        runBlocking {
            subject.getUserInfo("")
        }
    }

    @Test
    fun testGetUserCreatePlaylist() {
        server.enqueue(MockResponse().setBody(getJson("user_create_playlist.json")))

        runBlocking {
            when (val playlistBriefs = subject.getPlaylistBriefByUser("516959708")) {
                is ApiResponse.Success -> {
                    playlistBriefs.data.forEach {
                        println(it)
                    }
                    assertEquals(playlistBriefs.data.size, 11)
                    assertEquals(playlistBriefs.data[1].id, "8992878732")
                }

                is ApiResponse.BizError -> {

                }

                is ApiResponse.OtherError -> {

                }
            }
        }
    }

    @Test
    fun testGetRecommendPlaylist() {
        server.enqueue(MockResponse().setBody(getJson("recommend_playlist.json")))

        runBlocking {
            when (val playlistBriefs = subject.getRecommendPlaylist()) {
                is ApiResponse.Success -> {
                    playlistBriefs.data.forEach {
                        println(it)
                    }
                    assertEquals(playlistBriefs.data.size, 12)
                    assertEquals(playlistBriefs.data[0].id, "8075336924")
                }

                is ApiResponse.BizError -> {
                    println("BizError")
                }

                is ApiResponse.OtherError -> {
                    println("OtherError ${playlistBriefs.throwable}")
                }
            }
        }
    }
}
