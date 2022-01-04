package com.example.lightningmarket.src.Register.models

data class UserResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)