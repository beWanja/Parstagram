package com.example.parstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(ParseUser.getCurrentUser() != null){
            goToMainActivity()
        }
        
        findViewById<Button>(R.id.login_button).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username, password)
        }
        findViewById<Button>(R.id.signup_button).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signupUser(username, password)
        }
    }

    private fun signupUser(username: String, password: String){
        // Create the ParseUser
        val user = ParseUser()

    // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // User has successfully signed up
                Toast.makeText(this, "Welcome to Parstagram!", Toast.LENGTH_SHORT).show()

                //TODO NAVIAgatE USER TO MAIN

            } else {
                Toast.makeText(this, "Error signing up", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successful!!")
                goToMainActivity()
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )

    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        const val TAG = "LoginActivity"
    }
}