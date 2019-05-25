package com.kamilmarnik.talkit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kamilmarnik.talkit.model.Message
import com.kamilmarnik.talkit.model.User

class ShoutboxActivity: Fragment() {

    //private lateinit var mMessageRecView:RecyclerView
    //private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private var messagesList: MutableList<Message> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shoutbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mMessageRecView = view.findViewById<RecyclerView>(R.id.messagesRecyclerView)
        val mLayoutManager: LayoutManager = LinearLayoutManager(this.context)
        mMessageRecView.setHasFixedSize(true)
        mMessageRecView.adapter = MessageAdapter(messagesList, this.context)
        mMessageRecView.layoutManager = mLayoutManager
        mMessageRecView.adapter = mMessageRecView.adapter

        testList()
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