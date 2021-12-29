package com.udhipe.musemviewer.data.login.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val userName: String,
    val password: String
)