package com.example.lightningmarket.src.Login.Sign.models

data class LoginResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)