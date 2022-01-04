package com.example.lightningmarket.src.btm_nav.post.responses

data class postresponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)