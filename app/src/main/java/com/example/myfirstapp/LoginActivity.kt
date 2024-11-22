package com.example.myfirstapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginEmailEditText: EditText
    private lateinit var loginPasswordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var autoLoginCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        autoLoginCheckBox = findViewById(R.id.autoLoginCheckBox)

        loginButton.setOnClickListener {
            validateLogin()
        }
    }

    private fun validateLogin() {
        val emailOrPhone = loginEmailEditText.text.toString()
        val password = loginPasswordEditText.text.toString()

        val sharedPreferences: SharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)

        if (emailOrPhone == savedEmail && password == savedPassword) {
            // Сохраняем статус автоматического входа
            val editor = sharedPreferences.edit()
            editor.putBoolean("autoLogin", autoLoginCheckBox.isChecked)
            editor.apply()

            // Переход в ContentActivity
            startActivity(Intent(this, ContentActivity::class.java))
            finish()
        } else {
            // Ошибка входа
            Toast.makeText(this, "Неверный email/телефон или пароль", Toast.LENGTH_SHORT).show()
        }
    }
}