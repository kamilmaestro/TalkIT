package com.kamilmarnik.talkit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.kamilmarnik.talkit.model.MessageJSON
import com.kamilmarnik.talkit.model.Message
import com.kamilmarnik.talkit.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ShoutboxActivity: Fragment() {

    private var messagesList: MutableList<Message> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shoutbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mSendBtn = view.findViewById<ImageButton>(R.id.sendBtn)
        val mMessEditText = view.findViewById<EditText>(R.id.messageEditText)
        val mMessageRecView = view.findViewById<RecyclerView>(R.id.messagesRecyclerView)
        mMessageRecView.setHasFixedSize(true)
        mMessageRecView.layoutManager = LinearLayoutManager(this.context)
        mMessageRecView.adapter = MessageAdapter(messagesList, this.context)
        loadMessages(mMessageRecView)
        mSendBtn.setOnClickListener{sendMessages(mMessEditText.text.toString()); mMessEditText.text.clear()}
        mMessageRecView.adapter
        //deleteMessage("5ced0ee808208d03d637a06d")
    }

    private fun loadMessages(recView: RecyclerView){
        val call: Call<List<MessageJSON>> = MessageService.invoke().getMessAPI(getString(R.string.URL)).getAllMessJSON()

        call.enqueue(object : Callback<List<MessageJSON>> {
            override fun onFailure(call: Call<List<MessageJSON>>, t: Throwable) {
                Toast.makeText(context, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<MessageJSON>>, response: Response<List<MessageJSON>>) {
                if(!response.isSuccessful){
                    Toast.makeText(context, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
                    return
                }
                showMessages(response, recView)
            }
        })
    }

    fun showMessages(response: Response<List<MessageJSON>>, recView: RecyclerView){
        val messJSON: List<MessageJSON>? = response.body()
        for((counter) in (0 until messJSON?.size!!).withIndex()) {
            messagesList.add(Message(messJSON[counter].login,
                MessageService.invoke().getMessDate(messJSON[counter].date),
                MessageService.invoke().getMessHour(messJSON[counter].date),
                messJSON[counter].content,
                messJSON[counter].id)
            )
        }
        recView.adapter = MessageAdapter(messagesList, context)
    }

    fun sendMessages(content: String){
        val messJSON = MessageJSON(content, User.login)
        val call: Call<MessageJSON> = MessageService.invoke().getMessAPI(getString(R.string.URL))
            .postMessJSON(messJSON.content, messJSON.login)

        call.enqueue(object : Callback<MessageJSON>{
            override fun onFailure(call: Call<MessageJSON>, t: Throwable) {
                Toast.makeText(context, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<MessageJSON>, response: Response<MessageJSON>) {
                if(!response.isSuccessful){
                    Toast.makeText(context, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
                    return
                }
            }
        })
    }
}