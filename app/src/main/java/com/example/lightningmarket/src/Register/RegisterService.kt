package com.example.lightningmarket.src.Register

import android.content.ContentValues
import android.util.Log
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.src.Register.models.PostRegisterRequest
import com.example.lightningmarket.src.Register.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class RegisterService(val view: RegisterView) {

    fun tryPostSignUp(postRegisterRequest: PostRegisterRequest){
        val registerRetrofitInterface = ApplicationClass.retrofit.create(RegisterInterface::class.java)
        registerRetrofitInterface.postSignUp(postRegisterRequest).enqueue(object :
            Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                view.onPostSignUpSuccess(response.body() as UserResponse) //response가 null로 옴
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }
        })
    }

}
