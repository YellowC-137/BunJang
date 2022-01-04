package com.example.lightningmarket.src.detail

import com.example.lightningmarket.src.detail.models.detailData
import com.example.lightningmarket.src.productItem.productItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface Detailinterface {
    @GET("products/{productIdx}")
    fun getDetail(
        @Path("productIdx")productIdx : Int,@Header("X-ACCESS-TOKEN") jwt:String
    ) : Call<detailData>

    @GET("products/")
    fun getProducts() : Call<productItems>
}