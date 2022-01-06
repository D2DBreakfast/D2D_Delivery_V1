package com.utico.dawntodusk.delivery.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.adapter.HomeAdapter
import com.utico.dawntodusk.delivery.databinding.FragmentHomeBinding
import com.utico.dawntodusk.delivery.model.DeliveryOrder
import com.utico.dawntodusk.delivery.model.DeliveryOrderListResponse
import com.utico.dawntodusk.delivery.viewmodel.HomeViewModel
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeAdapter: HomeAdapter
    private var deliveryBoyId:String? = null
    private var sectorId:String? = null
    private lateinit var sharedPreferences: SharedPreferences
    private var orderList = mutableListOf<DeliveryOrder>()

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
        sectorId =sharedPreferences.getString("sectorId","")
        val view: View = binding.root

      /*  viewModel.text.observe(viewLifecycleOwner, Observer {
        })*/
        initAdapter()
        fetchDeliveryBoyPendingOrders(deliveryBoyId!!)
        clickEvent()
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

    private fun clickEvent(){
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // search(query)
                //  Toast.makeText(context,query,Toast.LENGTH_LONG).show()
                return true
            }
            override fun onQueryTextChange(searchText: String): Boolean {
                // search(newText)
                //Toast.makeText(context,searchText,Toast.LENGTH_LONG).show()
                  searchFilter(searchText)
                return true
            }
        })/*getting the Search Text*/
    }

    private fun searchFilter(searchText:String){
        var orderFilterList = mutableListOf<DeliveryOrder>()
        for (data in orderList){
          if (data.orderId.toLowerCase(Locale.getDefault()).contains(searchText) ||
              data.userName.toLowerCase(Locale.getDefault()).contains(searchText) ||
              data.landMark.toLowerCase(Locale.getDefault()).contains(searchText)){
              orderFilterList.add(data)
              homeAdapter.orderList = orderFilterList
              homeAdapter.notifyDataSetChanged()
          }
        }
    }/*Search filter*/

    private fun fetchDeliveryBoyPendingOrders(delivery_boyId:String){
      viewModel.pendingOrderObservable().observe(viewLifecycleOwner, Observer<DeliveryOrderListResponse> {
        if (it.statusCode == 400){
           Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
        }else {
            homeAdapter.orderList = it.DeliveryOrderList.toMutableList()
            orderList = it.DeliveryOrderList.toMutableList()
            homeAdapter.notifyDataSetChanged()
        }
      })
        viewModel.apiCallPendingOrders(delivery_boyId!!,sectorId!!)
    }
}