package com.kamilmarnik.talkit

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class Communicator: ViewModel() {
    val message = MutableLiveData<Any>()

    fun setMsg(msg: String?){
        message.value = msg
    }
}