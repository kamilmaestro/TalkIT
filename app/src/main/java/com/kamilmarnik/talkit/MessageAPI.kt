package com.kamilmarnik.talkit

import com.kamilmarnik.talkit.model.MessageJSON
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MessageAPI{
    @GET("messages") fun getMessagesJSON(): Call<List<MessageJSON>>
    @POST("message") fun postMessageJSON(@Body messJSON: MessageJSON): Call<MessageJSON>
}