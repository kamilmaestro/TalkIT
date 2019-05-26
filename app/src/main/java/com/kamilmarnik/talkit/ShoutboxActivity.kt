package com.kamilmarnik.talkit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kamilmarnik.talkit.model.MessJSON
import com.kamilmarnik.talkit.model.Message
import com.kamilmarnik.talkit.model.User
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
        loadMessages()
        mMessageRecView.adapter = MessageAdapter(messagesList, this.context)

        //loadMessages()
        //testList()
    }

    fun loadMessages(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonService: JsonService = retrofit.create(JsonService::class.java)
        val call: Call<List<MessJSON>> = jsonService.getMessagesJSON()
        call.enqueue(object : Callback<List<MessJSON>> {
            override fun onFailure(call: Call<List<MessJSON>>, t: Throwable) {
                Toast.makeText(context, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<MessJSON>>, response: Response<List<MessJSON>>) {
                if(!response.isSuccessful){
                    Toast.makeText(context, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
                    return
                }
                val messJSON: List<MessJSON>? = response.body()
                var counter = 0
                for(i in 0..(messJSON?.size!! - 1 ?: 0)) {
                    messagesList.add(Message(messJSON[counter].content, messJSON[counter].date, "23:05", messJSON[counter].content))
                    counter++
                }
            }
        })


    }




    fun testList(){
        messagesList.add(Message(User.login, "2014", "05:03", "jestem kamil 1 komentarz"))
        messagesList.add(Message("hej3s", "2019", "10:30", "jestem  2 komentarz"))
        messagesList.add(Message("mapper", "2019", "20:54", "3 komentarz"))
        messagesList.add(Message(User.login, "2014", "05:03", "jestem kamil 1 komentarz"))
        messagesList.add(Message("hej3", "2019", "10:30", "jestem  2 komentarz"))
        messagesList.add(Message("mapper", "2019", "20:54", "3 komentarz"))
        messagesList.add(Message(User.login, "2014", "05:03", "jestem kamil 1 komentarz"))
        messagesList.add(Message("hej3", "2019", "10:30", "jestem  2 komentarz"))
        messagesList.add(Message("mapper", "2019", "20:54", "3 komentarz"))
        messagesList.add(Message(User.login, "2014", "05:03", "jestem kamil 1 komentarz"))
        messagesList.add(Message("hej3", "2019", "10:30", "jestem  2 komentarz"))
        messagesList.add(Message("mapper", "2019", "20:54", "3 komentarz"))
        messagesList.add(Message(User.login, "2014", "05:03", "jestem kamil 1 komentarz"))
        messagesList.add(Message("hej3", "2019", "10:30", "jestem  2 komentarz"))
        messagesList.add(Message("mapper", "2019", "20:54", "3 komentarz"))
    }
}