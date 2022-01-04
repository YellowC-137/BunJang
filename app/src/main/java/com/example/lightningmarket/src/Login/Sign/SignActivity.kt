package com.example.lightningmarket.src.Login.Sign

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivitySignBinding
import com.example.lightningmarket.src.Login.Sign.models.LoginResponse
import com.example.lightningmarket.src.Login.Sign.models.PostLoginRequest
import com.example.lightningmarket.src.MainActivity
import com.example.lightningmarket.src.Register.models.UserResponse

class SignActivity : BaseActivity<ActivitySignBinding>(ActivitySignBinding::inflate) , SignView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            login()
        }

        setContentView(binding.root)
    }
    private fun login(){
        val loginRequest = PostLoginRequest(
            email = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )
        SignService(this).tryPostLogin(loginRequest)

    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        response.message?.let { showToast(response.message.toString()) }
        val editor : SharedPreferences.Editor = ApplicationClass.SP.edit()
        val jwt = response.result.jwt
        editor.putString(ApplicationClass.X_ACCESS_TOKEN,jwt)
        editor.putInt("useridx",response.result.userIdx)
        editor.apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onPostLoginFailure(message: String) {
        Log.d("FAIL", "$message")
        showToast("오류 : $message")
        finish()
    }
}