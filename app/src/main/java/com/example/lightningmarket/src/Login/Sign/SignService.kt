package com.example.lightningmarket.src.Login.Sign

import android.content.ContentValues
import android.util.Log
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.src.Login.Sign.models.LoginResponse
import com.example.lightningmarket.src.Login.Sign.models.PostLoginRequest
import com.example.lightningmarket.src.Register.RegisterInterface
import com.example.lightningmarket.src.Register.models.PostRegisterRequest
import com.example.lightningmarket.src.Register.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignService(val view: SignView) {

    fun tryPostLogin(postLoginRequest: PostLoginRequest){
        val loginRetrofitInterface = ApplicationClass.retrofit.create(SignInterface::class.java)
        loginRetrofitInterface.postLogin(postLoginRequest).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                view.onPostLoginSuccess(response.body() as LoginResponse) //response가 null로 옴
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onPostLoginFailure(t.message ?: "통신 오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }
        })
    }
}