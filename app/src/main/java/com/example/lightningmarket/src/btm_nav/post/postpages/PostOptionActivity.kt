package com.example.lightningmarket.src.btm_nav.post.postpages

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityPostOptionBinding

class PostOptionActivity : BaseActivity<ActivityPostOptionBinding>(ActivityPostOptionBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var newold = 0
        var fund = 0

        binding.newold.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                0 -> newold = 1
                else -> newold =2
            }
        }
        binding.fundable.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                0 -> fund = 1
                else -> fund = 2
            }
        }

        binding.btn.setOnClickListener {
            val editor : SharedPreferences.Editor = ApplicationClass.SP.edit()
            editor.putString("postquantity",binding.etQuantity.text.toString())
            editor.putInt("postused",newold)
            editor.putInt("postfund",fund)
            editor.apply()
            finish()
        }

    }
}