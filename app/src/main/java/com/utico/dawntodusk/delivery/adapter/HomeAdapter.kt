package com.utico.dawntodusk.delivery.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.dawntodusk.delivery.databinding.ItemRowHomeBinding
import com.utico.dawntodusk.delivery.view.AddFragmentToActivity
import java.time.Instant

class HomeAdapter :RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowHomeBinding.inflate(inflater,parent,false)

           binding.cardView.setOnClickListener {
               val intent = Intent((parent.context),AddFragmentToActivity::class.java)
                   intent.putExtra("FragmentName","DeliveryScreenFragment")
               (parent.context).startActivity(intent)
           }

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }

    class MyViewHolder(val binding: ItemRowHomeBinding):RecyclerView.ViewHolder(binding.root)

}