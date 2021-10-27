package com.utico.dawntodusk.delivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.adapter.AdapterDeliveryScreen
import com.utico.dawntodusk.delivery.databinding.DeliveryScreenFragmentBinding
import com.utico.dawntodusk.delivery.viewmodel.DeliveryScreenViewModel

class DeliveryScreenFragment : Fragment() {
    private lateinit var adapterDeliveryScreen:AdapterDeliveryScreen
    private lateinit var binding: DeliveryScreenFragmentBinding

    companion object {
        fun newInstance() = DeliveryScreenFragment()
    }

    private lateinit var viewModel: DeliveryScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DeliveryScreenFragmentBinding.inflate(inflater,container,false)
        val view : View =binding.root
        initAdapter()
        return  view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeliveryScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun initAdapter(){
        val recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(activity)
            adapterDeliveryScreen = AdapterDeliveryScreen()
           recyclerView.adapter = adapterDeliveryScreen

    }

}