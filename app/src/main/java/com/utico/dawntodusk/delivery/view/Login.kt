package com.utico.dawntodusk.delivery.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.utico.dawntodusk.delivery.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
            btnLogin.setOnClickListener {
                val intent = Intent(this,OTPVerificationActivity::class.java)
                    startActivity(intent)
            }
    }
}