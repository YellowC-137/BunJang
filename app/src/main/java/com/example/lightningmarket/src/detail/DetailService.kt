package com.example.lightningmarket.src.detail
import com.example.lightningmarket.src.detail.models.detailData
import android.content.ContentValues
import android.util.Log
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.src.btm_nav.home.HomeInterface
import com.example.lightningmarket.src.productItem.productItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class DetailService(val view:DetailView) {
    fun tryGetDetail(productIdx:Int,jwt:String){
        val detailinterface = ApplicationClass.retrofit.create(Detailinterface::class.java)
        detailinterface.getDetail(productIdx,jwt).enqueue(object :Callback<detailData>{
            override fun onResponse(call: Call<detailData>, response: Response<detailData>) {
                view.onGetDetailSuccess(response.body() as detailData,productIdx)
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<detailData>, t: Throwable) {
                view.onGetDetailFailure(t.message ?:"동신오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }


        })
    }

    fun tryGetProducts(){
        val homeInterface = ApplicationClass.retrofit.
        create(Detailinterface::class.java)
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