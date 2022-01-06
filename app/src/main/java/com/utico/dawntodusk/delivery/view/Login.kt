package com.utico.dawntodusk.delivery.view

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
import com.utico.dawntodusk.delivery.databinding.ActivityLoginBinding
import com.utico.dawntodusk.delivery.model.LoginResponseModel
import com.utico.dawntodusk.delivery.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel:LoginViewModel
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        sharedPreferences =getSharedPreferences(resources.getString(R.string.login_shared_data),Context.MODE_PRIVATE)
        editor =sharedPreferences.edit()
        binding.loginModel = viewModel
        setButtonClickEvent()//Call button Click event
    }

    private fun setButtonClickEvent(){
               binding.buttonLogin.setOnClickListener {
                       viewModel.loginObservable().observe(this, Observer<LoginResponseModel> {
                     if (it.statusCode == 400){
                         Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                     }else{
                         editor.putString("deliveryBoyId",it.LoginData.deliveryBoyId)
                         editor.putString("fullName",it.LoginData.fullName)
                         editor.putString("mobileNo",it.LoginData.mobileNo)
                         editor.putString("sectorId",it.LoginData.sectorId)
                         editor.putString("adminId",it.LoginData.managerId)
                         editor.putString("idProof",it.LoginData.idProof)
                         editor.putString("registerDate",it.LoginData.registerDate)
                         editor.putString("otp",it.LoginData.mobileOtp)
                         editor.commit()
                         Toast.makeText(this,it.message +"OTP is "+it.LoginData.mobileOtp,Toast.LENGTH_SHORT).show()
                         val intent = Intent(this,VerifyOtpActivity::class.java)
                         startActivity(intent)
                     }
                   })
                   viewModel.loginApiCall()

                }
            }

    }
