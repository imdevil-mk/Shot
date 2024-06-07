package com.imdevil.core.tencent.retrofit

import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.shot.core.network.common.model.ApiResponse
import com.imdevil.shot.core.network.common.model.Host
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TencentApi {

    /**
     * 获取用户主页信息
     */
    @GET("/rsc/fcgi-bin/fcg_get_profile_homepage.fcg")
    suspend fun getUserInfo(
        @Query("userid") userid: String,
        @Query("cid") cid: Int = 205360838,
        @Query("reqfrom") reqfrom: Int = 1,
        /*
        @Query("hostuin") hostuin: String,
        @Query("loginUin") loginUin: String,
        @Query("hostUin") hostUin: String = "0",
         */
        @Query("cv") cv: Int = 4747474,
        @Query("ct") ct: Int = 24,
        @Query("reqtype") reqtype: Int = 0,
        @Query("g_tk") g_tk: Int = 5381,
        @Query("format") format: String = "json",
        @Query("inCharset") inCharset: String = "utf8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("notice") notice: String = "0",
        @Query("platform") platform: String = "yqq.json",
        @Query("needNewCode") needNewCode: String = "0",
    ): ResponseBody

    /**
     * 获取用户创建的歌单列表
     */
    @Headers("Referer: https://y.qq.com/portal/profile.html")
    @GET("/rsc/fcgi-bin/fcg_user_created_diss")
    suspend fun getPlaylistBriefByUser(
        @Query("hostuin") uin: String,
        @Query("size") size: Int = 200,
        @Query("hostUin") hostUin: String = "0",
        @Query("sin") sin: Int = 0,
        @Query("g_tk") g_tk: Int = 5381,
        @Query("loginUin") loginUin: String = "0",
        @Query("format") format: String = "json",
        @Query("inCharset") inCharset: String = "utf8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("notice") notice: String = "0",
        @Query("platform") platform: String = "yqq.json",
        @Query("needNewCode") needNewCode: String = "0",
    ): ApiResponse<List<PlaylistBrief>>

    /**
     * 获取歌单详细信息
     */
    @Headers("Referer: https://y.qq.com/portal/profile.html")
    @GET("/qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg")
    suspend fun getPlaylist(
        @Query("loginUin") uin: String,
        @Query("disstid") tid: String,
        @Query("format") format: String = "json",
        @Query("type") sin: Int = 1,
        @Query("utf8") size: Int = 1,
    ): ResponseBody

    /**
     * 获取歌曲详细信息
     */
    @Host("u.y.qq.com")
    @GET("/cgi-bin/musicu.fcg")
    suspend fun getSongDetail(
        @Query("data") data: String,
    ): ResponseBody

    /**
     * 获取推荐歌单
     */
    @Host("u.y.qq.com")
    @GET("/cgi-bin/musicu.fcg")
    suspend fun getRecommend(
        @Query("data") data: String = """
{
    "comm": {
        "ct": 24
    },
    "hot_category": {
        "method": "get_hot_category",
        "param": {
            "qq": ""
        },
        "module": "music.web_category_svr"
    },
    "playlist_by_category": {
        "method": "get_playlist_by_category",
        "param": {
            "id": 8,
            "curPage": 1,
            "size": 40,
            "order": 5,
            "titleid": 8
        },
        "module": "playlist.PlayListPlazaServer"
    },
    "hot_recommend": {
        "method": "get_hot_recommend",
        "param": {
            "async": 1,
            "cmd": 2
        },
        "module": "playlist.HotRecommendServer"
    },
    "new_song": {
        "module": "newsong.NewSongServer",
        "method": "get_new_song_info",
        "param": {
            "type": 5
        }
    },
    "new_album_area": {
        "module": "newalbum.NewAlbumServer",
        "method": "get_new_album_area",
        "param": {}
    },
    "new_album": {
        "module": "newalbum.NewAlbumServer",
        "method": "get_new_album_info",
        "param": {
            "area": 1,
            "sin": 0,
            "num": 10
        }
    },
    "toplist": {
        "module": "musicToplist.ToplistInfoServer",
        "method": "GetAll",
        "param": {}
    },
    "focus": {
        "module": "QQMusic.MusichallServer",
        "method": "GetFocus",
        "param": {}
    }
}
        """.trimIndent(),
    ): ResponseBody

    /**
     * 获取推荐歌单
     */
    @Host("u.y.qq.com")
    @GET("/cgi-bin/musicu.fcg")
    suspend fun getRecommendPlaylist(
        @Query("data") data: String = """
{
    "comm": {
        "ct": 24
    },
    "recommend_playlist": {
        "method": "get_hot_recommend",
        "param": {
            "async": 1,
            "cmd": 2
        },
        "module": "playlist.HotRecommendServer"
    }
}
        """.trimIndent(),
    ): ApiResponse<List<PlaylistBrief>>

    /**
     * 获取新专辑区域
     */
    @Host("u.y.qq.com")
    @GET("/cgi-bin/musicu.fcg")
    suspend fun getNewAlbumArea(
        @Query("data") data: String = """
{
    "comm": {
        "ct": 24
    },
    "newAlbumArea": {
        "module": "newalbum.NewAlbumServer",
        "method": "get_new_album_area",
        "param": {}
    }
}
        """.trimIndent(),
    ): ResponseBody

    /**
     * 获取某个区域下的新专辑
     */
    @Host("u.y.qq.com")
    @GET("/cgi-bin/musicu.fcg")
    suspend fun getNewAlbumInArea(
        @Query("data") data: String = """
{
    "comm": {
        "ct": 24
    },
    "newAlbumArea": {
        "module": "newalbum.NewAlbumServer",
        "method": "get_new_album_info",
        "param": {
            "area": 1,
            "sin": 0,
            "num": 20
        }
    }
}
        """.trimIndent(),
    ): ResponseBody

    /**
     * 获取整个榜单
     */
    @Host("u.y.qq.com")
    @GET("/cgi-bin/musicu.fcg")
    suspend fun getLeaderBoard(
        @Query("data") data: String = """
{
    "comm": {
        "ct": 24
    },
    "leader_board": {
        "module": "musicToplist.ToplistInfoServer",
        "method": "GetAll",
        "param": {}
    }
}
        """.trimIndent(),
    ): ResponseBody

    /**
     * 获取热门分类
     */
    @Host("u.y.qq.com")
    @GET("/cgi-bin/musicu.fcg")
    suspend fun getHotCategory(
        @Query("data") data: String = """
{
    "comm": {
        "ct": 24
    },
    "hot_category": {
        "method": "get_hot_category",
        "param": {
            "qq": ""
        },
        "module": "music.web_category_svr"
    }
}
        """.trimIndent(),
    ): ResponseBody

    /**
     * 获取某个分类下的歌单列表
     */
    @Host("u.y.qq.com")
    @GET("/cgi-bin/musicu.fcg")
    suspend fun getPlaylistByCategory(
        @Query("data") data: String = """
{
    "comm": {
        "ct": 24
    },
    "playlist_by_category": {
        "method": "get_playlist_by_category",
        "param": {
            "id": 3317,
            "curPage": 1,
            "size": 40,
            "order": 5,
            "titleid": 8
        },
        "module": "playlist.PlayListPlazaServer"
    }
}
        """.trimIndent(),
    ): ResponseBody

    /**
     * 获取榜单
     */
    @Host("c.y.qq.com")
    @GET("/v8/fcg-bin/fcg_myqq_toplist.fcg")
    suspend fun getLeaderBoardDeprecated(
        @Query("uin") uin: String = "0",
        @Query("g_tk") g_tk: Int = 1928093487,
        @Query("format") format: String = "json",
        @Query("inCharset") inCharset: String = "utf8",
        @Query("outCharset") outCharset: String = "utf-8",
        @Query("notice") notice: String = "0",
        @Query("platform") platform: String = "yqq.json",
        @Query("needNewCode") needNewCode: String = "1",
    ): ResponseBody
}
