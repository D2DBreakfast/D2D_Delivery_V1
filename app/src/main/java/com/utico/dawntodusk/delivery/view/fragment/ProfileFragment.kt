package com.utico.dawntodusk.delivery.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.dawntodusk.delivery.adapter.DeliveredOrdersAdapter
import com.utico.dawntodusk.delivery.databinding.FragmentProfileBinding
import com.utico.dawntodusk.delivery.viewmodel.DashboardViewModel

class ProfileFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentProfileBinding? = null
    private lateinit var deliveredOrdersAdapter: DeliveredOrdersAdapter
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
           _binding = FragmentProfileBinding.inflate(inflater, container, false)
           val view: View = binding.root
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        setAdapter()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun setAdapter(){
        var recyclerView = binding.recyclerViewDeliveredOrders
            recyclerView.layoutManager = LinearLayoutManager(activity)
            deliveredOrdersAdapter = DeliveredOrdersAdapter()
            recyclerView.adapter = deliveredOrdersAdapter
    }
}