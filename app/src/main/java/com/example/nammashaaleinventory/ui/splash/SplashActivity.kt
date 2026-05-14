package com.example.nammashaaleinventory.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.ui.login.LoginActivity
import com.example.nammashaaleinventory.utils.SessionManager
import com.example.nammashaaleinventory.utils.ThemeManager

class SplashActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply saved theme BEFORE setContentView
        themeManager = ThemeManager(this)
        ThemeManager.applyTheme(themeManager.isDarkMode())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sessionManager = SessionManager(this)

        // Always go to login (ask every time)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}