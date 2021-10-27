package com.utico.dawntodusk.delivery.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.dawntodusk.delivery.adapter.HomeAdapter
import com.utico.dawntodusk.delivery.databinding.FragmentHomeBinding
import com.utico.dawntodusk.delivery.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeAdapter: HomeAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view: View = binding.root

      /*  viewModel.text.observe(viewLifecycleOwner, Observer {
        })*/
       initAdapter()
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
}