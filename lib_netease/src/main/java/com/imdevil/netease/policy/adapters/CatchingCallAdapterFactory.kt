package com.imdevil.netease.policy.adapters

import com.imdevil.netease.model.ApiResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CatchingCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        println("origin return type = $returnType")

        val rawReturnType = getRawType(returnType)
        println("rawReturnType = $rawReturnType")
        if (rawReturnType != Call::class.java) return null

        val innerReturnType: Type = getParameterUpperBound(0, returnType as ParameterizedType)
        println("innerReturnType = $innerReturnType")
        val rawInnerReturnType = getRawType(innerReturnType)
        println("rawInnerReturnType = $rawInnerReturnType")
        if (rawInnerReturnType != ApiResponse::class.java) return null

        val delegate: CallAdapter<*, *> = retrofit.nextCallAdapter(this, returnType, annotations)

        return CatchingCallAdapter(
            innerReturnType,
            delegate,
        )
    }
}

class CatchingCallAdapter(
    private val dataType: Type,
    private val delegate: CallAdapter<*, *>,
) : CallAdapter<ApiResponse<Any>, Call<ApiResponse<Any>>> {
    override fun responseType(): Type {
        return delegate.responseType()
    }

    override fun adapt(call: Call<ApiResponse<Any>>): Call<ApiResponse<Any>> {
        return CatchingCall(
            call,
            dataType as ParameterizedType
        )
    }
}

class CatchingCall(
    private val delegate: Call<ApiResponse<Any>>,
    private val wrapperType: ParameterizedType,
) : Call<ApiResponse<Any>> {

    override fun enqueue(callback: Callback<ApiResponse<Any>>) = delegate.enqueue(object : Callback<ApiResponse<Any>> {
        override fun onResponse(call: Call<ApiResponse<Any>>, response: Response<ApiResponse<Any>>) {
            if (response.isSuccessful) {
                val body = response.body()
                callback.onResponse(
                    this@CatchingCall,
                    Response.success(body)
                )
            } else {
                val throwable = HttpException(response)
                callback.onResponse(
                    this@CatchingCall,
                    Response.success(ApiResponse.OtherError<Any>(throwable))
                )
            }
        }

        override fun onFailure(call: Call<ApiResponse<Any>>, t: Throwable) {
            callback.onResponse(
                this@CatchingCall,
                Response.success(ApiResponse.OtherError<Any>(t))
            )
        }
    })

    override fun clone(): Call<ApiResponse<Any>> = CatchingCall(delegate, wrapperType)

    override fun execute(): Response<ApiResponse<Any>> = delegate.execute()

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}