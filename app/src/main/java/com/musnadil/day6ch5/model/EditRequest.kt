package com.musnadil.day6ch5.model

import com.google.gson.annotations.SerializedName

data class EditRequest (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)