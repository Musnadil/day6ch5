package com.musnadil.day6ch5.model


import com.google.gson.annotations.SerializedName

data class ResponseRegister(
    @SerializedName("data")
    val data: DataRegister,
    @SerializedName("success")
    val success: Boolean
)
data class DataRegister(
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("username")
    val username: String
)