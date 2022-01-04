package com.example.lightningmarket.src.Register

import com.example.lightningmarket.src.Register.models.PostRegisterRequest
import com.example.lightningmarket.src.Register.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterInterface {

    @POST("users/")
    fun postSignUp(@Body params: PostRegisterRequest): Call<UserResponse>
}