package com.imdevil.netease

import com.imdevil.core.netease.NeteaseManager
import com.imdevil.core.netease.model.DefaultFileCookieManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NetworkTest {

    private val cookieFile = getRes("cookies.txt")
    private val api = NeteaseManager.getInstance(DefaultFileCookieManager(cookieFile))
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val uid = "48919251"
    private val playlistId = "6901317824"
    private val songId = "28009051"

    @Before
    fun prepare() {
        if (!cookieFile.exists()) {
            cookieFile.createNewFile()
        }
    }

    @Test
    fun loginStatus() = runBlocking {
        api.loginStatus(uid).logAndSave(moshi, "login_status.json")
    }

    @Test
    fun getAccount() = runBlocking {
        api.getAccount().execute().body()!!.string().saveAsJson("user_account.json")
    }

    @Test
    fun getSubCount() = runBlocking {
        api.getSubCount().execute().body()!!.string().saveAsJson("user_sub_count.json")
    }

    @Test
    fun getPlaylist() = runBlocking {
        api.getPlaylists(uid, 30, 0, true).log {
            this.playlists.forEach {
                println(it.name)
            }
        }
    }

    @Test
    fun getPlayListDetail() = runBlocking {
        api.getPlaylistDetail(playlistId).log {
            with(detail) {
                println("$id-$name")
                tracks.forEach { println(it.name) }
            }
        }
    }

    @Test
    fun getSongDetail() = runBlocking {
        api.getSongDetailBatch("[{\"id\":$songId}]").log {
            songs.forEach { println(it.name) }
        }
    }

    @Test
    fun getSongPlayInfo() = runBlocking {
        api.getSongPlayInfo("[$songId]").log {
            this.songs.forEach { println(it.url) }
        }
    }
}