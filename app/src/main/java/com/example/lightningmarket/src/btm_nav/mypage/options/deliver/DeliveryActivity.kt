package com.example.lightningmarket.src.btm_nav.mypage.options.deliver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityDeliveryBinding

class DeliveryActivity : BaseActivity<ActivityDeliveryBinding>(ActivityDeliveryBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}