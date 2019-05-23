package com.kamilmarnik.talkit

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.kamilmarnik.talkit.constants.*
import com.kamilmarnik.talkit.dto.User

class UserActivity: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mLoginEditText = view.findViewById<EditText>(R.id.loginEditText)
        val mSetLogin = view.findViewById<Button>(R.id.setLoginBtn)

        setLogin(loadData().toString())
        mLoginEditText.setText(User.login)
        mSetLogin.setOnClickListener{setLogin(mLoginEditText.text.toString()); saveData(); passData()}
    }

    private fun setLogin(value: String){
        User.login = value
    }

    private fun loadData():String?{
        val shared: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)

        return shared.getString(getString(R.string.LOGIN), DEF_LOGIN)
    }

    private fun saveData(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this.context)?: return
        with(sharedPref.edit()){
            putString(getString(R.string.LOGIN), User.login)
            apply()
        }
    }

    private fun passData(){
        fragmentManager!!.beginTransaction().replace(R.id.fragment_container, ShoutboxActivity()).commit()
    }
}