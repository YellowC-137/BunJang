package com.example.lightningmarket.src.btm_nav.post.models

data class postdata(
    val areaIdx: Int,
    val changes: String,
    val conditions: String,
    val content: String,
    val freeShipping: String,
    val imgList: List<Img>,
    val negotiable: String,
    val prices: Int,
    val productName: String,
    val quantity: Int,
    val subcategoryIdx: Int,
    val tagList: List<Tag>
)