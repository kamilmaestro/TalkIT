package com.kamilmarnik.talkit

import com.kamilmarnik.talkit.internal.MessageServiceImpl
import retrofit2.Retrofit

interface MessageService {
    fun buildRetrofit(url: String): Retrofit
    fun getMessDate(wholeDate: String): String
    fun getMessHour(wholeDate: String): String

    companion object {
        fun invoke(): MessageService = MessageServiceImpl()
    }
}