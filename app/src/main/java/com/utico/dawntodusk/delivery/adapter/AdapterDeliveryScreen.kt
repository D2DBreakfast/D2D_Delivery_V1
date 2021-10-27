package com.utico.dawntodusk.delivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.dawntodusk.delivery.databinding.ItemRowDeliveryBinding
import java.util.zip.Inflater

class AdapterDeliveryScreen:RecyclerView.Adapter<AdapterDeliveryScreen.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding = ItemRowDeliveryBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 3
    }

    class MyViewHolder(val binding: ItemRowDeliveryBinding) :RecyclerView.ViewHolder(binding.root)
}