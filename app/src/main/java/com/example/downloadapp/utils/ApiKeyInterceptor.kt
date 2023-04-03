package com.example.downloadapp.utils

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("JsonStub-User-Key","f5e0861a-b53d-4b80-9c28-2233780c3d5d")
            .build()

        return chain.proceed(request)
    }
}