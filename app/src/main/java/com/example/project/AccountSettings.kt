package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AccountSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)

        supportActionBar?.hide()

        val back = findViewById<ImageButton>(R.id.back )

        back.setOnClickListener{
            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)
        }

    }
}