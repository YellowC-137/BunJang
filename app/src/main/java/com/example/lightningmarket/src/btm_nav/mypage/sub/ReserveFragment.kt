package com.example.lightningmarket.src.btm_nav.mypage.sub

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.lightningmarket.R
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.BaseFragment
import com.example.lightningmarket.databinding.FragmentReserveBinding
import com.example.lightningmarket.src.btm_nav.mypage.MypageService
import com.example.lightningmarket.src.btm_nav.mypage.MypageView
import com.example.lightningmarket.src.btm_nav.mypage.models.ProfileData
import com.example.lightningmarket.src.detail.models.detailData


class ReserveFragment : BaseFragment<FragmentReserveBinding>(FragmentReserveBinding::bind,R.layout.fragment_reserve),
    MypageView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buy = SP.getInt("mypurchase",0)
        val jwt = SP.getString(ApplicationClass.X_ACCESS_TOKEN,"")
        Log.d("TEST", "${buy} , 구매")

            binding.empty.visibility = View.VISIBLE
            binding.on.visibility = View.GONE

    }

    private fun setitem(buy:Int,jwt:String){
        Log.d("TEST", "구매 예약")
        MypageService(this).tryGetDetail(buy,jwt)
    }

    override fun onGetDetailSuccess(response: detailData, productIdx: Int) {
        Glide.with(binding.ivItem.context).load(response.result.imgList[0]).into(binding.ivItem)
        binding.tvSeller.text = response.result.productName
        binding.tvPrice.text = response.result.prices.toString()
        binding.on.visibility = View.VISIBLE
        binding.empty.visibility = View.GONE
    }

    override fun onGetDetailFailure(message: String) {

    }

    override fun onGetProfileSuccess(response: ProfileData) {

    }

    override fun onGetProfileFailure(message: String) {

    }

    override fun onPatchProfileSuccess(response: ProfileData) {

    }

    override fun onPatchProfileFailure(message: String) {

    }

}