package com.kamilmarnik.talkit.model

object User{
    const val DEF_LOGIN = "unknown"
    private const val MAX_LOGIN = 15
    private const val MIN_LOGIN = 5
    var login: String = ""

    fun isLoginProper(): Boolean {
        if(login.length < MIN_LOGIN || login.length > MAX_LOGIN)
            return false

        var isProper = true
        login.toCharArray().forEach { ch -> if(!ch.isLetterOrDigit()) isProper = false}
        return isProper
    }
}