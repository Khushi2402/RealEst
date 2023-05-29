package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class EditProfile : AppCompatActivity() {

    lateinit var etname: EditText
    lateinit var ettitle: EditText
    lateinit var etemail: EditText
    lateinit var etphone: EditText
    lateinit var etpassword: EditText
    lateinit var etlocation: EditText
    lateinit var btnsave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        supportActionBar?.hide()

        val back = findViewById<ImageButton>(R.id.back )

        back.setOnClickListener{
            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)
        }

        etname = findViewById(R.id.edit_name)
        ettitle = findViewById(R.id.edit_title)
        etemail = findViewById(R.id.edit_email)
        etphone = findViewById(R.id.edit_phone)
        etpassword = findViewById(R.id.edit_password)
        etlocation = findViewById(R.id.edit_location)
        btnsave = findViewById(R.id.save)

        btnsave.setOnClickListener {
            saveDetails()
        }
    }

    private fun saveDetails() {
        val name = etname.text.toString()
        val title = ettitle.text.toString()
        val email = etemail.text.toString()
        val phone = etphone.text.toString()
        val password = etpassword.text.toString()
        val location = etlocation.text.toString()

        val intent = Intent(this, ProfileFragment::class.java)
        intent.putExtra("name", name)
        intent.putExtra("title", title)
        intent.putExtra("email", email)
        intent.putExtra("phone", phone)
        intent.putExtra("password", password)
        intent.putExtra("location", location)

        startActivity(intent)
    }
}