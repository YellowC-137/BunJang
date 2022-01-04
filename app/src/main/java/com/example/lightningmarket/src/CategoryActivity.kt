package com.example.lightningmarket.src

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lightningmarket.R
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityCategoryBinding
import com.example.lightningmarket.src.product.ProductActivity

class CategoryActivity : BaseActivity<ActivityCategoryBinding>(ActivityCategoryBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_up,R.anim.slide_down)



        binding.toSneakers.setOnClickListener {
            val intent = Intent(this,ProductActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}