package com.utico.dawntodusk.delivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.databinding.ItemRowDeliveredOrdersBinding
import com.utico.dawntodusk.delivery.model.DeliveryOrderHistory

class OrdersHistoryAdapter:RecyclerView.Adapter<OrdersHistoryAdapter.MyViewHolder>(){
    var orderHistoryList = mutableListOf<DeliveryOrderHistory>()
    var context:Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowDeliveredOrdersBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(orderHistoryList[position],context)
    }

    override fun getItemCount(): Int {
        return orderHistoryList.size
    }

    class MyViewHolder(val binding: ItemRowDeliveredOrdersBinding):RecyclerView.ViewHolder(binding.root){
        val tvOrderId = binding.tvOrderId
        val tvPaymentPaid = binding.tvPaymentPaid
        val tvOrderStatus = binding.tvOrderStatus
        val tvOrderDate = binding.tvOrderDate
        fun bind(data: DeliveryOrderHistory, context: Context?){
           tvOrderId.text = data.orderId
           tvPaymentPaid.text = "AED"+" "+data.paymentPaid
           tvOrderStatus.text =data.deliveryStatus
           if (data.deliveryStatus.equals("Pending")){
               tvOrderDate.text = data.orderDate
               tvOrderStatus.setTextColor(context!!.resources.getColor(R.color.gold))
           }else if (data.deliveryStatus.equals("Delivered")){
               tvOrderDate.text = data.deliveredDate
               tvOrderStatus.setTextColor(context!!.resources.getColor(R.color.green))
           }
        }

    }
}