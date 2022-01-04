package com.example.lightningmarket.config

import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = SP.getString(X_ACCESS_TOKEN, null)
        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken)
        }
        //자동으로 http 헤더에 jwt 토큰 붙이기
        return chain.proceed(builder.build())
    }
}