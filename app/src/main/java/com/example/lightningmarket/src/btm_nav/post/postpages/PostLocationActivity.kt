package com.example.lightningmarket.src.btm_nav.post.postpages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityPostLocationBinding

class PostLocationActivity : BaseActivity<ActivityPostLocationBinding>(ActivityPostLocationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}