package com.example.lightningmarket.src.Purchase.models

data class PurchaseRequest(
    val addressIdx: Int,
    val points: Int,
    val productIdx: Int,
    val purchaseType: String,
    val requestMsg: String
)