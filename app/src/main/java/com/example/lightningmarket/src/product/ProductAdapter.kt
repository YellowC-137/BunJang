package com.example.lightningmarket.src.product

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lightningmarket.databinding.ItemDetailPlusBinding
import com.example.lightningmarket.databinding.ItemProductOneBinding
import com.example.lightningmarket.databinding.ItemProductThreeBinding
import com.example.lightningmarket.src.detail.DetailActivity
import com.example.lightningmarket.src.product.models.Goods
import com.example.lightningmarket.src.productItem.Result

class ProductAdapter : ListAdapter<Goods,ProductAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemProductThreeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(items: Goods){
            binding.tvPrice.text = items.prices.toString()
            binding.tvDesc.text = items.productName
            Glide.with(binding.ivItem.context).load(items.imgUrl).into(binding.ivItem)
            binding.ivItem.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java).apply {
                    putExtra("productIdx",items.productIdx)
                }.run { binding.root.context.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductThreeBinding.inflate(LayoutInflater.from(parent.context),parent,false))

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
                newItem:Goods
            ): Boolean {
                return oldItem.productIdx == newItem.productIdx
            }

        }
    }
}