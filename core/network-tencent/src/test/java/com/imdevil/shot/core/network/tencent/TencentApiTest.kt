package com.imdevil.shot.core.network.tencent

import com.imdevil.core.tencent.model.ICookie
import com.imdevil.core.tencent.retrofit.RetrofitTencentNetwork
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TencentApiTest {
    private lateinit var cookieManager: ICookie
    private lateinit var subject: RetrofitTencentNetwork

    @Before
    fun prepare() {
        cookieManager = JvmTestCookieManager()
        subject = RetrofitTencentNetwork(cookieManager)
    }

    @Test
    fun ensureCookie() {
        println(cookieManager.getCookie())
    }

    @Test
    fun testGetUserInfo() {
        runBlocking {
            subject.getUserInfo("")
        }
    }

    @Test
    fun testGetRecommend() {
        runBlocking {
            subject.getRecommend()
        }
    }

    @Test
    fun testGetRecommendPlaylist() {
        runBlocking {
            subject.getRecommendPlaylist()
        }
    }

    @Test
    fun testGetNewAlbumArea() {
        runBlocking {
            subject.getNewAlbumArea()
        }
    }

    @Test
    fun testGetNewAlbumInArea() {
        runBlocking {
            subject.getNewAlbumInArea()
        }
    }

    @Test
    fun testGetLeaderBoard() {
        runBlocking {
            subject.getLeaderBoard()
        }
    }

    @Test
    fun testGetHotCategory() {
        runBlocking {
            subject.getHotCategory()
        }
    }

    @Test
    fun testPlaylistByCategory() {
        runBlocking {
            subject.getPlaylistByCategory()
        }
    }
}
