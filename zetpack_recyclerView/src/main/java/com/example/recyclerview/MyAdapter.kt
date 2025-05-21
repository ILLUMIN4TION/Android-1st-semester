package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemListBinding

class MyAdapter(private val items:List<String>) : RecyclerView.Adapter<MyViewHolder>() {


    //껍데기, 공간을 만드는 역할
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    //실제로 데이터를 껍데기에 붙여주는 역할
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])


    }

    //저장한 리스트의 사이즈만큼 화면에 출력하기 위함,
    override fun getItemCount(): Int {
        return items.size
    }

}