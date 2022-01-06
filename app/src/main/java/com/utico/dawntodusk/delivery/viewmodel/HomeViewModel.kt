package com.utico.dawntodusk.delivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.dawntodusk.delivery.model.DeliveryOrderListResponse
import com.utico.dawntodusk.delivery.retrofit.ApiService
import com.utico.dawntodusk.delivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    //var deliveryBoyId:String? =null
    lateinit var pendingOrderResponse:MutableLiveData<DeliveryOrderListResponse>
    lateinit var errorResponse:MutableLiveData<String>

   /* private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text*/

    init {
        pendingOrderResponse= MutableLiveData()
        errorResponse = MutableLiveData()
    }

    fun pendingOrderObservable():MutableLiveData<DeliveryOrderListResponse>{
        return pendingOrderResponse
    }

    fun apiCallPendingOrders(deliveryBoyId:String,sectorId:String){
     val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
     val call =retroInstance.fetchFoodDeliveryPendingOrders(deliveryBoyId!!,sectorId)
         call.enqueue(object : Callback<DeliveryOrderListResponse>{
             override fun onResponse(call: Call<DeliveryOrderListResponse>, response: Response<DeliveryOrderListResponse>) {
                 if (response.isSuccessful){
                     pendingOrderResponse.postValue(response.body())
                 }else{
                     errorResponse.postValue(response.errorBody().toString())
                 }
             }

             override fun onFailure(call: Call<DeliveryOrderListResponse>, t: Throwable) {
                 pendingOrderResponse.postValue(null)
                 errorResponse.postValue(t.toString())
             }

         })
    }

}