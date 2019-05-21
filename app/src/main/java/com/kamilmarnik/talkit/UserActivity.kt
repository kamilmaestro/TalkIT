package com.kamilmarnik.talkit

import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
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
    private lateinit var mSetLogin:Button
    private var user = User()

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
        setLogin(loadData().toString())
        mLoginEditText.setText(user.login)

        mSetLogin.setOnClickListener{setLogin(mLoginEditText.text.toString()); saveData(); passData()}
    }

    private fun setLogin(value: String){
        user.login = value
    }

    private fun loadData():String?{
        val shared: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)

        return shared.getString(getString(R.string.LOGIN), "")
    }

    private fun saveData(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this.context)?: return
        with(sharedPref.edit()){
            putString(getString(R.string.LOGIN), user.login)
            apply()
        }
    }

    private fun passData(){
        fragmentManager!!.beginTransaction().replace(R.id.fragment_container, ShoutboxActivity()).commit()
    }
}