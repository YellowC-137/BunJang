package com.example.lightningmarket.src.btm_nav.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lightningmarket.R
import com.example.lightningmarket.databinding.ItemHomeBinding
import com.example.lightningmarket.src.detail.DetailActivity
import com.example.lightningmarket.src.productItem.Result

class HomeAdapter : ListAdapter<Result,HomeAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemHomeBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(items: Result){
                        binding.likeCount.text = items.cntLikes.toString()
                        binding.tvPrice.text = "${items.prices.toString()}원"
                        binding.tvDesc.text = items.productName
                    if (items.areaName !=null){
                        binding.tvLoc.text = items.areaName.toString()
                    }

                        binding.tvTime.text = items.createdAt
                        Glide.with(binding.ivItem.context).load(items.imgList[0].imgUrl).into(binding.ivItem)
                        binding.ivItem.setOnClickListener {
                            val intent = Intent(binding.root.context,DetailActivity::class.java).apply {
                                putExtra("productIdx",items.productIdx)
                            }.run { binding.root.context.startActivity(this) }
                        }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean {
                return oldItem.productIdx == newItem.productIdx
            }

        }
    }

}