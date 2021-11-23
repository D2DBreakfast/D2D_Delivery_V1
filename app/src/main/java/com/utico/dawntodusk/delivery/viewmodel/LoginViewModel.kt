package com.utico.dawntodusk.delivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.dawntodusk.delivery.model.LoginResponseModel
import com.utico.dawntodusk.delivery.retrofit.ApiService
import com.utico.dawntodusk.delivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel:ViewModel() {
var countryCode:String? = null
var mobileNo:String? = null
lateinit var loginResponseData:MutableLiveData<LoginResponseModel>
lateinit var errbody:MutableLiveData<String>

     init {
         loginResponseData = MutableLiveData()
     }

    fun loginObservable(): MutableLiveData<LoginResponseModel>{
        return loginResponseData
    }

    fun loginApiCall(){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.deliveryBoyLogin(mobileNo!!)
            call.enqueue(object : Callback<LoginResponseModel>{
                override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) {
                    if(response.isSuccessful){
                        loginResponseData.postValue(response.body())
                    }else{
                        errbody.postValue(response.errorBody().toString())

                    }
                }

                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                    loginResponseData.postValue(null)
                    errbody.postValue(t.toString())

                }

            })
    }
}