package com.utico.dawntodusk.delivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.dawntodusk.delivery.databinding.ItemRowDeliveredOrdersBinding

class DeliveredOrdersAdapter:RecyclerView.Adapter<DeliveredOrdersAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowDeliveredOrdersBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }

    class MyViewHolder(val binding: ItemRowDeliveredOrdersBinding):RecyclerView.ViewHolder(binding.root)
}