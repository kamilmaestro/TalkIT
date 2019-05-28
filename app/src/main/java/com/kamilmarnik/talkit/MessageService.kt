package com.kamilmarnik.talkit

import com.kamilmarnik.talkit.internal.MessageServiceImpl
import retrofit2.Retrofit
import java.net.URL

interface MessageService {
    fun getMessAPI(url: String): MessageAPI
    fun getMessDate(wholeDate: String): String
    fun getMessHour(wholeDate: String): String

    companion object {
        fun invoke(): MessageService = MessageServiceImpl()
    }
}