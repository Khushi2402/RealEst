package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ViewedProperties : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewed_properties)

        supportActionBar?.hide()

        val back = findViewById<ImageButton>(R.id.back )

        back.setOnClickListener{
            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)
        }

    }
}