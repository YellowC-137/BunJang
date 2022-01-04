package com.example.lightningmarket.src.btm_nav.post

import android.content.ContentValues
import android.util.Log
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.src.btm_nav.post.models.postdata
import com.example.lightningmarket.src.btm_nav.post.responses.postresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PostService(val view: PostView) {
    fun tryPostItem(jwt:String,userIdx:Int,Postdata: postdata){
        val postItemInterface = ApplicationClass.retrofit.create(PostInterface::class.java)
        postItemInterface.postitem(userIdx,jwt,Postdata).enqueue(object : Callback<postresponse>{
            override fun onResponse(call: Call<postresponse>, response: Response<postresponse>) {
                view.onPostItemSuccess(response.body() as postresponse)
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<postresponse>, t: Throwable) {
                view.onPostItemFailure(t.message ?: "통신 오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }

        })
    }
}