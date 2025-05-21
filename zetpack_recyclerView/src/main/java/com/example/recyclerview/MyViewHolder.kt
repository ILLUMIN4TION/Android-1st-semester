package com.example.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemListBinding

class MyViewHolder(val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(text:String){
        binding.tvItem.text = text
    }
}