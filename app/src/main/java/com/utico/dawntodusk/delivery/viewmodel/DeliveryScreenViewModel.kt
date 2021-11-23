package com.utico.dawntodusk.delivery.viewmodel

import android.net.DnsResolver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.SortedList
import com.utico.dawntodusk.delivery.model.OrderConfirmResponse
import com.utico.dawntodusk.delivery.model.UserItemOrderDetailsModel
import com.utico.dawntodusk.delivery.retrofit.ApiService
import com.utico.dawntodusk.delivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryScreenViewModel : ViewModel() {
    lateinit var userItemOrderDetailResponse:MutableLiveData<UserItemOrderDetailsModel>
    lateinit var orderConfirmResponseData:MutableLiveData<OrderConfirmResponse>


    init {
        userItemOrderDetailResponse= MutableLiveData()
        orderConfirmResponseData = MutableLiveData()
    }

    fun userItemOrderObservable():MutableLiveData<UserItemOrderDetailsModel>{
        return userItemOrderDetailResponse
    }

    fun apiCallUserItemOrders(userId:String){
      val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
      val  call =retroInstance.fetchUserOrderDetails(userId)
           call.enqueue(object :Callback<UserItemOrderDetailsModel>{
               override fun onResponse(call: Call<UserItemOrderDetailsModel>, response: Response<UserItemOrderDetailsModel>) {
                   if(response.isSuccessful){
                       userItemOrderDetailResponse.postValue(response.body())
                   }
               }

               override fun onFailure(call: Call<UserItemOrderDetailsModel>, t: Throwable) {
                   userItemOrderDetailResponse.postValue(null)
               }

           })
    }



    /*Order Confirm Code*/
    fun orderConfirmObservable():MutableLiveData<OrderConfirmResponse>{
        return orderConfirmResponseData
    }

    fun apiCallOrderConfirm(userId:String,orderId:String){
      val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
      val call = retroInstance.orderConfirm(userId,orderId)
          call.enqueue(object : Callback<OrderConfirmResponse>{
              override fun onResponse(call: Call<OrderConfirmResponse>, response: Response<OrderConfirmResponse>) {
                  if (response.isSuccessful){
                      orderConfirmResponseData.postValue(response.body())
                  }
              }

              override fun onFailure(call: Call<OrderConfirmResponse>, t: Throwable) {
                  orderConfirmResponseData.postValue(null)
              }

          })
    }
}