package com.kamilmarnik.talkit

import android.content.Context
import com.kamilmarnik.talkit.internal.HttpRequestsImpl

interface HttpRequests {
    fun deleteMessage(id: String?, context: Context?)
    var url: String

    companion object{
        fun invoke(url: String): HttpRequests = HttpRequestsImpl(url)
    }
}