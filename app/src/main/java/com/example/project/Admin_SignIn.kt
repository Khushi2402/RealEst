package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Admin_SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_sign_in)

        supportActionBar?.hide()

        auth = Firebase.auth

        // to go to sign up page
        val signup = findViewById<TextView>(R.id.tv5 )
        val loadingDialog = LoadingDialog(this@Admin_SignIn)

        signup.setOnClickListener{
            val intent = Intent(this, Admin_SignUp::class.java)
            startActivity(intent)
        }

        val signInButton = findViewById<Button>(R.id.btn1)

        signInButton.setOnClickListener{
            loadingDialog.startLoadingDialog()
            val handler = Handler()
            handler.postDelayed({
                loadingDialog.dismissDialog()
            }, 5000)
            performSignIn()
        }
    }



    private fun performSignIn() {
        val inEmail = findViewById<EditText>(R.id.signIn_ed1)
        val inPass = findViewById<EditText>(R.id.signIn_ed2)

        if (inEmail.text.isEmpty() || inPass.text.isEmpty()){
            Toast.makeText(this, "Please fill all the fields",
                Toast.LENGTH_SHORT).show()
        }

        val inputEmailIn = inEmail.text.toString()
        val inputPassIn = inPass.text.toString()

        auth.signInWithEmailAndPassword(inputEmailIn, inputPassIn)
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

            .addOnFailureListener{
                Toast.makeText(baseContext, "Authentication failed. ${it.localizedMessage}",
                    Toast.LENGTH_SHORT).show()
            }

    }
}