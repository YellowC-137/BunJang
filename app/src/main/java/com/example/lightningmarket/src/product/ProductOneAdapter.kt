package com.example.lightningmarket.src.product

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lightningmarket.databinding.ItemProductOneBinding
import com.example.lightningmarket.src.detail.DetailActivity
import com.example.lightningmarket.src.product.models.Goods

class ProductOneAdapter: ListAdapter<Goods, ProductOneAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemProductOneBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(items: Goods){
            binding.like.text = items.cntLikes.toString()
            binding.tvPrice.text = items.prices.toString()
            binding.tvName.text = items.productName
            if (items.areaName != null){
                binding.tvLoc.text = items.areaName.toString()
            }
            binding.tvTime.text = items.createdAt.toString()
            Glide.with(binding.ivOne.context).load(items.imgUrl).into(binding.ivOne)
            binding.ivOne.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java).apply {
                    putExtra("productIdx",items.productIdx)
                }.run { binding.root.context.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductOneBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Goods>() {
            override fun areItemsTheSame(
                oldItem: Goods,
                newItem: Goods
            ): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(
                oldItem: Goods,
                newItem: Goods
            ): Boolean {
                return oldItem.productIdx == newItem.productIdx
            }

        }
    }
}