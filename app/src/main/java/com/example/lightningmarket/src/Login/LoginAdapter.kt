package com.example.lightningmarket.src.Login

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lightningmarket.R
import com.example.lightningmarket.databinding.BottomsheetLoginBinding
import com.example.lightningmarket.databinding.ItemLoginViewpagerBinding

class LoginAdapter: RecyclerView.Adapter<LoginAdapter.PagerViewHolder>() {
    var vpitem = arrayListOf<LoginViewPagerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder{
        return PagerViewHolder(ItemLoginViewpagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = 4 	// 아이템의 갯수를 임의로 확 늘린다. Int.MAX_VALUE

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(vpitem[position])
    }

    inner class PagerViewHolder(private val binding: ItemLoginViewpagerBinding) :
        RecyclerView.ViewHolder
        (binding.root){
        fun bind(item : LoginViewPagerData){
            binding.tvTop.text = item.top
            binding.tvMid.text = item.mid
            Glide.with(binding.ivBot.context).load(item.img).into(binding.ivBot)
        }

    }

}