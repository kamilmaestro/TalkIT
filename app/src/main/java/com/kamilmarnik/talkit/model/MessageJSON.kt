package com.kamilmarnik.talkit.model

data class MessageJSON(var content: String, var login: String) {
    var date: String? = null
    var id: String? = null
}