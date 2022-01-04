package com.example.lightningmarket.src.product.models

data class Goods(
    val areaName: Any,
    val cntLikes: Int,
    val createdAt: String,
    val imgUrl: String,
    val prices: Int,
    val productIdx: Int,
    val productName: String,
    val safePayment: String,
    val statusLike: Any
)