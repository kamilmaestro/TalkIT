package com.kamilmarnik.talkit

import android.support.v7.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.widget.EditText
import java.lang.ClassCastException

class EditMessDialog: AppCompatDialogFragment() {
    lateinit var listener: EditMessDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val view = activity!!.layoutInflater.inflate(R.layout.edit_mess_dialog, null)
        val mContentEditText = view.findViewById<EditText>(R.id.contentEditText)


        builder.setView(view).setTitle("Edit a message")
            .setNegativeButton("Cancel") { _, _ -> }
            .setPositiveButton("Ok") { _, _ -> listener.applyContent(mContentEditText.text.toString())}


        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try { context as EditMessDialogListener } catch (e: ClassCastException)
            {throw ClassCastException("Must implement EditMessDialogListener")}
    }

    interface EditMessDialogListener{
        fun applyContent(newContent: String)
    }
}