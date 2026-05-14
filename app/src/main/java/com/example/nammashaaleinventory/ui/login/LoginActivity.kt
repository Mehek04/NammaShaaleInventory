package com.example.nammashaaleinventory.ui.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.databinding.ActivityLoginBinding
import com.example.nammashaaleinventory.databinding.DialogSignupBinding
import com.example.nammashaaleinventory.ui.dashboard.DashboardActivity
import com.example.nammashaaleinventory.utils.SessionManager
import com.example.nammashaaleinventory.utils.ThemeManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val themeManager = ThemeManager(this)
        ThemeManager.applyTheme(themeManager.isDarkMode())

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnLogin.setOnClickListener {
            performLogin()
        }

        binding.tvSignup.setOnClickListener {
            showSignupDialog()
        }
    }

    private fun performLogin() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val registeredUsers = sessionManager.getRegisteredUsers()
        val userPassword = registeredUsers[username]

        if (userPassword != null && userPassword == password) {
            sessionManager.setLogin(username)
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        } else if (username == "teacher" && password == "123456") {
            sessionManager.setLogin(username)
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSignupDialog() {
        val dialog = Dialog(this, R.style.CustomDialogTheme)
        val dialogBinding = DialogSignupBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.9).toInt(),
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialogBinding.btnCancelSignup.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.btnConfirmSignup.setOnClickListener {
            val newUsername = dialogBinding.etNewUsername.text.toString().trim()
            val newPassword = dialogBinding.etNewPassword.text.toString().trim()
            val secretKey = dialogBinding.etSecretKey.text.toString().trim()

            if (newUsername.isEmpty() || newPassword.isEmpty() || secretKey.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (secretKey != "SCHOOL2024") {
                Toast.makeText(this, "Invalid secret key!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sessionManager.registerUser(newUsername, newPassword)
            sessionManager.setLogin(newUsername)
            Toast.makeText(this, "Account created! Welcome!", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        dialog.show()
    }
}