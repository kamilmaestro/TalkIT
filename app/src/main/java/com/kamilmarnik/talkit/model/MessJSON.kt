package com.kamilmarnik.talkit.model

import com.google.gson.annotations.SerializedName

data class MessJSON(
    @SerializedName("content") var content:String,
    @SerializedName("login") var login:String,
    @SerializedName("date") var date:String,
    @SerializedName("id") var id: String
)