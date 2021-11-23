package com.utico.dawntodusk.delivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.dawntodusk.delivery.databinding.ItemRowDeliveryBinding
import com.utico.dawntodusk.delivery.model.UserOrderItem
import java.util.zip.Inflater

class AdapterDeliveryScreen:RecyclerView.Adapter<AdapterDeliveryScreen.MyViewHolder>(){
    var userItemOrderList = mutableListOf<UserOrderItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding = ItemRowDeliveryBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userItemOrderList[position])
    }

    override fun getItemCount(): Int {
        return userItemOrderList.size
    }

    class MyViewHolder(val binding: ItemRowDeliveryBinding) :RecyclerView.ViewHolder(binding.root){
        val tvTitle = binding.tvTitle
        val tvQuantity = binding.tvQuantity
        val tvTotalPrice =binding.tvTotalPrice

       fun bind(data:UserOrderItem) {
         tvTitle.text = data.itemName
         tvQuantity.text =data.itemQuantity
         tvTotalPrice.text =data.itemPrice
       }
    }
    /*check some  first comment*/
}