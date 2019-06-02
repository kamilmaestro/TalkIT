package com.kamilmarnik.talkit

import com.kamilmarnik.talkit.model.MessageJSON
import retrofit2.Call
import retrofit2.http.*

interface MessageAPI{
    @GET("messages") fun getAllMessJSON(): Call<List<MessageJSON>>

    @FormUrlEncoded @POST("message") fun postMessJSON(@Field("content") content: String,
                                                      @Field("login") login: String): Call<MessageJSON>

    @PUT("message/{id}") fun putMessJSON(@Path("id") id: String?, @Body messJSON: MessageJSON): Call<MessageJSON>

    @DELETE("message/{id}") fun deleteMessJSON(@Path("id") id: String?): Call<Void>
}