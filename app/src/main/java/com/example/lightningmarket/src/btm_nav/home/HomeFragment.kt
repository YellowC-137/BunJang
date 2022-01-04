package com.example.lightningmarket.src.btm_nav.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.lightningmarket.R
import com.example.lightningmarket.config.BaseFragment
import com.example.lightningmarket.databinding.FragmentHomeBinding
import com.example.lightningmarket.src.CategoryActivity
import com.example.lightningmarket.src.detail.DetailActivity
import com.example.lightningmarket.src.productItem.Result
import com.example.lightningmarket.src.productItem.productItems
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.abs


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind,R.layout.fragment_home),
HomeFragmentView{
    private var currentPosition = Int.MAX_VALUE / 2
    private var myHandler = MyHandler()
    private val intervalTime = 1500.toLong()
    private lateinit var homeAdapter: HomeAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (appBarLayout != null){
                    when{
                        verticalOffset==0 -> {
                          binding.toolbar.visibility = View.GONE
                            binding.notiTop.visibility = View.VISIBLE
                            binding.ivMenuTop.visibility = View.VISIBLE
                            binding.searchTop.visibility = View.VISIBLE
                             }
                        abs(verticalOffset)>=appBarLayout.totalScrollRange ->{
                            binding.toolbar.visibility = View.VISIBLE
                            binding.notiTop.visibility = View.GONE
                            binding.ivMenuTop.visibility = View.GONE
                            binding.searchTop.visibility = View.GONE
                        }
                        else -> { binding.toolbar.visibility =View.GONE
                            binding.notiTop.visibility = View.GONE
                            binding.ivMenuTop.visibility = View.GONE
                            binding.searchTop.visibility = View.GONE   }
                    }
                }
            }
        })

        setbanner()
        sethome()

        binding.test.setOnClickListener {
            startActivity(Intent(activity,DetailActivity::class.java))
        }

        binding.menu.setOnClickListener {
            val intent = Intent(activity,CategoryActivity::class.java)
            startActivity(intent)
        }
        binding.tvSneakers.setOnClickListener {
            showCustomToast("스니커즈")
        }


    }


    private fun setbanner(){
        binding.vp2Ad.adapter = AdViewPagerAdapter(getBannerList())
        binding.vp2Ad.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vp2Ad.setCurrentItem(currentPosition,false)
        binding.vp2Ad.apply {
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
                    binding.bannerCount.text = "${(position%10)+1}/10"
                }
            })
        }
    }

    private fun sethome(){
        HomeService(this).tryGetProducts()
    }

    override fun onGetProductsSuccess(response: productItems) {
        var Result  = response.result
        homeAdapter = HomeAdapter()
        homeAdapter.submitList(Result)
        binding.rcvHome.layoutManager = GridLayoutManager(activity,2)
        binding.rcvHome.adapter = homeAdapter
    }

    override fun onGetProductsFailure(message: String) {
        showCustomToast("오류 : $message")
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
                binding.vp2Ad.setCurrentItem(++currentPosition, true)
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

    fun getBannerList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3,R.drawable.banner4,R.drawable.banner5,R.drawable.banner6,R.drawable.banner7,R.drawable.banner8,R.drawable.banner9,R.drawable.banner10)
    }

}