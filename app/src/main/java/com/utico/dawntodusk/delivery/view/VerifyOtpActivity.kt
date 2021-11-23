package com.utico.dawntodusk.delivery.view

import `in`.aabhasjindal.otptextview.OTPListener
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.databinding.ActivityVerifyotpBinding
import com.utico.dawntodusk.delivery.model.VerifyOTPResponseModel
import com.utico.dawntodusk.delivery.viewmodel.VerifyOtpViewModel


class VerifyOtpActivity : AppCompatActivity(){
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityVerifyotpBinding
    private lateinit var viewModel:VerifyOtpViewModel
    private var mobileNo:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_verifyotp)
        viewModel = ViewModelProviders.of(this).get(VerifyOtpViewModel::class.java)
        sharedPreferences = getSharedPreferences(resources.getString(R.string.login_shared_data),Context.MODE_PRIVATE)
        mobileNo = sharedPreferences.getString("mobileNo","")
        binding.verifyOtpModel = viewModel
        viewModel.mobileNo = mobileNo //Pass the Mobile Number to the Model Class
        setUIClickEvent()
    }

    private fun setUIClickEvent(){
        binding.buttonVerify.setOnClickListener {
            viewModel.verifyOtpObservable().observe(this, Observer<VerifyOTPResponseModel> {
               if (it.statusCode == 200){
                   Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                   val intent = Intent(this,DashboardActivity::class.java)
                       startActivity(intent)
               }
            })
            viewModel.verifyOtpApiCall()
        }

       var otpview = binding.otpView
        otpview?.requestFocusOTP()
        otpview?.otpListener = object : OTPListener {
            override fun onInteractionListener() {
            }
            override fun onOTPComplete(otp: String) {
              viewModel.otp = otp
            }
        }
    }

}