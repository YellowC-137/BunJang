package com.example.lightningmarket.src.product.models

data class CategoryData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)