package com.utico.dawntodusk.delivery.retrofit

import com.utico.dawntodusk.delivery.model.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.Call

interface ApiService {

  @FormUrlEncoded
  @POST("deliveryBoyLogin")
   fun deliveryBoyLogin(
      @Field("mobileNo") mobileNo:String
   ): Call<LoginResponseModel>


   @FormUrlEncoded
   @POST("verifyDeliveryBoyOtp")
    fun verifyOtp(
       @Field("mobileNo") mobileNo:String,
       @Field("mobileOtp") mobileOtp:String
    ):Call<VerifyOTPResponseModel>

    @FormUrlEncoded
    @POST("fetchDeliveryOrderList")
    fun fetchFoodDeliveryPendingOrders(
        @Field("deliveryBoyId") deliveryBoyId:String,
        @Field("sectorId") sectorId:String
        ):Call<DeliveryOrderListResponse>

    @FormUrlEncoded
    @POST("fetchOrderItemDetails")
    fun fetchUserOrderDetails(
        @Field("userId") userId:String,
        @Field("orderId") orderId:String
    ): Call<OrderDetailsResponseModel>

    @FormUrlEncoded
    @POST("confirmOrder")
    fun orderConfirm(
     @Field("userId") userId:String,
     @Field("orderId") orderId:String
    ):Call<OrderConfirmResponse>

    @FormUrlEncoded
    @POST("deliveryOrderHistory")
    fun orderHistory(
    @Field("orderStatus") orderStatus:String
    ):Call<OrderHistoryResponseModel>
}