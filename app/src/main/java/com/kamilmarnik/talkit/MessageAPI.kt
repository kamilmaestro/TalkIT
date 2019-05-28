package com.kamilmarnik.talkit

import com.kamilmarnik.talkit.model.MessageJSON
import retrofit2.Call
import retrofit2.http.*

interface MessageAPI{
    @GET("messages") fun getMessagesJSON(): Call<List<MessageJSON>>
    @FormUrlEncoded @POST("message") fun postMessageJSON(@Field("content") content: String,
                                                            @Field("login") login: String): Call<MessageJSON>
}