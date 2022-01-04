package com.example.lightningmarket.src.product.models

data class Result(
    val goodsList: List<Goods>,
    val imgUrl: String,
    val subcategoryIdx: Int,
    val subcategoryName: String
)