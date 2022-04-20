package com.musnadil.day6ch5.model


import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("data")
    val data: DataLogin,
    @SerializedName("success")
    val success: Boolean
)

data class DataLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("username")
    val username: String
)