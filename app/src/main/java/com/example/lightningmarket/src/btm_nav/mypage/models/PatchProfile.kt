package com.example.lightningmarket.src.btm_nav.mypage.models

import java.sql.Time

data class PatchProfile(
    var profiles : String,
    var storeName : String,
    var storeAddress : String,
    var contactableTime: String,
    var storeIntro: String,
    var tradePolicy: String,
    var flag : String
)
