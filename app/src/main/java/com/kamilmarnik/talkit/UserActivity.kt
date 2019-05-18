package com.kamilmarnik.talkit

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class UserActivity: Fragment() {
    private lateinit var mLoginEditText:EditText
    lateinit var mSetLogin:Button
    lateinit var userLogin:String

    companion object{
        fun create(): UserActivity = UserActivity()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoginEditText = view.findViewById(R.id.loginEditText)
        mSetLogin = view.findViewById(R.id.setLoginBtn)
        mSetLogin.setOnClickListener{userLogin = getLoginEditText(); saveData()}
    }

    private fun getLoginEditText(): String {
        return mLoginEditText.text.toString()
    }

    fun saveData(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)?: return
        with(sharedPref.edit()){
            putString(getString(R.string.LOGIN), userLogin)
            commit()
        }
    }

}