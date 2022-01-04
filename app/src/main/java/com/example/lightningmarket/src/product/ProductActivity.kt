package com.example.lightningmarket.src.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lightningmarket.R
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityProductBinding
import com.example.lightningmarket.databinding.BottomsheetSortBinding
import com.example.lightningmarket.src.product.models.CategoryData
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.properties.Delegates

class ProductActivity : BaseActivity<ActivityProductBinding>(ActivityProductBinding::inflate),ProductView {
    private lateinit var jwt : String
    private var sort by Delegates.notNull<Int>()
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productOneAdapter: ProductOneAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        jwt = SP.getString("X-ACCESS-TOKEN","").toString()
        sort = 3
        category(sort)

        binding.tvSort.setOnClickListener {
            //바텀올라오고 본인인증으로 시작하기
            var sortDialog = BottomSheetDialog(this)
            val sortBinding = BottomsheetSortBinding.inflate(layoutInflater)
            sortDialog.setContentView(sortBinding.root)
            sortDialog.setCanceledOnTouchOutside(true)
            sortBinding.btnSet.setOnClickListener {
                sortDialog.dismiss()
                category(sort)
            }
            sortBinding.tvOne.setOnClickListener {
                sort = 1
                sortBinding.tvOne.setTextColor(getColor(R.color.main))
                sortBinding.tvOne.setBackgroundResource(R.drawable.sortbox)
                sortBinding.tvThree.setTextColor(getColor(R.color.black))
                sortBinding.tvThree.setBackgroundResource(R.drawable.box)
            }

            sortDialog.create()
            sortDialog.show()
        }
    }
    private fun category(sort:Int){
        ProductService(this).trygetCategory(1,jwt)
    }

    override fun onGetCategorySuccess(response: CategoryData) {
        val result = response.result
        val goods = result.goodsList
        if (sort == 3){
            productAdapter = ProductAdapter()
            productAdapter.submitList(goods)
            binding.recycleProduct.layoutManager = GridLayoutManager(this,sort)
            binding.recycleProduct.adapter = productAdapter
        }
        else{
            Log.d("TAGTAG", sort.toString())
            productOneAdapter = ProductOneAdapter()
            productOneAdapter.submitList(goods)
            binding.recycleProduct.layoutManager = LinearLayoutManager(this)
            binding.recycleProduct.adapter = productOneAdapter
        }


    }

    override fun onGetCategoryFailure(message: String) {
        showToast("오류 : $message")
    }
}