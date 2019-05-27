package com.kamilmarnik.talkit

import com.kamilmarnik.talkit.model.MessageJSON
import retrofit2.Call
import retrofit2.http.GET

interface MessageAPI{
    @GET("messages") fun getMessagesJSON(): Call<List<MessageJSON>>
}