package com.example.lightningmarket.src.Register

import com.example.lightningmarket.src.Login.Sign.models.LoginResponse
import com.example.lightningmarket.src.Register.models.UserResponse

interface RegisterView {
    fun onPostSignUpSuccess(response: UserResponse)

    fun onPostSignUpFailure(message: String)
}