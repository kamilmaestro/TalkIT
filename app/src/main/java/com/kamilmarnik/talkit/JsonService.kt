package com.kamilmarnik.talkit

import com.kamilmarnik.talkit.model.MessJSON
import retrofit2.Call
import retrofit2.http.GET

interface JsonService{
    @GET("messages") fun getMessagesJSON(): Call<List<MessJSON>>
}