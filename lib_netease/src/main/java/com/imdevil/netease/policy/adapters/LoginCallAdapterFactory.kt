package com.imdevil.netease.policy.adapters

import com.imdevil.netease.policy.annotations.NeteaseLogin
import com.imdevil.netease.policy.cookie.ICookie
import com.imdevil.netease.policy.cookie.INTERESTED_COOKIES
import retrofit2.*
import java.lang.reflect.Type

class LoginCallAdapterFactory(
    private val cookieManager: ICookie,
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        annotations.forEach {
            if (it is NeteaseLogin) {
                val delegate: CallAdapter<*, *> =
                    retrofit.nextCallAdapter(this, returnType, annotations)
                return LoginCallAdapter(delegate, cookieManager)
            }
        }
        return null
    }
}

class LoginCallAdapter(
    private val delegate: CallAdapter<*, *>,
    private val cookieManager: ICookie,
) : CallAdapter<Any, Call<Any>> {
    override fun responseType(): Type = delegate.responseType()

    override fun adapt(call: Call<Any>): Call<Any> = LoginCall(call, cookieManager)
}

class LoginCall(
    private val delegate: Call<Any>,
    private val cookieManager: ICookie,
) : Call<Any> by delegate {

    override fun enqueue(callback: Callback<Any>) = delegate.enqueue(object : Callback<Any> {
        override fun onResponse(call: Call<Any>, response: Response<Any>) {
            if (response.isSuccessful) {
                val cookies = mutableMapOf<String, String>()

                response.headers().toMultimap()["set-cookie"]?.forEach {
                    it.split(";")[0].split("=").also { list ->
                        if (INTERESTED_COOKIES.contains(list[0])) {
                            cookies[list[0]] = list.getOrNull(1) ?: ""
                        }
                    }
                }

                cookieManager.saveCookies(cookies)
            }

            callback.onResponse(this@LoginCall, response)
        }

        override fun onFailure(call: Call<Any>, t: Throwable) {
            // never enter
        }
    })

    override fun clone(): Call<Any> = LoginCall(delegate, cookieManager)
}