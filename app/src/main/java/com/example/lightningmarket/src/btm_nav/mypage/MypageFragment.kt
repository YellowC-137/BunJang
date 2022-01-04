package com.example.lightningmarket.src.btm_nav.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.lightningmarket.R
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.BaseFragment
import com.example.lightningmarket.databinding.BottomsheetSettingBinding
import com.example.lightningmarket.databinding.FragmentMypageBinding
import com.example.lightningmarket.databinding.FragmentOnSaleBinding
import com.example.lightningmarket.src.btm_nav.mypage.models.ProfileData
import com.example.lightningmarket.src.btm_nav.mypage.options.OptionsActivity
import com.example.lightningmarket.src.btm_nav.mypage.setting.SettingActivity
import com.example.lightningmarket.src.btm_nav.mypage.sub.BlankFragment
import com.example.lightningmarket.src.btm_nav.mypage.sub.OnSaleFragment
import com.example.lightningmarket.src.btm_nav.mypage.sub.ReserveFragment
import com.example.lightningmarket.src.detail.models.detailData
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MypageFragment : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::bind,R.layout.fragment_mypage),MypageView{
    lateinit var BottomsheetLayout: View
    private lateinit var tabLayout: TabLayout
    lateinit var SaleBinding : FragmentOnSaleBinding
    val tabTexts = arrayListOf("판매중","예약중","판매완료")
    private lateinit var viewPager2: ViewPager2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = SP.getInt("useridx",30)

        viewPager2 = binding.vp2Mypage
        tabLayout = binding.tab

        SaleBinding = FragmentOnSaleBinding.inflate(layoutInflater)
        SaleBinding.empty.visibility = View.GONE
        SaleBinding.on.visibility = View.VISIBLE

        setfragment()
        getprofile(index)

        binding.tosetting.setOnClickListener {
            val intent = Intent(activity,OptionsActivity::class.java)
            startActivity(intent)
        }

        binding.setting.setOnClickListener {
            var SettingDialog = activity?.let { BottomSheetDialog(it) }
            val SettingBinding = BottomsheetSettingBinding.inflate(layoutInflater)
            if (SettingDialog != null) {
                SettingDialog.setContentView(SettingBinding.root)
                SettingDialog.setCanceledOnTouchOutside(true)
                SettingDialog.create()
                SettingDialog.show()
                SettingBinding.setting.setOnClickListener {
                    val intent = Intent(activity, SettingActivity::class.java)
                    startActivity(intent)
                }
            }
        }


    }


    private fun setfragment(){
        val pagerAdapter = ViewPagerAdapter(requireActivity())
        pagerAdapter.addFragment(OnSaleFragment())
        pagerAdapter.addFragment(ReserveFragment())
        pagerAdapter.addFragment(BlankFragment())
        viewPager2.adapter = pagerAdapter
        viewPager2?.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
        TabLayoutMediator(tabLayout,viewPager2){
                tab,position ->
            tab.text = tabTexts[position]
        }.attach()

    }


    private fun getprofile(idx:Int){
        MypageService(this).tryGetProfile(idx)
    }

    override fun onGetDetailSuccess(response: detailData, productIdx: Int) {

    }

    override fun onGetDetailFailure(message: String) {

    }

    override fun onGetProfileSuccess(response: ProfileData) {
        binding.tvNickname.text = response.result.storeName.toString()
        response.message?.let { showCustomToast(response.message.toString()) }
    }

    override fun onGetProfileFailure(message: String) {
        message?.let { showCustomToast(message.toString()) }
    }

    override fun onPatchProfileSuccess(response: ProfileData) {
        response.message?.let { showCustomToast(response.message.toString()) }
    }

    override fun onPatchProfileFailure(message: String) {
        message?.let { showCustomToast(message.toString()) }
    }

}