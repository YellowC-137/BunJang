package com.example.lightningmarket.src.Login.Sign

import com.example.lightningmarket.src.Login.Sign.models.LoginResponse
import com.example.lightningmarket.src.Login.Sign.models.PostLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignInterface {

    @POST("users/logIn")
    fun postLogin(@Body params: PostLoginRequest): Call<LoginResponse>
}