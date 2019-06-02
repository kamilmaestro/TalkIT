package com.kamilmarnik.talkit.internal

import com.kamilmarnik.talkit.MessageAPI
import com.kamilmarnik.talkit.MessageService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MessageServiceImpl: MessageService {
    override fun getMessAPI(url: String?): MessageAPI {
        val retrofit = Retrofit.Builder().baseUrl(url ?: "").addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(MessageAPI::class.java)
    }

    override fun getMessDate(wholeDate: String?): String =
        wholeDate?.substringBefore("T").toString()

    override fun getMessHour(wholeDate: String?): String =
        wholeDate?.subSequence(wholeDate.indexOf("T") + 1, wholeDate.indexOf(".")).toString()

}