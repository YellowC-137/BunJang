package com.example.lightningmarket.src.btm_nav.home

import com.example.lightningmarket.src.productItem.productItems

interface HomeFragmentView {
    fun onGetProductsSuccess(response: productItems)

    fun onGetProductsFailure(message: String)
}


