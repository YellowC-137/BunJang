package com.example.lightningmarket.src.btm_nav.post.postpages

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityPostCategoryBinding

class PostCategoryActivity : BaseActivity<ActivityPostCategoryBinding>(ActivityPostCategoryBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.sneakersCategory.setOnClickListener {
            val editor : SharedPreferences.Editor = SP.edit()
            editor.putInt("postcategory",1)
            editor.apply()
            finish()
        }
    }
}