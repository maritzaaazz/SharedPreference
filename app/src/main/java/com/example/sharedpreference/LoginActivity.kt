package com.example.sharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpreference.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager.getInstance(this)

        with(binding){
            btnLogin.setOnClickListener {
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()

                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(
                        this@LoginActivity,
                        "Mohon isi semua data",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    if (isValidUsernamePassword()){
                        prefManager.setLoggedIn(true)
                        checkLoginStatus()
                    }else{
                        Toast.makeText(this@LoginActivity, "Usernmae atau password salah",
                            Toast.LENGTH_SHORT). show()
                    }
                }
            }
            txtRegister.setOnClickListener{
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }
    private fun isValidUsernamePassword(): Boolean{
        val username = prefManager.getUsername()
        val password = prefManager.getPassword()
        val inputUsername = binding.edtUsername.text.toString()
        val inputPassword = binding.edtPassword.text.toString()

        return username == inputUsername && password == inputPassword
    }

    private fun checkLoginStatus(){
        val isLoggedIn = prefManager.isLoggedIn()
        if (isLoggedIn){
            Toast.makeText(this@LoginActivity, "Login berhasil",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this@LoginActivity, "Login Gagal",
                Toast.LENGTH_SHORT).show()
        }
    }
}