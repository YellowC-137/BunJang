package com.example.lightningmarket.src.btm_nav.mypage

import com.example.lightningmarket.src.btm_nav.mypage.models.ProfileData
import com.example.lightningmarket.src.detail.models.detailData

interface MypageView {
    fun onGetDetailSuccess(response: detailData, productIdx:Int)

    fun onGetDetailFailure(message: String)

    fun onGetProfileSuccess(response: ProfileData)

    fun onGetProfileFailure(message: String)

    fun onPatchProfileSuccess(response: ProfileData)

    fun onPatchProfileFailure(message: String)
}