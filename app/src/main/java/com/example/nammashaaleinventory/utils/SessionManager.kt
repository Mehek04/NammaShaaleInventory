package com.example.nammashaaleinventory.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    companion object {
        private const val PREF_NAME = "NammaShaalePrefs"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_USERNAME = "username"
        private const val KEY_REGISTERED_USERS = "registered_users"
    }

    fun setLogin(username: String) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_USERNAME, username)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getUsername(): String? {
        return prefs.getString(KEY_USERNAME, "Teacher")
    }

    fun logout() {
        editor.putBoolean(KEY_IS_LOGGED_IN, false)
        editor.apply()
    }

    fun registerUser(username: String, password: String) {
        val users = getRegisteredUsers().toMutableMap()
        users[username] = password
        val usersString = users.map { "${it.key}:${it.value}" }.joinToString(",")
        editor.putString(KEY_REGISTERED_USERS, usersString)
        editor.apply()
    }

    fun getRegisteredUsers(): Map<String, String> {
        val usersString = prefs.getString(KEY_REGISTERED_USERS, "") ?: ""
        if (usersString.isEmpty()) return emptyMap()
        return usersString.split(",").associate {
            val parts = it.split(":")
            parts[0] to parts[1]
        }
    }
}