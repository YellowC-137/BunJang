package com.example.lightningmarket.src.product

import com.example.lightningmarket.src.detail.models.detailData
import com.example.lightningmarket.src.product.models.CategoryData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProductInterface {
    @GET("categories/{subcategoryIdx}")
    fun getCategory(
        @Path("subcategoryIdx")subcategoryIdx : Int, @Header("X-ACCESS-TOKEN") jwt:String
    ) : Call<CategoryData>
}