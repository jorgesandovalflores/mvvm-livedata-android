package com.quebuu.mvvm_livedata_android.core.repository.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiBodyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return if (response.code == 204 || response.code == 205) {
            response
                .newBuilder()
                .code(200)
                .body(response.body)
                .build()
        } else {
            response
        }
    }
}