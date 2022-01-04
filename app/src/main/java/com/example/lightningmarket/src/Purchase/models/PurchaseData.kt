package com.example.lightningmarket.src.Purchase.models

data class PurchaseData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)