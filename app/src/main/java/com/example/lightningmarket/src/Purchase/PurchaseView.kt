package com.example.lightningmarket.src.Purchase

import com.example.lightningmarket.src.Purchase.models.PurchaseData
import com.example.lightningmarket.src.detail.models.detailData


interface PurchaseView {

    fun onPurchaseSuccess(response: PurchaseData)

    fun onPurchaseFailure(message: String)

    fun onGetDetailSuccess(response: detailData, productIdx:Int)

    fun onGetDetailFailure(message: String)
}