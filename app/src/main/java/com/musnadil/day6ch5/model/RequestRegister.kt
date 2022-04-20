package com.musnadil.day6ch5.model


import com.google.gson.annotations.SerializedName

data class RequestRegister(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String

)