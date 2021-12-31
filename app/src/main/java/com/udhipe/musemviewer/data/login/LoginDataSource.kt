package com.udhipe.musemviewer.data.login

import com.udhipe.musemviewer.data.SharedPreferencesManager

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private val sharedPreferencesManager = SharedPreferencesManager.instance

    fun checkSession(username: String): Boolean {
        val userName = sharedPreferencesManager.readSessionData()
        var isSessionExist = false

        if (userName != "") {
            isSessionExist = true
        }

        return isSessionExist
    }

    fun login(username: String, password: String): Boolean {
        var result = false
        val savedUser = sharedPreferencesManager.readData(username)

        if (savedUser != null) {
            if (savedUser.password == password) {
                sharedPreferencesManager.writeSessionData(username)
                result = true
            }
        }

        return result
    }

    fun logout() {
        sharedPreferencesManager.removeSessionData()
    }
}