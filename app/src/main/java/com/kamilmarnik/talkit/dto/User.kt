package com.kamilmarnik.talkit.dto

import com.kamilmarnik.talkit.constants.MAX_LOGIN
import com.kamilmarnik.talkit.constants.MIN_LOGIN

object User{
    var login: String = ""

    fun isLoginProper(): Boolean {
        var isProper = true
        if(login.length < MIN_LOGIN || login.length > MAX_LOGIN)
            isProper = false
        login.toCharArray().forEach { ch -> if(!ch.isLetterOrDigit()) isProper = false}
        return isProper
    }
}