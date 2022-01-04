package com.example.lightningmarket.src.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lightningmarket.databinding.ItemDetailSimilarBinding
import com.example.lightningmarket.src.productItem.Result

class similarAdapter : ListAdapter<Result,similarAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemDetailSimilarBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(items:Result){
                    binding.tvPrice.text = "${items.prices.toString()}원"
                    binding.tvSimilarContent.text = items.productName.toString()
                    Glide.with(binding.ivOther.context).load(items.imgList[0].imgUrl).into(binding.ivOther)
                    binding.ivOther.setOnClickListener {
                        val intent = Intent(binding.root.context,DetailActivity::class.java).apply {
                            putExtra("productIdx",items.productIdx)
                        }.run { binding.root.context.startActivity(this) }
                    }
                }
            }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDetailSimilarBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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