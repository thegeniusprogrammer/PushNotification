package com.thegeniusprogrammer.pushnotification.api

import com.thegeniusprogrammer.pushnotification.utils.Constants.Companion.CONTENT_TYPE
import com.thegeniusprogrammer.pushnotification.utils.Constants.Companion.SERVER_KEY
import com.thegeniusprogrammer.pushnotification.modelclass.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun sendNotification(@Body notification: PushNotification): Response<ResponseBody>



}