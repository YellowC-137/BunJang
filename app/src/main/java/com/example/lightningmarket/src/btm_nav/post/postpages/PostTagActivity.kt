package com.example.lightningmarket.src.btm_nav.post.postpages

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityPostTagBinding

class PostTagActivity : BaseActivity<ActivityPostTagBinding>(ActivityPostTagBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.chk.setOnClickListener {
            val editor : SharedPreferences.Editor = ApplicationClass.SP.edit()
            editor.putString("posttag",binding.et.text.toString())
            editor.apply()
            finish()
        }

        setContentView(binding.root)
    }
}