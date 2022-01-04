package com.example.lightningmarket.src.btm_nav.mypage.options

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityOptionsBinding
import com.example.lightningmarket.src.btm_nav.mypage.options.deliver.DeliveryActivity

class OptionsActivity : BaseActivity<ActivityOptionsBinding>(ActivityOptionsBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.deliverySetting.setOnClickListener {
            val intent = Intent(this,DeliveryActivity::class.java)
            startActivity(intent)
        }
        binding.logout.setOnClickListener {
            showToast("로그아웃 되었습니다.")
        }

        setContentView(binding.root)
    }
}