package com.example.lightningmarket.src.btm_nav.post

import com.example.lightningmarket.src.Register.models.PostRegisterRequest
import com.example.lightningmarket.src.Register.models.UserResponse
import com.example.lightningmarket.src.btm_nav.post.models.postdata
import com.example.lightningmarket.src.btm_nav.post.responses.postresponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PostInterface {
    @POST("products/{userIdx}")
    fun postitem(@Path("userIdx")userIdx:Int,@Header("X-ACCESS-TOKEN") jwt:String,
    @Body params: postdata): Call<postresponse>
}