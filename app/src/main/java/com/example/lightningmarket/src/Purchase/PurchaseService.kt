package com.example.lightningmarket.src.Purchase

import android.content.ContentValues
import android.util.Log
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.src.Purchase.models.PurchaseData
import com.example.lightningmarket.src.Purchase.models.PurchaseRequest
import com.example.lightningmarket.src.Register.models.UserResponse
import com.example.lightningmarket.src.detail.Detailinterface
import com.example.lightningmarket.src.detail.models.detailData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PurchaseService(val view:PurchaseView) {
    fun trygetPurchase(userIdx:Int,purchaseRequest: PurchaseRequest){
        val retrofitInterface = ApplicationClass.retrofit.create(PurchaseInterface::class.java)
        retrofitInterface.getPurchase(userIdx,purchaseRequest).enqueue(object :Callback<PurchaseData>{
            override fun onResponse(call: Call<PurchaseData>, response: Response<PurchaseData>) {
                view.onPurchaseSuccess(response.body() as PurchaseData) //response가 null로 옴
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<PurchaseData>, t: Throwable) {
                view.onPurchaseFailure(t.message ?: "통신 오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }

        })

    }

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
}