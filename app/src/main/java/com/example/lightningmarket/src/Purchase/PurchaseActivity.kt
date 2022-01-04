package com.example.lightningmarket.src.Purchase

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.lightningmarket.R
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityPurchaseBinding
import com.example.lightningmarket.databinding.FragmentReserveBinding
import com.example.lightningmarket.src.MainActivity
import com.example.lightningmarket.src.Purchase.models.PurchaseData
import com.example.lightningmarket.src.Purchase.models.PurchaseRequest
import com.example.lightningmarket.src.detail.DetailService
import com.example.lightningmarket.src.detail.models.detailData

class PurchaseActivity : BaseActivity<ActivityPurchaseBinding>(ActivityPurchaseBinding::inflate) ,PurchaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val userIdx = SP.getInt("useridx",0)
        var getintent = intent
        val jwt = SP.getString(X_ACCESS_TOKEN,"")
        val productIdx = getintent.getIntExtra("itemIdx",0)

        if (jwt != null) {
            setPurchase(productIdx,jwt)
        }

        binding.btnPay.setOnClickListener {
            buy(userIdx,productIdx)
        }
        binding.ivChk.setOnClickListener {
            Glide.with(binding.ivChk.context).load(R.drawable.pay_chk).into(binding.ivChk)
        }
    }
    private fun setPurchase(productIdx : Int,jwt:String){
        PurchaseService(this).tryGetDetail(productIdx,jwt)
    }

    private fun buy(userIdx:Int,ProductIdx:Int){
        val purchaseRequest = PurchaseRequest(
            addressIdx = 0,
            points = 0,
            productIdx = ProductIdx,
            purchaseType = "D",
            requestMsg = "구매하겠습니다!"

        )
        PurchaseService(this).trygetPurchase(userIdx,purchaseRequest)
    }

    override fun onPurchaseSuccess(response: PurchaseData) {
        showToast("예약 완료했습니다")
        val editor : SharedPreferences.Editor = SP.edit()
        editor.putInt("mypurchase",response.result.purchaseIdx)
        finish()
    }

    override fun onPurchaseFailure(message: String) {
        showToast("오류 : $message")
    }

    override fun onGetDetailSuccess(response: detailData, productIdx: Int) {
        Glide.with(binding.ivItem.context).load(response.result.imgList[0].imgUrl).into(binding.ivItem)
        binding.tvItemPrice.text = "${response.result.prices}원"
        binding.tvProduct.text = response.result.productName
        binding.price1.text = "${response.result.prices}원"
        binding.price2.text =  "${response.result.prices}원"
        binding.tvTotal.text =  "${response.result.prices}원"
    }

    override fun onGetDetailFailure(message: String) {

    }
}