package com.utico.dawntodusk.delivery.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.databinding.ItemRowHomeBinding
import com.utico.dawntodusk.delivery.model.UserOrderDetail
import com.utico.dawntodusk.delivery.view.AddFragmentToActivity

class HomeAdapter :RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    var deliveryPendingOrderList = mutableListOf<UserOrderDetail>()
    private var context:Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowHomeBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val sharedPreferences = context?.getSharedPreferences(context?.resources?.getString(R.string.placed_order_user_data), Context.MODE_PRIVATE)
         val editor = sharedPreferences?.edit()
         holder.bind(deliveryPendingOrderList[position])
         holder.binding.cardView.setOnClickListener {
            val intent = Intent((context),AddFragmentToActivity::class.java)
             intent.putExtra("FragmentName","DeliveryScreenFragment")
             editor?.putString("userId",deliveryPendingOrderList[position].userId)
             editor?.putString("orderId",deliveryPendingOrderList[position].orderId)
             editor?.putString("phone",deliveryPendingOrderList[position].phone)
             editor?.commit()
             context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return deliveryPendingOrderList.size
    }

    class MyViewHolder(val binding: ItemRowHomeBinding):RecyclerView.ViewHolder(binding.root){
         val tvTitleOrderNo = binding.tvTitleOderNumber
        val tvTitleName = binding.tvTitleName
        val tvAddress = binding.tvAddress
        fun bind(data:UserOrderDetail){
            tvTitleOrderNo.text = data.orderId
            tvTitleName.text = data.userName
            tvAddress.text =data.address
        }

    }

}