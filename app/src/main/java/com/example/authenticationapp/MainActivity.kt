package com.example.authenticationapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser
        if (user == null) {
            navigateToLogin()
        }

        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        welcomeText.text = "Welcome"

        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        auth.signOut()
        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        // Prevent back navigation from main activity to login
        if (auth.currentUser == null) {
            super.onBackPressed()
        }
    }

}
