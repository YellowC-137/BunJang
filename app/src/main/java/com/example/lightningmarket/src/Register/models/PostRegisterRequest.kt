package com.example.lightningmarket.src.Register.models

data class PostRegisterRequest(
    val email : String,
    val password : String,
    val storeName : String,
    val userType : Char
)