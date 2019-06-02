package com.kamilmarnik.talkit

import android.content.Context
import com.kamilmarnik.talkit.internal.HttpRequestsImpl
import retrofit2.Call

interface HttpRequests {
    var url: String
    fun sendMessage(content: String, context: Context?)
    fun deleteMessage(id: String?, context: Context?)
    fun editMessage(id: String?, content: String, context: Context?)
    fun makeEnqueue(call: Call<Any>, context: Context?)

    companion object{
        fun invoke(url: String): HttpRequests = HttpRequestsImpl(url)
    }
}