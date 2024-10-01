package com.example.myfirstapp

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var byPhoneButton: Button
    private lateinit var byEmailButton: Button

    private var isPhoneSelected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)
        byPhoneButton = findViewById(R.id.by_phone_button)
        byEmailButton = findViewById(R.id.by_email_button)

        // Установим начальные значения
        setPhoneMode()

        byPhoneButton.setOnClickListener {
            setPhoneMode()
        }

        byEmailButton.setOnClickListener {
            setEmailMode()
        }

        registerButton.setOnClickListener {
            validateFields()
        }
    }

    private fun setPhoneMode() {
        isPhoneSelected = true
        byPhoneButton.setTextColor(ContextCompat.getColor(this, R.color.purple_500))
        byEmailButton.setTextColor(ContextCompat.getColor(this, R.color.black))
        emailEditText.hint = "Введите номер телефона"
        emailEditText.inputType = InputType.TYPE_CLASS_PHONE
    }

    private fun setEmailMode() {
        isPhoneSelected = false
        byPhoneButton.setTextColor(ContextCompat.getColor(this, R.color.black))
        byEmailButton.setTextColor(ContextCompat.getColor(this, R.color.purple_500))
        emailEditText.hint = "Введите email"
        emailEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    private fun validateFields() {
        val emailOrPhone = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (isPhoneSelected) {
            if (!emailOrPhone.startsWith("+")) {
                Toast.makeText(this, "Номер телефона должен начинаться с +", Toast.LENGTH_SHORT).show()
                return
            }
        } else {
            if (!emailOrPhone.contains("@")) {
                Toast.makeText(this, "Email должен содержать символ @", Toast.LENGTH_SHORT).show()
                return
            }
        }

        if (password.length < 8) {
            Toast.makeText(this, "Пароль должен содержать минимум 8 символов", Toast.LENGTH_SHORT).show()
            return
        }

        // Если все корректно, не происходит никаких действий
    }
}