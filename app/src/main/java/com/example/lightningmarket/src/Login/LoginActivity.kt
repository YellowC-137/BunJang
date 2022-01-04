package com.example.lightningmarket.src.Login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.lightningmarket.R
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityLoginBinding
import com.example.lightningmarket.databinding.BottomsheetLoginBinding
import com.example.lightningmarket.src.Login.Sign.SignActivity
import com.example.lightningmarket.src.MainActivity
import com.example.lightningmarket.src.Register.RegisterActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private var currentPosition = Int.MAX_VALUE / 4
    private var myHandler = MyHandler()
    private val intervalTime = 3000.toLong()
    lateinit var BottomsheetLayout: View
    var loginViewPagerData = arrayListOf<LoginViewPagerData>()
    private lateinit var loginAdapter: LoginAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tempKakao.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        
        binding.tvOtherlogin.setOnClickListener { 
            //바텀올라오고 본인인증으로 시작하기
            var LoginDialog = BottomSheetDialog(this)
            val LoginBinding = BottomsheetLoginBinding.inflate(layoutInflater)
            LoginDialog.setContentView(LoginBinding.root)
            LoginDialog.setCanceledOnTouchOutside(true)
            LoginBinding.tvPhone.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            LoginBinding.tvLogin.setOnClickListener {
                val intent = Intent(this,SignActivity::class.java)
                startActivity(intent)
            }

            LoginDialog.create()
            LoginDialog.show()
        }



        initvp()
        loginAdapter = LoginAdapter()
        loginAdapter.vpitem = loginViewPagerData
        binding.vp2Login.adapter = loginAdapter
        binding.springDot.setViewPager2(binding.vp2Login)
        binding.vp2Login.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vp2Login.setCurrentItem(currentPosition,false)
        binding.vp2Login.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {

                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)

                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

    }

    private fun initvp(){
        loginViewPagerData.apply {
            add(0, LoginViewPagerData("취향을 잇는 거래,\n번개장터","요즘 유행하는 메이저 취향부터\n나만 알고싶은 마이너 취향까지",R.drawable.login1))
            add(1,LoginViewPagerData("안전하게\n취향을 잇습니다.","번개톡, 번개페이로\n거래의 시작부터 끝까지 안전하게",R.drawable.login2))
            add(2,LoginViewPagerData("편리하게\n취향을 잇습니다.","포장택배 서비스로\n픽업/포장/배송을 한번에",R.drawable.login3))
            add(3,LoginViewPagerData("번개장터에서\n취향을 거래해보세요.","지금 바로 번개장터에서\n당신의 취향에 맞는 아이템들을 찾아보세요!",R.drawable.login4))

        }
                }

    private fun autoScrollStart(intervalTime: Long) {
        myHandler.removeMessages(0)
        myHandler.sendEmptyMessageDelayed(0, intervalTime)
    }

    private fun autoScrollStop(){
        myHandler.removeMessages(0)
    }

    private inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if(msg.what == 0) {
                binding.vp2Login.setCurrentItem(++currentPosition, true)
                autoScrollStart(intervalTime)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        autoScrollStart(intervalTime)
    }
    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

}