package com.utico.dawntodusk.delivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.dawntodusk.delivery.model.VerifyOTPResponseModel
import com.utico.dawntodusk.delivery.retrofit.ApiService
import com.utico.dawntodusk.delivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerifyOtpViewModel : ViewModel() {
    var mobileNo:String? = null
    var otp:String? = null
    lateinit var verifyOtpResponseData:MutableLiveData<VerifyOTPResponseModel>


    init {
        verifyOtpResponseData = MutableLiveData()
    }


    fun verifyOtpObservable():MutableLiveData<VerifyOTPResponseModel>{
        return verifyOtpResponseData
    }


    fun verifyOtpApiCall(){
     val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
     val call = retroInstance.verifyOtp(mobileNo!!,otp!!)
         call.enqueue(object :Callback<VerifyOTPResponseModel>{
             override fun onResponse(call: Call<VerifyOTPResponseModel>, response: Response<VerifyOTPResponseModel>) {
               if (response.isSuccessful){
                 verifyOtpResponseData.postValue(response.body())
               }
             }
             override fun onFailure(call: Call<VerifyOTPResponseModel>, t: Throwable) {
               verifyOtpResponseData.postValue(null)
             }

         })
    }


}