package com.kamilmarnik.talkit

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.kamilmarnik.talkit.dto.User

class UserActivity: Fragment() {
    private lateinit var mLoginEditText:EditText
    lateinit var mSetLogin:Button
    lateinit private var userLogin: String
    private var model: Communicator ?= null

    companion object{
        fun create(): UserActivity = UserActivity()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProviders.of(activity!!).get(Communicator::class.java)
        mLoginEditText = view.findViewById(R.id.loginEditText)
        mSetLogin = view.findViewById(R.id.setLoginBtn)

        mSetLogin.setOnClickListener{setLogin(); saveData(); passData()}
    }

    private fun saveData(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this.context)?: return
        with(sharedPref.edit()){
            putString(getString(R.string.LOGIN), userLogin)
            commit()
        }
        Log.d("TAG", "user: $userLogin")
    }
    fun setLogin(){
        userLogin = mLoginEditText.text.toString()
    }
    fun getLogin(): String?{
        return userLogin
    }


    private fun passData(){
        model!!.setMsg(userLogin)
        fragmentManager!!.beginTransaction().replace(R.id.fragment_container, ShoutboxActivity()).commit()
    }
}