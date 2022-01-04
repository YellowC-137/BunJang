package com.example.lightningmarket.src.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.lightningmarket.R
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityDetailBinding
import com.example.lightningmarket.databinding.BottomsheetPurchaseBinding
import com.example.lightningmarket.databinding.BottomsheetSettingBinding
import com.example.lightningmarket.src.Purchase.PurchaseActivity
import com.example.lightningmarket.src.btm_nav.mypage.setting.SettingActivity
import com.example.lightningmarket.src.detail.models.Result
import com.example.lightningmarket.src.detail.models.detailData
import com.example.lightningmarket.src.productItem.productItems
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_detail.*
import kotlin.math.abs

class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate),DetailView {
    lateinit var BottomsheetLayout: View
    private lateinit var SimilarAdapter: similarAdapter
    private lateinit var PlusAdapter : plusAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var getintent = intent
        val productIdx = getintent.getIntExtra("productIdx",0)
        val jwtToken: String? = ApplicationClass.SP.getString(ApplicationClass.X_ACCESS_TOKEN, null)

        if (jwtToken != null) {
            setDetail(productIdx,jwtToken)
        }
        setbot()
        binding.pay.setOnClickListener {
            var payDialog = BottomSheetDialog(this)
            val PurchaseBinding = BottomsheetPurchaseBinding.inflate(layoutInflater)
            if (payDialog != null) {
                payDialog.setContentView(PurchaseBinding.root)
                payDialog.setCanceledOnTouchOutside(true)
                payDialog.create()
                payDialog.show()
                PurchaseBinding.facePay.setOnClickListener {
                    val intent = Intent(this, PurchaseActivity::class.java)
                    intent.putExtra("itemIdx",productIdx)
                    startActivity(intent)
                }
            }
        }


        binding.appbar.addOnOffsetChangedListener(object :AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (appBarLayout != null){
                    when{
                        verticalOffset == 0->{
                            binding.toolbarHidden.visibility = View.GONE
                            binding.toolbar.visibility = View.VISIBLE
                        }
                        abs(verticalOffset)>=appBarLayout.totalScrollRange ->{
                            binding.toolbarHidden.visibility = View.VISIBLE
                            binding.toolbar.visibility = View.GONE
                            showToast("G")
                        }
                        else ->{
                            binding.toolbarHidden.visibility = View.VISIBLE
                            binding.toolbar.visibility = View.GONE
                        }
                    }
                }
            }
        })


        setContentView(binding.root)
    }
    private fun setbot(){
        DetailService(this).tryGetProducts()
    }

    private fun setDetail(productIdx:Int,jwt:String){
        DetailService(this).tryGetDetail(productIdx,jwt)
    }

    override fun onGetDetailSuccess(Response: detailData,productIdx: Int) {
        var response = Response.result
        var tags = response.tagList
        var tagtxt = ""
                tags.forEach {
                    tagtxt += "#${it.tagName} "
                }
                binding.tvSellerName.text = response.storeName
                binding.tvHashtag.text = tagtxt
                binding.tvTime.text = response.createdAt
                binding.likecount.text = response.cntLikes.toString()
                binding.tvTitle.text = response.productName
                binding.tvContent.text = response.content
                binding.tvPrice.text = "${response.prices.toString()}원"
                if (response.areaName != null){
                    binding.tvLoc.text = response.areaName.toString()
                }
                binding.tvCategory.text = response.subcategoryName
                Glide.with(binding.itemImg.context).load(response.imgList[0].imgUrl).into(binding.itemImg)

                if (response.conditions == "U"){
                    binding.tvUsed.text = "중고"
                }
                if(response.conditions == "N"){binding.tvUsed.text = "새상품"}

                if (response.freeShipping == "Y"){binding.tvFee.text="배송비 포함"}
                if (response.freeShipping == "N"){binding.tvFee.text="배송비 불포함"}

    }

    override fun onGetDetailFailure(message: String) {
        showToast("오류 : $message")
        Log.d("TAGG", message)
    }

    override fun onGetProductsSuccess(response: productItems) {
        var Result  = response.result
        SimilarAdapter = similarAdapter()
        SimilarAdapter.submitList(Result)
        binding.similerRcv.layoutManager = GridLayoutManager(this,3)
        binding.similerRcv.adapter = SimilarAdapter

        PlusAdapter = plusAdapter()
        PlusAdapter.submitList(Result)
        binding.powerRcv.layoutManager = GridLayoutManager(this,3)
        binding.powerRcv.adapter = PlusAdapter
    }

    override fun onGetProductsFailure(message: String) {
        showToast("오류 : $message")
    }
}