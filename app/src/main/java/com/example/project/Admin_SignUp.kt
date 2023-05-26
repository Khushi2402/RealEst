package com.example.project

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class Admin_SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_sign_up)

        supportActionBar?.hide()

        auth = Firebase.auth

        val signin = findViewById<TextView>(R.id.tv7)
        val loadingDialog = LoadingDialog(this@Admin_SignUp)

        signin.setOnClickListener {
            val intent = Intent(this, Admin_SignIn::class.java)
            startActivity(intent)
        }

        val signUpButton = findViewById<Button>(R.id.admin_btn1)

        signUpButton.setOnClickListener {
            loadingDialog.startLoadingDialog()
            val handler = Handler()
            handler.postDelayed({
                loadingDialog.dismissDialog()
            }, 5000)
            performSignUp()
        }
    }
    private fun performSignUp() {
        val email = findViewById<EditText>(R.id.ed1)
        val password = findViewById<EditText>(R.id.ed2)
        val con_password = findViewById<EditText>(R.id.ed3)

        if (email.text.isEmpty() || password.text.isEmpty() || con_password.text.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields",
                Toast.LENGTH_SHORT).show()
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()
        val con_inputPassword = con_password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Success.",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

            .addOnFailureListener {
                Toast.makeText(this, "Error Occurred ${it.localizedMessage}",
                    Toast.LENGTH_SHORT).show()
            }
    }
}