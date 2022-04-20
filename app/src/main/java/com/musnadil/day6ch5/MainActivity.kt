package com.musnadil.day6ch5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.musnadil.day6ch5.databinding.ActivityMainBinding
import com.musnadil.day6ch5.model.ResponseRegister
import com.musnadil.day6ch5.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = this.getSharedPreferences(LoginActivity.USER, Context.MODE_PRIVATE)

        binding.tvId.text = "ID : ${preferences.getString(LoginActivity.ID,null)}"
        binding.tvUsername.text = "Username : ${preferences.getString(LoginActivity.USERNAME,null)}"
        binding.tvEmail.text = "Email : ${preferences.getString(LoginActivity.EMAIL, null)}"

        binding.btnAuth.setOnClickListener {
            getAuth()
        }
    }
    fun getAuth(){
        val token = preferences.getString(LoginActivity.TOKEN,null)
        ApiClient.instance.checkAuth("Bearer $token")
            .enqueue(object : Callback<ResponseRegister>{
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    val code = response.code()
                    val data = response.body()?.data
                    if (code == 200){
                        Toast.makeText(this@MainActivity, "Token ${data?.username} is valid", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@MainActivity, "Token invalid", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
    }
}