package com.example.lightningmarket.src.btm_nav.mypage.setting

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivitySettingBinding
import com.example.lightningmarket.databinding.BottomsheetFlagBinding
import com.example.lightningmarket.databinding.BottomsheetRefundBinding
import com.example.lightningmarket.databinding.BottomsheetStoreintroBinding
import com.example.lightningmarket.src.btm_nav.mypage.MypageService
import com.example.lightningmarket.src.btm_nav.mypage.MypageView
import com.example.lightningmarket.src.btm_nav.mypage.models.PatchProfile
import com.example.lightningmarket.src.btm_nav.mypage.models.ProfileData
import com.example.lightningmarket.src.detail.models.detailData
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class SettingActivity : BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate),MypageView {
    lateinit var BottomsheetLayout: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idx = SP.getInt("useridx",30)
        var timeflag = 0
        val jwtToken: String? = SP.getString(ApplicationClass.X_ACCESS_TOKEN, null)

        setprofile(idx)

        binding.tvTime1.setOnClickListener {
            val cal = Calendar.getInstance()
            var timeString : String
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}시 ${minute}분"
                binding.tvTime1.text = timeString
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show()
        }

        binding.tvTime2.setOnClickListener {
            val cal = Calendar.getInstance()
            var timeString : String
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}시 ${minute}분"
                binding.tvTime2.text = timeString
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show()
        }





        binding.chk.setOnClickListener {
            if (jwtToken != null) {
                newprofile(idx,jwtToken)
            }
        }

        binding.tvTobefore.setOnClickListener {
            var beforeDialog = BottomSheetDialog(this)
            val flagBinding = BottomsheetFlagBinding.inflate(layoutInflater)

            beforeDialog.setCanceledOnTouchOutside(true)
            flagBinding.finishBtn.setOnClickListener {
                binding.tvTobefore.text = flagBinding.tvIntro.text.toString()
            }
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val text = p0.toString()
                    flagBinding.count.text = "${p0?.length?.toInt()}/1000"
                }

                override fun afterTextChanged(E: Editable?) {
                    val text = E.toString()
                    flagBinding.count.text = "${text.length}/1000"
                }
            }
            flagBinding.tvIntro.addTextChangedListener(textWatcher)
            beforeDialog.setContentView(flagBinding.root)
            beforeDialog.create()
            beforeDialog.show()
        }
        binding.tvTorefund.setOnClickListener {
            var refundDialog = BottomSheetDialog(this)
            val refundBinding = BottomsheetRefundBinding.inflate(layoutInflater)

            refundDialog.setCanceledOnTouchOutside(true)
            refundBinding.finishBtn.setOnClickListener {
                binding.tvTorefund.text = refundBinding.tvIntro.text.toString()
            }
            val textWatcher = object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val text = p0.toString()
                    refundBinding.count.text  = "${p0?.length?.toInt()}/1000"
                }

                override fun afterTextChanged(E: Editable?) {
                    val text = E.toString()
                    refundBinding.count.text = "${text.length}/1000"
                }
            }
            refundBinding.tvIntro.addTextChangedListener(textWatcher)
            refundDialog.setContentView(refundBinding.root)
            refundDialog.create()
            refundDialog.show()
        }
        binding.tvTointro.setOnClickListener {
            var introDialog = BottomSheetDialog(this)
            val introBinding = BottomsheetStoreintroBinding.inflate(layoutInflater)

            introDialog.setCanceledOnTouchOutside(true)
            introBinding.finishBtn.setOnClickListener {
                binding.tvTointro.text = introBinding.tvIntro.toString()
            }
            val textWatcher = object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    introBinding.count.text = "${p0?.length?.toInt()}/1000"
                }

                override fun afterTextChanged(E: Editable?) {
                    val text = E.toString()
                    introBinding.count.text = "${text.length}/1000"
                }
            }

            introBinding.tvIntro.addTextChangedListener(textWatcher)
            introDialog.setContentView(introBinding.root)
            introDialog.create()
            introDialog.show()
        }




        setContentView(binding.root)
    }

    private fun setprofile(idx:Int){
        //가져오기
        MypageService(this).tryGetProfile(idx)
    }

    private fun newprofile(idx:Int,jwtToken:String){
        //patch
        val time = "${binding.tvTime1.text} ~ ${binding.tvTime2.text}"
        val patchProfile = PatchProfile(
            "https://profile1",
            storeName = binding.etName.text.toString(),
            storeAddress ="http://shop.bunjang.co.kr/",
            contactableTime = time,
            storeIntro = binding.tvTointro.text.toString(),
            tradePolicy = binding.tvTorefund.text.toString(),
            flag = binding.tvTobefore.text.toString()
        )
        MypageService(this).tryPatchProfile(idx,jwtToken,patchProfile)
    }

    override fun onGetDetailSuccess(response: detailData, productIdx: Int) {

    }

    override fun onGetDetailFailure(message: String) {

    }

    override fun onGetProfileSuccess(response: ProfileData) {
        response.message?.let { showToast(response.message.toString()) }

        binding.etName.setText(response.result.storeName.toString())
        if (response.result.storeIntro != null)
        {
            binding.tvTointro.text = response.result.storeIntro.toString()
        }
        if (response.result.tradePolicy != null)
        {
            binding.tvTorefund.text = response.result.tradePolicy.toString()
        }
        if (response.result.flag!= null)
        {
            binding.tvTobefore.text =response.result.flag.toString()
        }

        binding.tvTimeTemp.text = response.result.contactableTime.toString()


    }

    override fun onGetProfileFailure(message: String) {
        message?.let { showToast(message.toString()) }
    }

    override fun onPatchProfileSuccess(response: ProfileData) {
        response.message?.let { showToast(response.message.toString()) }
        binding.etName.setText(response.result.storeName.toString())
        binding.tvTimeTemp.text = response.result.contactableTime.toString()
        binding.tvTointro.text = response.result.storeIntro.toString()
        binding.tvTorefund.text = response.result.tradePolicy.toString()
        binding.tvTobefore.text =response.result.flag.toString()
        finish()

    }

    override fun onPatchProfileFailure(message: String) {
        //message?.let { showToast(message.toString())
        Log.d("FAIL", message)
        finish()
    }
}
