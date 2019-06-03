package com.kamilmarnik.talkit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kamilmarnik.talkit.model.User
import com.kamilmarnik.talkit.model.User.DEF_LOGIN
import kotlinx.android.synthetic.main.activity_main.*

class UserActivity: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mLoginEditText = view.findViewById<EditText>(R.id.loginEditText)
        val mSetLogin = view.findViewById<Button>(R.id.setLoginBtn)

        setLogin(loadData(this.context, getString(R.string.LOGIN), DEF_LOGIN).toString())
        mLoginEditText.setText(User.login)
        mSetLogin.setOnClickListener{setLogin(mLoginEditText.text.toString()); checkLogin()}
    }

    private fun setLogin(value: String){
        User.login = value
    }

    private fun checkLogin(){
        if(User.isLoginProper()) {
            saveData(this.context, getString(R.string.LOGIN), User.login)
            goToShoutbox()
        }
        else Toast.makeText(this.context, "Wrong login!", Toast.LENGTH_LONG).show()
    }

    private fun goToShoutbox(){
        (activity as MainActivity).nav_view.setCheckedItem(R.id.drawer_shoutbox)
        fragmentManager!!.beginTransaction().replace(R.id.fragment_container, ShoutboxActivity()).commit()
    }
}