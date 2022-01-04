package com.example.lightningmarket.src.btm_nav.post.postpages

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityPostDescriptBinding
import com.example.lightningmarket.src.detail.DetailActivity

class PostDescriptActivity : BaseActivity<ActivityPostDescriptBinding>(ActivityPostDescriptBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.et.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.cnt.text = "${binding.et.text.length}/2000"
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.cnt.text = "${binding.et.text.length}/2000"
            }

        })

        binding.tvFinish.setOnClickListener {
            val editor : SharedPreferences.Editor = ApplicationClass.SP.edit()
            editor.putString("postcontent",binding.et.text.toString())
            editor.apply()
            finish()
        }
        setContentView(binding.root)
    }
}