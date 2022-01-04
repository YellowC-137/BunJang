package com.example.lightningmarket.src.Login.Sign

import com.example.lightningmarket.src.Login.Sign.models.LoginResponse
import com.example.lightningmarket.src.Register.models.UserResponse

interface SignView {
    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)
}