package com.utico.dawntodusk.delivery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.dawntodusk.delivery.model.OrderHistoryResponseModel
import com.utico.dawntodusk.delivery.retrofit.ApiService
import com.utico.dawntodusk.delivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
  private lateinit var orderHistoryResponse:MutableLiveData<OrderHistoryResponseModel>

  init {
      orderHistoryResponse = MutableLiveData()
  }

    fun orderHistoryObservable():MutableLiveData<OrderHistoryResponseModel> {
        return  orderHistoryResponse
    }

    fun apiCallOrderHistory(orderStatus:String){
      val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
      val call = retroInstance.orderHistory(orderStatus)
          call.enqueue(object :Callback<OrderHistoryResponseModel>{
              override fun onResponse(call: Call<OrderHistoryResponseModel>, response: Response<OrderHistoryResponseModel>) {
                  if (response.isSuccessful){
                      orderHistoryResponse.postValue(response.body())

                  }
              }
              override fun onFailure(call: Call<OrderHistoryResponseModel>, t: Throwable) {
                  orderHistoryResponse.postValue(null)
              }
          })
    }
}