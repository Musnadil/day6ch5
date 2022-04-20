package com.musnadil.day6ch5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.musnadil.day6ch5.databinding.ActivityRegisterBinding
import com.musnadil.day6ch5.model.RequestRegister
import com.musnadil.day6ch5.model.ResponseRegister
import com.musnadil.day6ch5.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegist.setOnClickListener {
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            registerAction(email,password,username)
        }
    }
    private fun registerAction(email: String, pass: String, username : String){
        val request = RequestRegister(email,username,pass)
        ApiClient.instance.postRegister(request)
            .enqueue(object : Callback<ResponseRegister>{
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    val code = response.code()
                    if (code == 201){
                        Toast.makeText(this@RegisterActivity, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                    }else{
                        Toast.makeText(this@RegisterActivity, "code diluar 201", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
    }
}