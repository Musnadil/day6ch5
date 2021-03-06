package com.musnadil.day6ch5.service

import com.musnadil.day6ch5.model.RequestLogin
import com.musnadil.day6ch5.model.RequestRegister
import com.musnadil.day6ch5.model.ResponseLogin
import com.musnadil.day6ch5.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("api/v1/auth/register")
    fun postRegister(@Body request : RequestRegister): Call<ResponseRegister>

    @POST("api/v1/auth/login")
    fun postLogin(@Body request: RequestLogin): Call<ResponseLogin>

    @GET("api/v1/auth/me")
    fun checkAuth(@Header("Authorization") token:String):Call<ResponseRegister>

    @PUT("api/v1/users")
    fun updateUser(@Header("Authorization")token:String,@Body request: RequestLogin) :Call<ResponseRegister>

}