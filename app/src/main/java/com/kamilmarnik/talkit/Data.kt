package com.kamilmarnik.talkit

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

fun saveData(context: Context?, key: String, value: String) {
    val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)?: return
    with(sharedPref.edit()){
        putString(key, value)
        apply()
    }
}

fun loadData(context: Context?, key: String, defVal: String): String? =
    PreferenceManager.getDefaultSharedPreferences(context).getString(key, defVal)

fun goToFragment(fragment: Fragment, fragmentManager: FragmentManager?, activity: Activity, checkedItem: Int) {
    (activity as MainActivity).nav_view.setCheckedItem(checkedItem)
    fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fragment)?.commit()
}

fun isInternetCon(context: Context?): Boolean{
    val network = (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
    return network != null && network.isConnected
}