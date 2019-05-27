package com.kamilmarnik.talkit.internal

import com.kamilmarnik.talkit.MessageService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MessageServiceImpl: MessageService {
    override fun buildRetrofit(url: String): Retrofit =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()


}