package com.imdevil.shot.core.network.tencent

import com.imdevil.core.tencent.di.NetworkProvideModule
import com.imdevil.core.tencent.model.ICookie
import com.imdevil.core.tencent.retrofit.RetrofitTencentNetwork
import com.imdevil.shot.core.network.common.model.onSuccess
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class TencentApiTest {
    private lateinit var cookieManager: ICookie
    private lateinit var subject: RetrofitTencentNetwork
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun prepare() {
        cookieManager = JvmTestCookieManager()
        subject = RetrofitTencentNetwork(
            cookieManager,
            NetworkProvideModule.providesTencentHttpUrl(),
            NetworkProvideModule.providesTencentMoshi(),
        )
    }

    @Test
    fun ensureCookie() {
        println(cookieManager.getCookie())
    }

    @Test
    fun testGetUserInfo() = runTest {
        subject.getUserInfo().onSuccess {
            println(it)
        }
    }

    @Test
    fun getGetPlaylistByUser() = runTest {
        subject.getPlaylistBriefByUser().onSuccess {
            for (playlistBrief in it) {
                println(playlistBrief)
            }
        }
    }

    @Test
    fun testGetRecommendPlaylist() = runTest {
        subject.getRecommendPlaylist()
    }

    @Test
    fun testGetPlaylistByCategory() = runTest {
        subject.getPlaylistByCategory()
    }

    @Test
    fun testGetNewSongs() = runTest {
        subject.getNewSongs()
    }

    @Test
    fun testGetSongDetail() = runTest {
        subject.getSongDetail("004IXgOS20hEjo")
    }

    @Test
    fun testGetHotKeys() = runTest {
        subject.getHotKeys()
    }

    @Test
    fun testGetRecommend() = runTest {
        subject.getRecommend()
    }

    @Test
    fun testGetNewAlbumArea() = runTest {
        subject.getNewAlbumArea()
    }

    @Test
    fun testGetNewAlbumInArea() = runTest {
        subject.getNewAlbumInArea()
    }

    @Test
    fun testGetLeaderBoard() = runTest {
        subject.getLeaderBoard()
    }

    @Test
    fun testGetHotCategory() = runTest {
        subject.getHotCategory()
    }
}
