package com.example.lightningmarket.src.product

import android.content.ContentValues
import android.util.Log
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.src.product.models.CategoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class ProductService(val view:ProductView) {
    fun trygetCategory(subcategoryIdx:Int,jwt:String){
        val categoryinterface = ApplicationClass.retrofit.create(ProductInterface::class.java)
        categoryinterface.getCategory(subcategoryIdx, jwt).enqueue(object :Callback<CategoryData>{
            override fun onResponse(call: Call<CategoryData>, response: Response<CategoryData>) {
                view.onGetCategorySuccess(response.body() as CategoryData)
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<CategoryData>, t: Throwable) {
                view.onGetCategoryFailure(t.message ?: "통신오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }

        })
    }
}