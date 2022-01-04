package com.example.lightningmarket.src.Purchase

import com.example.lightningmarket.src.Purchase.models.PurchaseData
import com.example.lightningmarket.src.Purchase.models.PurchaseRequest
import com.example.lightningmarket.src.detail.models.detailData
import retrofit2.Call
import retrofit2.http.*

interface PurchaseInterface {
    @GET("products/{productIdx}")
    fun getDetail(
        @Path("productIdx")productIdx : Int,@Header("X-ACCESS-TOKEN") jwt:String
    ) : Call<detailData>


    @POST("purchases/{userIdx}")
    fun getPurchase(
        @Path("userIdx")userIdx : Int, @Body params: PurchaseRequest
    ) : Call<PurchaseData>
}