package com.example.lightningmarket.src.btm_nav.mypage

import com.example.lightningmarket.src.btm_nav.mypage.models.PatchProfile
import com.example.lightningmarket.src.btm_nav.mypage.models.ProfileData
import com.example.lightningmarket.src.detail.models.detailData

import retrofit2.Call
import retrofit2.http.*

interface MypageInterface {
    @GET("users/{userIdx}")
    fun getProfile(@Path("userIdx") userIdx:Int ) : Call<ProfileData>

    @PATCH("users/{userIdx}")
    fun patchProfile(@Path("userIdx") userIdx:Int,@Header("X-ACCESS-TOKEN") jwt:String,@Body params: PatchProfile) : Call<ProfileData>

    @GET("products/{productIdx}")
    fun getDetail(
        @Path("productIdx")productIdx : Int,@Header("X-ACCESS-TOKEN") jwt:String
    ) : Call<detailData>
}