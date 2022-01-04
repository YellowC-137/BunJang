package com.example.lightningmarket.src.btm_nav.home

import com.example.lightningmarket.src.productItem.productItems
import retrofit2.Call
import retrofit2.http.GET

interface HomeInterface {
    @GET("products/")
    fun getProducts() : Call<productItems>
}