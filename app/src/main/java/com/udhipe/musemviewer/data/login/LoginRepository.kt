package com.udhipe.musemviewer.data.login

import com.udhipe.musemviewer.data.login.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: String? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    // okay
    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Boolean {
        // handle login
        val result = dataSource.login(username, password)

        if (result) {
            setLoggedInUser(username)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: String) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}