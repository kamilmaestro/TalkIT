package com.kamilmarnik.talkit

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kamilmarnik.talkit.model.Message
import kotlinx.android.synthetic.main.message_item.view.*

class MessageAdapter(private val messagesList: MutableList<Message>, private val context: Context?): RecyclerView.Adapter<MessageAdapter.MessViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewHolder: Int): MessViewHolder =
        MessViewHolder(LayoutInflater.from(context).inflate(R.layout.message_item, parent, false))

    override fun onBindViewHolder(holder: MessViewHolder, position: Int) {
        val currentMess: Message = messagesList[position]
        holder.login?.text = currentMess.login
        holder.date?.text = currentMess.date
        holder.hour?.text = currentMess.hour
        holder.content?.text = currentMess.content

        holder.itemView.setOnLongClickListener{
            remove(position)
            HttpRequests.invoke(context!!.resources.getString(R.string.URL)).deleteMessage(currentMess.id, context)
            true
        }
    }

    override fun getItemCount(): Int = messagesList.size

    class MessViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val login: TextView? = view.loginText
        val date: TextView? = view.dateText
        val hour: TextView? = view.hourText
        val content: TextView? = view.contentText
    }

    private fun remove(position: Int) {
        messagesList.removeAt(position)
        notifyItemChanged(position)
    }
}