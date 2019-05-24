package com.kamilmarnik.talkit.internal

import android.content.Context
import android.preference.PreferenceManager
import com.kamilmarnik.talkit.Data


class DataImpl: Data {
    override fun saveData(context: Context?, key: String, value: String) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)?: return
        with(sharedPref.edit()){
            putString(key, value)
            apply()
        }
    }

    override fun loadData(context: Context?, key: String, defVal: String): String? =
        PreferenceManager.getDefaultSharedPreferences(context).getString(key, defVal)
}