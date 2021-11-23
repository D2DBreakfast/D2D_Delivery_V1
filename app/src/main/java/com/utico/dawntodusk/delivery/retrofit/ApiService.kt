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
       @Field("otp") otp:String
    ):Call<VerifyOTPResponseModel>

    @FormUrlEncoded
    @POST("getDeliveryBoyPendingOrders")
    fun fetchFoodDeliveryPendingOrders(
        @Field("deliveryBoyId") deliveryBoyId:String
    ):Call<DeliveryPendingOrdersResponseModel>

    @FormUrlEncoded
    @POST("userOrderDeliveryDetails")
    fun fetchUserOrderDetails(
        @Field("userId") userId:String
    ): Call<UserItemOrderDetailsModel>


    @FormUrlEncoded
    @POST("confirmOrder")
    fun orderConfirm(
     @Field("userId") userId:String,
     @Field("orderId") orderId:String
    ):Call<OrderConfirmResponse>
}