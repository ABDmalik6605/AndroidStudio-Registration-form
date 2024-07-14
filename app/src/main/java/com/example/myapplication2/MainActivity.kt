package com.example.myapplication2

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var pressSubmitButton: Button
    private lateinit var sayYourName: EditText
    private lateinit var sayYourEmail: EditText
    private lateinit var sayYourPassword: EditText
    private lateinit var sayYourConfirm: EditText
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Your other code...

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
    }

    private fun setListeners() {
        pressSubmitButton.setOnClickListener {
            if (sayYourName.text.toString()=="" || sayYourEmail.text.toString()=="" || sayYourPassword.text.toString()=="" || sayYourConfirm.text.toString()==""){
                resultText.text="Please fill all the required fields"
                resultText.setTextColor(Color.RED)
            }
            else {
                val name = sayYourName.text.toString()
                var nameflag = true
                for (char in name) {
                    if (!char.isLetter()) {
                        nameflag = false
                        break
                    }
                }
                if (nameflag == false)
                    resultText.text = "Enter correct name"
                else {
                    val email = sayYourEmail.text.toString().trim()
                    if (!isValidEmail(email)) {
                        resultText.text = "Enter a valid email address"
                        resultText.setTextColor(Color.RED)
                    }
                    else {
                        if(sayYourPassword.text.toString().length<8){
                            resultText.text = "Password must have atleast 8 characters"
                        }
                        else {
                            val password=sayYourPassword.text.toString()
                            var boolDigit=containsDigit(password)
                            var boolSpecial=containsSpecialCharacter(password)
                            var boolCapital=containsUpperCase(password)
                            if (boolDigit && boolSpecial && boolCapital) {
                                if (sayYourPassword.text.toString() == sayYourConfirm.text.toString()) {
                                    resultText.text = "Thank you for registering"
                                } else {
                                    resultText.text = "Password do not match"
                                }
                            }
                            else{
                                resultText.text = "Password must contain atleast one digit,one special character and one capital letter"
                            }
                        }
                    }
                }
            }
        }
    }
    private fun isValidEmail(email: String): Boolean {
        val domains = arrayOf("@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com")
        val lowerCaseEmail = email.lowercase().trim()
        var hasValidDomain = false
        for (domain in domains) {
            if (lowerCaseEmail.endsWith(domain)) {
                if (lowerCaseEmail.length > domain.length) {
                    hasValidDomain = true
                    break
                }
            }
        }
        return hasValidDomain
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