package com.utico.dawntodusk.delivery.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.adapter.OrdersHistoryAdapter
import com.utico.dawntodusk.delivery.databinding.FragmentProfileBinding
import com.utico.dawntodusk.delivery.model.OrderHistoryResponseModel
import com.utico.dawntodusk.delivery.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private lateinit var ordersHistoryAdapter: OrdersHistoryAdapter
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
            viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
            _binding = FragmentProfileBinding.inflate(inflater, container, false)
           val view: View = binding.root

        initAdapter()
        clickEvent()
        getOrderHistory("Pending")
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   private fun initAdapter(){
        var recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(activity)
            ordersHistoryAdapter = OrdersHistoryAdapter()
            recyclerView.adapter = ordersHistoryAdapter
    }

    private fun clickEvent(){
     val lnrPendingOrders = binding.lnrPending
     val lnrCompletedOrders = binding.lnrComplete

        lnrPendingOrders.setOnClickListener {
            getOrderHistory("Pending")
            binding.tvTitle.text = context?.resources?.getString(R.string.pending_orders)
            binding.tvTitle.setTextColor(context?.resources!!.getColor(R.color.gold))
            binding.tvPendingOrderLabel.setTextColor(context?.resources!!.getColor(R.color.gold))
        }

        lnrCompletedOrders.setOnClickListener {
            getOrderHistory("Delivered")
            binding.tvTitle.text = context?.resources?.getString(R.string.completed_orders)
            binding.tvTitle.setTextColor(context?.resources!!.getColor(R.color.green))
            binding.tvCompletedOrderLabel.setTextColor(context?.resources!!.getColor(R.color.green))

        }

    }

 private fun getOrderHistory(orderStatus:String){
    viewModel.orderHistoryObservable().observe(viewLifecycleOwner, Observer<OrderHistoryResponseModel> {
     if (it.statusCode == 400){
         Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
         binding.tvOrderPendingCount.text ="0"
         binding.tvOrderCompletedCount.text = "0"
         ordersHistoryAdapter.orderHistoryList.clear()
         ordersHistoryAdapter.notifyDataSetChanged()
     }else{
         ordersHistoryAdapter.orderHistoryList = it.DeliveryOrderHistory.toMutableList()
         ordersHistoryAdapter.notifyDataSetChanged()
         if (orderStatus.equals("Pending")){
             binding.tvOrderPendingCount.text = it.OrderCount.toString()
             binding.tvOrderCompletedCount.text ="0"
         }else{
             binding.tvOrderCompletedCount.text = it.OrderCount.toString()
             binding.tvOrderPendingCount.text ="0"
         }
     }
    })
    viewModel.apiCallOrderHistory(orderStatus)
 }

}