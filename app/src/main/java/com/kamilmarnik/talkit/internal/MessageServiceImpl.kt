package com.kamilmarnik.talkit.internal

import com.kamilmarnik.talkit.MessageService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MessageServiceImpl: MessageService {
    override fun buildRetrofit(url: String): Retrofit =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

    override fun getMessDate(wholeDate: String): String =
        wholeDate.substringBefore("T")

    override fun getMessHour(wholeDate: String): String =
        wholeDate.subSequence(wholeDate.indexOf("T") + 1, wholeDate.indexOf(".")).toString()

}