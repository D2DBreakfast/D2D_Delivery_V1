package com.utico.dawntodusk.delivery.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.databinding.ItemRowHomeBinding
import com.utico.dawntodusk.delivery.model.DeliveryOrder
import com.utico.dawntodusk.delivery.view.AddFragmentToActivity

class HomeAdapter :RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    var orderList = mutableListOf<DeliveryOrder>()
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
         holder.bind(orderList[position])
         holder.binding.cardView.setOnClickListener {
            val intent = Intent((context),AddFragmentToActivity::class.java)
             intent.putExtra("FragmentName","DeliveryScreenFragment")
             editor?.putString("userName",orderList[position].userName)
             editor?.putString("orderId",orderList[position].orderId)
             editor?.putString("mobileNo",orderList[position].mobileNo)
             editor?.putString("address",orderList[position].landMark)
             editor?.putString("totalAmount",orderList[position].amount)
             editor?.putString("userId",orderList[position].userId)
             editor?.commit()
             context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    class MyViewHolder(val binding: ItemRowHomeBinding):RecyclerView.ViewHolder(binding.root){
         val tvTitleOrderNo = binding.tvTitleOderNumber
        val tvTitleName = binding.tvTitleName
        val tvAddress = binding.tvAddress
        fun bind(data:DeliveryOrder){
            tvTitleOrderNo.text = data.orderId
            tvTitleName.text = data.userName
            tvAddress.text =data.landMark
        }

    }

}