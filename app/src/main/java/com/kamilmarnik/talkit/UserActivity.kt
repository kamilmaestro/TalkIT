package com.kamilmarnik.talkit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
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
        checkInternetCon(mSetLogin)
        mSetLogin.setOnClickListener{setLogin(mLoginEditText.text.toString()); checkLogin()}
        manualRefresh(mSetLogin)
    }

    private fun setLogin(value: String){
        User.login = value
    }

    private fun checkInternetCon(mSetLogin: Button){
        if(!isInternetCon(context)){
            mSetLogin.isEnabled = false
            (activity as MainActivity).drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            Toast.makeText(context, "Internet connection required", Toast.LENGTH_LONG).show()
        }else{
            mSetLogin.isEnabled = true
            (activity as MainActivity).drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    private fun checkLogin(){
        if(User.isLoginProper()) {
            saveData(this.context, getString(R.string.LOGIN), User.login)
            goToFragment(ShoutboxActivity(), fragmentManager, activity as FragmentActivity, R.id.drawer_shoutbox)
        }
        else Toast.makeText(this.context, "Wrong login!", Toast.LENGTH_LONG).show()
    }

    private fun manualRefresh(mSetLogin: Button){
        val pullToRefresh: SwipeRefreshLayout? = view?.findViewById(R.id.pullToRefresh)
        pullToRefresh?.setOnRefreshListener { checkInternetCon(mSetLogin); pullToRefresh.isRefreshing = false }
    }
}