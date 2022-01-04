package com.example.lightningmarket.src.btm_nav.talk

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lightningmarket.R
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.config.BaseFragment
import com.example.lightningmarket.databinding.FragmentTalkBinding

class TalkFragment : BaseFragment<FragmentTalkBinding>(FragmentTalkBinding::bind,R.layout.fragment_talk) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myproduct = ApplicationClass.SP.getInt("myproduct",0)
        Log.d("TALK", "onViewCreated: {$myproduct}")
        if (myproduct == 0){
            binding.no.visibility = View.VISIBLE
            binding.yes.visibility = View.GONE
        }
        else{
            binding.no.visibility = View.GONE
            binding.yes.visibility = View.VISIBLE
        }
    }


}