package com.udhipe.musemviewer.data.login.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userName: String,
    val password: String,
)