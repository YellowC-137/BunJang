package com.example.lightningmarket.src.detail

import com.example.lightningmarket.src.detail.models.detailData
import com.example.lightningmarket.src.productItem.productItems


interface DetailView {
    fun onGetDetailSuccess(response: detailData,productIdx:Int)

    fun onGetDetailFailure(message: String)

    fun onGetProductsSuccess(response: productItems)

    fun onGetProductsFailure(message: String)
}