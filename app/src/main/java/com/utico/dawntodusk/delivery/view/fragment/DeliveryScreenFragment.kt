package com.utico.dawntodusk.delivery.view.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.adapter.AdapterDeliveryScreen
import com.utico.dawntodusk.delivery.databinding.DeliveryScreenFragmentBinding
import com.utico.dawntodusk.delivery.model.OrderDetailsResponseModel
import com.utico.dawntodusk.delivery.view.AddFragmentToActivity
import com.utico.dawntodusk.delivery.viewmodel.DeliveryScreenViewModel

class DeliveryScreenFragment : Fragment() {
    private lateinit var adapterDeliveryScreen:AdapterDeliveryScreen
    private lateinit var binding: DeliveryScreenFragmentBinding
    private var customerContactNumber:String? = null
    private var managerContactNumber:String?  ="9535347309"
    private val REQUSET_PHONE_CALL=1
    private lateinit var viewModel: DeliveryScreenViewModel
    private var userName:String? = null
    private var orderId:String? = null
    private var addresses:String? = null
    private  var totalAmount:String? = null
    private var orderPlacedSharedPreferencesData: SharedPreferences? = null
    private var userId:String? = null


    companion object {
        fun newInstance() = DeliveryScreenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DeliveryScreenFragmentBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(DeliveryScreenViewModel::class.java)
        orderPlacedSharedPreferencesData = context?.getSharedPreferences(resources.getString(R.string.placed_order_user_data),Context.MODE_PRIVATE)
        userName = orderPlacedSharedPreferencesData?.getString("userName","")
        orderId = orderPlacedSharedPreferencesData?.getString("orderId","")
        addresses = orderPlacedSharedPreferencesData?.getString("address","")
        totalAmount = orderPlacedSharedPreferencesData?.getString("totalAmount","")
        userId = orderPlacedSharedPreferencesData?.getString("userId","")
        customerContactNumber = orderPlacedSharedPreferencesData?.getString("mobileNo","")

        binding.tvName.text = userName
        binding.tvOrderNumber.text ="Order No" +": " + orderId
        binding.tvAddress.text = addresses
        binding.tvCallToCustomer.text = customerContactNumber
        binding.tvTotalPrice.text = "AED"+" "+totalAmount


        val view : View =binding.root
        checkPhonePermission()
        initAdapter()
        buttonClickEvent()
        fetchUserOrderDetails(userId!!,orderId!!)
        return  view

    }

    private fun initAdapter(){
        val recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(activity)
            adapterDeliveryScreen = AdapterDeliveryScreen()
           recyclerView.adapter = adapterDeliveryScreen

    }

    private fun checkPhonePermission(){
        if(ActivityCompat.checkSelfPermission(activity as AppCompatActivity,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity as AppCompatActivity, arrayOf(Manifest.permission.CALL_PHONE),REQUSET_PHONE_CALL)
        }
        phoneCall()
    }

    private fun phoneCall(){
        binding.tvCallToCustomer.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:"+ customerContactNumber!!)
            context?.startActivity(callIntent)
        }
        binding.tvCallToManager.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:" + managerContactNumber!!)
            context?.startActivity(callIntent)
        }
    }

    private fun buttonClickEvent(){
        binding.imageViewGmap.setOnClickListener {
            val intent = Intent(context,AddFragmentToActivity::class.java)
            intent.putExtra("FragmentName","MapsFragment")
            context?.startActivity(intent)
        }

        binding.buttonConfirm.setOnClickListener {
          viewModel.orderConfirmObservable().observe(viewLifecycleOwner, Observer {
              Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
          })
          viewModel.apiCallOrderConfirm(userId!!,orderId!!)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUSET_PHONE_CALL)phoneCall()
    }

    private fun fetchUserOrderDetails(userid:String,orderid:String){
        Toast.makeText(context,userId,Toast.LENGTH_LONG).show()
        viewModel.userItemOrderObservable().observe(viewLifecycleOwner, Observer<OrderDetailsResponseModel> {
         if (it.statusCode==400){
             Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
         }else {
             adapterDeliveryScreen.userItemOrderList = it.DeliveryOrderDetails.toMutableList()
             adapterDeliveryScreen.notifyDataSetChanged()
         }
      })
      viewModel.apiCallUserItemOrders(userid!!,orderid!!)
    }

}