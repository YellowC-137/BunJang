package com.example.lightningmarket.src.product

import com.example.lightningmarket.src.product.models.CategoryData

interface ProductView {
    fun onGetCategorySuccess(response: CategoryData)

    fun onGetCategoryFailure(message: String)
}