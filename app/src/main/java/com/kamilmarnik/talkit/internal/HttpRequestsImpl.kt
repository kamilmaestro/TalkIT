package com.kamilmarnik.talkit.internal

import android.content.Context
import android.widget.Toast
import com.kamilmarnik.talkit.HttpRequests
import com.kamilmarnik.talkit.MessageService
import com.kamilmarnik.talkit.R
import com.kamilmarnik.talkit.model.MessageJSON
import com.kamilmarnik.talkit.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HttpRequestsImpl(override var url: String) : HttpRequests {
    override fun sendMessage(content: String, context: Context?) {
        val messJSON = MessageJSON(content, User.login)
        val call: Call<MessageJSON> = MessageService.invoke().getMessAPI(url)
            .postMessJSON(messJSON.content, messJSON.login)

        makeEnqueue(call as Call<Any>, context)
    }

    override fun deleteMessage(id: String?, context: Context?) {
        val call: Call<Void> = MessageService.invoke().getMessAPI(url)
            .deleteMessJSON(id)

        makeEnqueue(call as Call<Any>, context)
    }

    override fun makeEnqueue(call: Call<Any>, context: Context?) {
        call.enqueue(object : Callback<Any>{
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(context, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(!response.isSuccessful) {
                    Toast.makeText(context, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
                    return
                }
            }
        })
    }
}