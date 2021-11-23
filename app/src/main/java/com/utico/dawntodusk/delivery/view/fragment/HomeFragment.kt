package com.utico.dawntodusk.delivery.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.adapter.HomeAdapter
import com.utico.dawntodusk.delivery.databinding.FragmentHomeBinding
import com.utico.dawntodusk.delivery.model.DeliveryPendingOrdersResponseModel
import com.utico.dawntodusk.delivery.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeAdapter: HomeAdapter
    private var deliveryBoyId:String? = null
    private lateinit var sharedPreferences: SharedPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        sharedPreferences = context?.getSharedPreferences(resources.getString(R.string.login_shared_data),Context.MODE_PRIVATE)!!
        deliveryBoyId = sharedPreferences.getString("deliveryBoyId","")
        val view: View = binding.root

      /*  viewModel.text.observe(viewLifecycleOwner, Observer {
        })*/
       initAdapter()
        fetchDeliveryBoyPendingOrders(deliveryBoyId!!)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initAdapter(){
       val recyclerView = binding.recyclerView
           recyclerView.layoutManager = LinearLayoutManager(activity)
           homeAdapter = HomeAdapter()
           recyclerView.adapter = homeAdapter
    }

    private fun fetchDeliveryBoyPendingOrders(delivery_boyId:String){
      viewModel.pendingOrderObservable().observe(viewLifecycleOwner, Observer<DeliveryPendingOrdersResponseModel> {
        if (it.userOrderDetails == null){
           Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
        }else {
            homeAdapter.deliveryPendingOrderList = it.userOrderDetails.toMutableList()
            homeAdapter.notifyDataSetChanged()
        }
      })
        viewModel.apiCallPendingOrders(delivery_boyId!!)
    }
}