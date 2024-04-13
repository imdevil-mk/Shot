package com.imdevil.shot.core.network.common.retrofit

import com.imdevil.shot.core.network.common.model.ApiResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawReturnType = getRawType(returnType)
        if (rawReturnType != Call::class.java) return null
        val responseType: Type = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawResponseType = getRawType(responseType)
        if (rawResponseType != ApiResponse::class.java) return null
        return ApiResponseCallAdapter<Any>(responseType)
    }
}

class ApiResponseCallAdapter<T>(
    private val returnType: Type,
) : CallAdapter<ApiResponse<T>, Call<ApiResponse<T>>> {
    override fun responseType(): Type {
        return returnType
    }

    override fun adapt(call: Call<ApiResponse<T>>): Call<ApiResponse<T>> {
        return ApiResponseCall(call)
    }
}

class ApiResponseCall<T>(
    private val delegate: Call<ApiResponse<T>>
) : Call<ApiResponse<T>> {
    override fun enqueue(callback: Callback<ApiResponse<T>>) =
        delegate.enqueue(object : Callback<ApiResponse<T>> {
            override fun onResponse(
                call: Call<ApiResponse<T>>,
                response: Response<ApiResponse<T>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()!!
                    callback.onResponse(
                        this@ApiResponseCall,
                        Response.success(body)
                    )
                } else {
                    val throwable = HttpException(response)
                    callback.onResponse(
                        this@ApiResponseCall,
                        Response.success(ApiResponse.OtherError(throwable))
                    )
                }
            }

            override fun onFailure(call: Call<ApiResponse<T>>, t: Throwable) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(ApiResponse.OtherError(t))
                )
            }
        })

    override fun execute(): Response<ApiResponse<T>> {
        val response = delegate.execute()
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            Response.success(body)
        } else {
            val throwable = HttpException(response)
            Response.success(ApiResponse.OtherError(throwable))
        }
    }

    override fun clone(): Call<ApiResponse<T>> = ApiResponseCall(delegate)

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}