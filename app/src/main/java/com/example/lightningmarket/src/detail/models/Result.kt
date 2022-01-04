package com.example.lightningmarket.src.detail.models

data class Result(
    val areaName: Any,
    val avgScores: Double,
    val changes: String,
    val cntFollowers: Int,
    val cntLikes: Int,
    val conditions: String,
    val content: String,
    val createdAt: String,
    val freeShipping: String,
    val imgList: List<Img>,
    val negotiable: String,
    val prices: Int,
    val productIdx: Int,
    val productName: String,
    val quantity: Int,
    val safePayment: String,
    val statusLike: Any,
    val storeName: String,
    val subcategoryName: String,
    val tagList: List<Tag>
)