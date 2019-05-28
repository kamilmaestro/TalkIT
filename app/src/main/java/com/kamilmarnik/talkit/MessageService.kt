package com.kamilmarnik.talkit

import com.kamilmarnik.talkit.internal.MessageServiceImpl

interface MessageService {
    fun getMessAPI(url: String): MessageAPI
    fun getMessDate(wholeDate: String?): String
    fun getMessHour(wholeDate: String?): String

    companion object {
        fun invoke(): MessageService = MessageServiceImpl()
    }
}