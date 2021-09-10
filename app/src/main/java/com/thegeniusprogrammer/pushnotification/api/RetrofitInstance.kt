package com.thegeniusprogrammer.pushnotification.api

import com.thegeniusprogrammer.pushnotification.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

 private val retrofit by lazy {
     Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
 }

    val api: NotificationAPI by lazy {
        retrofit.create(NotificationAPI::class.java)
    }

}