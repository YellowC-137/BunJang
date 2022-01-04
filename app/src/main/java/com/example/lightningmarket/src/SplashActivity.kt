package com.example.lightningmarket.src

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivitySplashBinding
import com.example.lightningmarket.src.Login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val SPLASH_VIEW_TIME: Long = 2000 //일단은 2초

        Handler().postDelayed({

            //loginchk()
            startActivity(Intent(this, LoginActivity::class.java))
        }, SPLASH_VIEW_TIME)

        setContentView(binding.root)
    }
    private fun loginchk() {
        //추후에 자동로그인 기능 추가 필요!
        val jwtToken: String? = SP.getString(ApplicationClass.X_ACCESS_TOKEN, null)
        if (jwtToken != null){
            startActivity(Intent(this, MainActivity::class.java))
            Log.d("LOGIN_PASS", jwtToken)
            finish()
        }
        else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}