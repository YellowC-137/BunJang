package com.example.lightningmarket.src.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lightningmarket.databinding.ItemDetailPlusBinding
import com.example.lightningmarket.src.productItem.Result

class plusAdapter : ListAdapter<Result, plusAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemDetailPlusBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(items: Result){
            binding.tvName.text = items.productName.toString()
            binding.tvPrice.text = "${items.prices.toString()}원"
            binding.tvBrand.text = items.storeName
            Glide.with(binding.ivPlus.context).load(items.imgList[0].imgUrl).into(binding.ivPlus)
            binding.ivPlus.setOnClickListener {
                val intent = Intent(binding.root.context,DetailActivity::class.java).apply {
                    putExtra("productIdx",items.productIdx)
                }.run { binding.root.context.startActivity(this) }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDetailPlusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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