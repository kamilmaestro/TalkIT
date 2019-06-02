package com.kamilmarnik.talkit

import android.content.Context
import android.preference.PreferenceManager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView

fun saveData(context: Context?, key: String, value: String) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)?: return
        with(sharedPref.edit()){
            putString(key, value)
            apply()
        }
    }

    fun loadData(context: Context?, key: String, defVal: String): String? =
        PreferenceManager.getDefaultSharedPreferences(context).getString(key, defVal)