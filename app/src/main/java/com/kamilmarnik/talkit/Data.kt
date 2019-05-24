package com.kamilmarnik.talkit

import android.content.Context
import com.kamilmarnik.talkit.internal.DataImpl

interface Data {
    fun saveData(context: Context?, key: String, value: String)
    fun loadData(context: Context?, key: String, defVal: String): String?

    companion object {
        fun invoke(): Data = DataImpl()
    }
}