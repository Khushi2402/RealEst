package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        supportActionBar?.hide()

        val back = findViewById<ImageButton>(R.id.back )
        val loadingDialog = LoadingDialog(this@EditProfile)

        back.setOnClickListener{
            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)
        }

    }
}