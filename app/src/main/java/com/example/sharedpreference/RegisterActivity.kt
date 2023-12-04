package com.example.sharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpreference.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefManager = PrefManager.getInstance(this)

        with(binding){
            btnRegister.setOnClickListener {
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()
                val confirmPassowrd = edtPasswordConfirm.text.toString()

                if(username.isEmpty() || password.isEmpty() ||
                    confirmPassowrd.isEmpty()){
                    Toast.makeText(
                        this@RegisterActivity,
                        "Mohon isi semua data",
                        Toast.LENGTH_SHORT
                    ).show()
                }else if (password != confirmPassowrd){
                    Toast.makeText(this@RegisterActivity, "Password Tidak Sama",
                        Toast.LENGTH_SHORT)
                        .show()
                }else{
                    prefManager.saveUsername(username)
                    prefManager.savePassword(password)
                    prefManager.setLoggedIn(true)
                    checkLoginStatus()
                }
            }
            txtLogin.setOnClickListener{
                startActivity((Intent(this@RegisterActivity, LoginActivity::class.java)))
            }
        }
    }

    private fun checkLoginStatus(){
        val isLoggedIn = prefManager.isLoggedIn()
        if (isLoggedIn){
            Toast.makeText(this@RegisterActivity, "Registrasi berhasil",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this@RegisterActivity, "Registrasi gagal",
                Toast.LENGTH_SHORT).show()
        }
    }
}