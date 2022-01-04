package com.example.lightningmarket.src.btm_nav.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}