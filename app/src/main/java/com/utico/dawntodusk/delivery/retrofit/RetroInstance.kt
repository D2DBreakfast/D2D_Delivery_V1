package com.utico.dawntodusk.delivery.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
  companion object {
val baseUrl ="http://10.12.12.66:3000/deliveryBoy/"

      fun getRetroInstance(): Retrofit{
         val logging = HttpLoggingInterceptor()
             logging.level = (HttpLoggingInterceptor.Level.BODY)
         val client = OkHttpClient.Builder()
             client.addInterceptor(logging)
         return Retrofit.Builder()
             .baseUrl(baseUrl)
             .client(client.build())
             .addConverterFactory(GsonConverterFactory.create())
             .build()
      }
  }

}