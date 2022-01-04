package com.example.lightningmarket.src.btm_nav.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lightningmarket.R

class AdViewPagerAdapter(bannerList: ArrayList<Int>) :
    RecyclerView.Adapter<AdViewPagerAdapter.PagerViewHolder>() {
    var item = bannerList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = Int.MAX_VALUE	// 아이템의 갯수를 임의로 확 늘린다.

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.banner.setImageResource(item[position%item.size])

    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_home_ad, parent, false)){
        val banner : ImageView = itemView.findViewById(R.id.iv_HomeBanner)
    }

}