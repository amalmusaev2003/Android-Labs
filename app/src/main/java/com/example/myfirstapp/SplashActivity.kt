package com.example.myfirstapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)
        val autoLogin = sharedPreferences.getBoolean("autoLogin", false)

        Handler(Looper.getMainLooper()).postDelayed({
            when {
                email != null && password != null && autoLogin -> {
                    startActivity(Intent(this, ContentActivity::class.java))
                }
                email != null && password != null -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                else -> {
                    startActivity(Intent(this, RegisterActivity::class.java))
                }
            }
            finish()
        }, 2000) // Задержка 2 секунды для ProgressBar
    }
}