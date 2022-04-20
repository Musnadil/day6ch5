package com.musnadil.day6ch5

import android.content.Intent
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    if(code == 200){
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