package com.example.lightningmarket.src.btm_nav.home

import android.content.ContentValues
import android.util.Log
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.src.productItem.productItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView) {
    fun tryGetProducts(){
        val homeInterface = ApplicationClass.retrofit.
        create(HomeInterface::class.java)
        homeInterface.getProducts().enqueue(object : Callback<productItems> {
            override fun onResponse(call: Call<productItems>, response: Response<productItems>) {
                view.onGetProductsSuccess(response.body() as productItems)
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<productItems>, t: Throwable) {
                view.onGetProductsFailure(t.message ?: "통신 오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }
        })
    }
}