package com.kamilmarnik.talkit.internal

import android.content.Context
import android.widget.Toast
import com.kamilmarnik.talkit.HttpRequests
import com.kamilmarnik.talkit.MessageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HttpRequestsImpl(override var url: String) : HttpRequests {

    override fun deleteMessage(id: String?, context: Context?) {
        val call: Call<Void> = MessageService.invoke().getMessAPI(url)
            .deleteMessJSON(id)

        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(!response.isSuccessful) {
                    Toast.makeText(context, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
                    return
                }
            }
        })
    }
}