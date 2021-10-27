package com.utico.dawntodusk.delivery.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.utico.dawntodusk.delivery.R

class OTPVerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)
        val btnVerify = findViewById<Button>(R.id.btnVerify)
            btnVerify.setOnClickListener {
                val intent = Intent(this,DashboardActivity::class.java)
                    startActivity(intent)
            }
    }
}