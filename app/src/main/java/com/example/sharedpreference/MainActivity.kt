package com.example.sharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreference.databinding.ActivityLoginBinding
import com.example.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager.getInstance(this)
        checkLoginStatus()
        with(binding){
            txtUsername.text = prefManager.getUsername()
            btnLogout.setOnClickListener {
                prefManager.setLoggedIn(false)
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
            btnClear.setOnClickListener {
                prefManager.clear()
                finish()
            }
        }
    }

    fun checkLoginStatus(){
        val isLoggedIn = prefManager.isLoggedIn()
        if (!isLoggedIn){
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }
}