package com.imdevil.core.tencent.moshi.adapters

import com.imdevil.core.common.utils.Types
import com.imdevil.core.tencent.bean.UserInfo
import com.imdevil.core.tencent.moshi.readJsonObject
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonAdapter.Factory
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi

class UserInfoJsonAdapter(
    private val moshi: Moshi,
) : JsonAdapter<UserInfo>() {

    private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java)
    private val longAdapter: JsonAdapter<Long> = moshi.adapter(Long::class.java)
    private val listOfStringAdapter: JsonAdapter<List<String>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, String::class.java))

    override fun fromJson(reader: JsonReader): UserInfo {
        var name = ""
        var uin = ""
        var avatar = ""
        val icons = mutableListOf<String>()
        var follows: Long = 0
        var followedSingers: Long = 0
        var followedUsers: Long = 0
        var fans: Long = 0
        var friends: Long = 0
        var visitors: Long = 0

        readJsonObject(reader) {
            readChildObject("data") {
                readChildObject("creator") {
                    readValue("nick") {
                        name = it.nextString()
                    }
                    readValue("encrypt_uin") {
                        uin = it.nextString()
                    }
                    readValue("headpic") {
                        avatar = it.nextString()
                    }
                    readChildList("lvinfo") {
                        readValue("iconurl") {
                            icons.add(it.nextString())
                        }
                    }
                    readChildObject("userInfoUI") {
                        readChildList("iconlist") {
                            readValue("srcUrl") {
                                icons.add(it.nextString())
                            }
                        }
                    }
                    readChildObject("nums") {
                        readValue("follownum") {
                            follows = it.nextLong()
                        }
                        readValue("followsingernum") {
                            followedSingers = it.nextLong()
                        }
                        readValue("followusernum") {
                            followedUsers = it.nextLong()
                        }
                        readValue("fansnum") {
                            fans = it.nextLong()
                        }
                        readValue("frdnum") {
                            friends = it.nextLong()
                        }
                        readValue("visitornum") {
                            visitors = it.nextLong()
                        }
                    }
                }
            }
        }
        return UserInfo(
            name,
            uin,
            avatar,
            follows,
            followedSingers,
            followedUsers,
            fans,
            friends,
            visitors,
            icons
        )
    }

    override fun toJson(writer: JsonWriter, userInfo: UserInfo?) {
        if (userInfo == null) {
            throw NullPointerException("userInfo was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("name")
        stringAdapter.toJson(writer, userInfo.name)
        writer.name("uin")
        stringAdapter.toJson(writer, userInfo.uin)
        writer.name("avatar")
        stringAdapter.toJson(writer, userInfo.avatar)
        writer.name("follows")
        longAdapter.toJson(writer, userInfo.follows)
        writer.name("followedSingers")
        longAdapter.toJson(writer, userInfo.followedSingers)
        writer.name("followedUsers")
        longAdapter.toJson(writer, userInfo.followedUsers)
        writer.name("fans")
        longAdapter.toJson(writer, userInfo.fans)
        writer.name("friends")
        longAdapter.toJson(writer, userInfo.friends)
        writer.name("visitors")
        longAdapter.toJson(writer, userInfo.visitors)
        writer.name("icons")
        listOfStringAdapter.toJson(writer, userInfo.icons)
        writer.endObject()
    }

    companion object {
        fun newFactory(): Factory {
            return Factory { _, _, moshi -> UserInfoJsonAdapter(moshi) }
        }
    }
}