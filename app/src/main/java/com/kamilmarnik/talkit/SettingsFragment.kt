package com.kamilmarnik.talkit

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kamilmarnik.talkit.model.User

class SettingsFragment: Fragment(){

    private lateinit var deleteFloatBtn: View
    private lateinit var refreshFloatBtn: View
    private lateinit var editFloatBtn: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deleteFloatBtn = view.findViewById(R.id.deleteFloating)
        refreshFloatBtn = view.findViewById(R.id.refreshFloating)
        editFloatBtn = view.findViewById(R.id.editFloating)

        floatBtnListeners()
    }

    private fun floatBtnListeners(){
        deleteFloatBtn.setOnClickListener { view ->
            Snackbar.make(view, "Hold a few seconds to delete a message", Snackbar.LENGTH_LONG).show()}

        refreshFloatBtn.setOnClickListener { view ->
            Snackbar.make(view, "Swipe from the top to refresh the shoutbox", Snackbar.LENGTH_LONG).show() }

        editFloatBtn.setOnClickListener { view ->
            Snackbar.make(view, "Click a message to edit it", Snackbar.LENGTH_LONG).show() }
    }
}