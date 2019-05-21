package com.kamilmarnik.talkit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ShoutboxActivity: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shoutbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
    }

    fun loadData(){
        val shared: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
        val login: String? = shared.getString(getString(R.string.LOGIN), "")
        val userLogin: TextView? = view?.findViewById(R.id.editMe)
        userLogin?.text = login
    }
}