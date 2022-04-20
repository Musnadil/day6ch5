package com.musnadil.day6ch5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.musnadil.day6ch5.databinding.ActivityLogin2Binding
import com.musnadil.day6ch5.model.RequestLogin
import com.musnadil.day6ch5.model.ResponseLogin
import com.musnadil.day6ch5.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogin2Binding
    private lateinit var preferences : SharedPreferences
    companion object{
        const val USER = "USER"
        const val ID = "ID"
        const val USERNAME = "USERNAME"
        const val EMAIL = "EMAIL"
        const val TOKEN = "TOKEN"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = this.getSharedPreferences(USER, Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            loginAction(binding.email.text.toString(),binding.password.text.toString())
        }
        binding.btnRegist.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    private fun loginAction(email : String, pass: String){
        val request = RequestLogin(email,pass)


        ApiClient.instance.postLogin(request)
            .enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    val code = response.code()
                    val body = response.body()
                    if(code == 200){
                        val editSp : SharedPreferences.Editor = preferences.edit()
                        editSp.putString(ID,body?.data?.id)
                        editSp.putString(USERNAME,body?.data?.username)
                        editSp.putString(EMAIL,body?.data?.email)
                        editSp.putString(TOKEN,body?.data?.token)
                        editSp.apply()
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                    }else{
                        Toast.makeText(this@LoginActivity, "gagal login", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}