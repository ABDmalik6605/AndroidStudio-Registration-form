package com.example.myapplication2

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var pressSubmitButton: Button
    private lateinit var sayYourName: EditText
    private lateinit var sayYourEmail: EditText
    private lateinit var sayYourPassword: EditText
    private lateinit var sayYourConfirm: EditText
    private lateinit var resultText: TextView
    private lateinit var toggleDarkModeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initUI()
        setListeners()
    }

    private fun initUI() {
        pressSubmitButton = findViewById(R.id.pressSubmitButton)
        sayYourName = findViewById(R.id.sayYourName)
        sayYourEmail = findViewById(R.id.sayYourEmail)
        sayYourPassword = findViewById(R.id.sayYourPassword)
        sayYourConfirm = findViewById(R.id.sayYourConfirm)
        resultText = findViewById(R.id.resultText)
        toggleDarkModeButton = findViewById(R.id.toggleDarkModeButton)
    }

    private fun setListeners() {
        pressSubmitButton.setOnClickListener {
            resultText.setTextColor(Color.RED)
            if (sayYourName.text.toString().isEmpty() || sayYourEmail.text.toString().isEmpty() ||
                sayYourPassword.text.toString().isEmpty() || sayYourConfirm.text.toString().isEmpty()) {
                resultText.text = "Please fill all the required fields"
            } else {
                val name = sayYourName.text.toString()
                var nameflag = true
                for (char in name) {
                    if (!char.isLetter()) {
                        nameflag = false
                        break
                    }
                }
                if (!nameflag)
                    resultText.text = "Enter correct name"
                else {
                    val email = sayYourEmail.text.toString().trim()
                    if (!isValidEmail(email)) {
                        resultText.text = "Enter a valid email address"
                    } else {
                        if (sayYourPassword.text.toString().length < 8) {
                            resultText.text = "Password must have at least 8 characters"
                        } else {
                            val password = sayYourPassword.text.toString()
                            val boolDigit = containsDigit(password)
                            val boolSpecial = containsSpecialCharacter(password)
                            val boolCapital = containsUpperCase(password)
                            if (boolDigit && boolSpecial && boolCapital) {
                                if (sayYourPassword.text.toString() == sayYourConfirm.text.toString()) {
                                    resultText.text = "Thank you for registering"
                                    resultText.setTextColor(Color.GREEN)
                                } else {
                                    resultText.text = "Passwords do not match"
                                }
                            } else {
                                resultText.text = "Password must contain at least one digit, one special character, and one capital letter"
                            }
                        }
                    }
                }
            }
        }
        toggleDarkModeButton.setOnClickListener {
            val currentMode = AppCompatDelegate.getDefaultNightMode()
            if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                toggleDarkModeButton.setTextColor(Color.RED)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val domains = arrayOf("@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com")
        val lowerCaseEmail = email.lowercase().trim()
        var flag = false
        for (domain in domains) {
            if (lowerCaseEmail.endsWith(domain)) {
                if (lowerCaseEmail.length > domain.length) {
                    flag = true
                    break
                }
            }
        }
        return flag
    }
    private fun containsUpperCase(password: String): Boolean {
        for (char in password) {
            if (char.isUpperCase()) {
                return true
            }
        }
        return false
    }
    private fun containsSpecialCharacter(password: String): Boolean {
        for (char in password) {
            if (!char.isLetterOrDigit()) {
                return true
            }
        }
        return false
    }
    private fun containsDigit(password: String): Boolean {
        for (char in password) {
            if (char.isDigit()) {
                return true
            }
        }
        return false
    }
}