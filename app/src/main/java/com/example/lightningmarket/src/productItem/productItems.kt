package com.example.lightningmarket.src.productItem

data class productItems(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)