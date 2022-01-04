package com.example.lightningmarket.src.Register

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityRegisterBinding
import com.example.lightningmarket.src.MainActivity
import com.example.lightningmarket.src.Register.models.PostRegisterRequest
import com.example.lightningmarket.src.Register.models.UserResponse

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate), RegisterView {
    private val EmailValidation =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressed()
        }
            binding.btnNext1.setOnClickListener {
                getbirth()
            }

    }

    private fun getbirth() {
        binding.constName.visibility = View.GONE
        binding.etPwName.text = binding.etName.text
        binding.constPw.visibility = View.VISIBLE
        binding.btnNext2.setOnClickListener {
                    getemail() }
    }
    private fun getemail(){
        binding.constPw.visibility = View.GONE
        binding.constEmail.visibility = View.VISIBLE
        binding.etEmailPw1.text = binding.etPw1.text
        binding.etEmailName.text = binding.etName.text
        binding.btnNext3.setOnClickListener{
                register() }


    }

    private fun register(){

        val postRequest = PostRegisterRequest(
            email = binding.etEmail.text.toString(),
            password = binding.etEmailPw1.text.toString(),
            storeName = binding.etEmailName.text.toString(),
            userType = 'E'
        )
        RegisterService(this).tryPostSignUp(postRequest)
    }

    override fun onPostSignUpSuccess(response: UserResponse) {
        response.message?.let { showToast(response.message.toString()) }
        val editor : SharedPreferences.Editor = SP.edit()
        val jwt = response.result.jwt
        editor.putString(X_ACCESS_TOKEN,jwt)
        editor.putInt("useridx",response.result.userIdx)
        editor.remove("myproduct")
        editor.apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onPostSignUpFailure(message: String) {
        showToast("오류 : $message")
        finish()
    }
}