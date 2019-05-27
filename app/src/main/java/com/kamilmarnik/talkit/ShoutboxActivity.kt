package com.kamilmarnik.talkit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kamilmarnik.talkit.model.MessageJSON
import com.kamilmarnik.talkit.model.Message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ShoutboxActivity: Fragment() {

    private var messagesList: MutableList<Message> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shoutbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mMessageRecView = view.findViewById<RecyclerView>(R.id.messagesRecyclerView)
        mMessageRecView.setHasFixedSize(true)
        mMessageRecView.layoutManager = LinearLayoutManager(this.context)
        mMessageRecView.adapter = MessageAdapter(messagesList, this.context)

        loadMessages(mMessageRecView)
    }

    fun loadMessages(rec: RecyclerView){
        val retrofit: Retrofit = MessageService.invoke().buildRetrofit(getString(R.string.URL))

        val messAPI: MessageAPI = retrofit.create(MessageAPI::class.java)
        val call: Call<List<MessageJSON>> = messAPI.getMessagesJSON()
        call.enqueue(object : Callback<List<MessageJSON>> {
            override fun onFailure(call: Call<List<MessageJSON>>, t: Throwable) {
                Toast.makeText(context, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<MessageJSON>>, response: Response<List<MessageJSON>>) {
                if(!response.isSuccessful){
                    Toast.makeText(context, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
                    return
                }
                val messJSON: List<MessageJSON>? = response.body()
                for((counter) in (0 until messJSON?.size!!).withIndex()) {
                    messagesList.add(Message(messJSON[counter].login, messJSON[counter].date, "23:05", messJSON[counter].content))
                }
                rec.adapter = MessageAdapter(messagesList, context)
            }
        })


    }
}