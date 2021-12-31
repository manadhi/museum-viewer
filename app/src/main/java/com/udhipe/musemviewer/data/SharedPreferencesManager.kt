package com.udhipe.musemviewer.data

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import com.udhipe.musemviewer.ThisApplication
import com.udhipe.musemviewer.data.login.model.LoggedInUser

class SharedPreferencesManager {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private var encryptedPreferences: EncryptedSharedPreferences? = null
    private var encryptedPreferencesSession: EncryptedSharedPreferences? = null

    companion object {
        val USERNAME = "username"

        val instance by lazy { SharedPreferencesManager() }
    }

    fun init() {
        encryptedPreferences = EncryptedSharedPreferences.create(
            "mv_data", // fileName
            masterKeyAlias,
            ThisApplication.getThisAppContext(), // context
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // prefKeyEncryptionScheme
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // prefvalueEncryptionScheme
        ) as EncryptedSharedPreferences

        encryptedPreferencesSession = EncryptedSharedPreferences.create(
            "mv_session", // fileName
            masterKeyAlias,
            ThisApplication.getThisAppContext(), // context
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // prefKeyEncryptionScheme
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // prefvalueEncryptionScheme
        ) as EncryptedSharedPreferences

    }

    fun writeSessionData(username: String) {
        encryptedPreferencesSession?.edit()?.apply {
            putString(USERNAME, username)
        }?.apply()
    }

    fun readSessionData(): String? {
        return encryptedPreferencesSession?.getString(USERNAME, "")
    }

    fun removeSessionData() {
        encryptedPreferencesSession?.edit()?.apply {
            putString(USERNAME, "")
        }?.apply()
    }

    fun writeData(key: String, value: LoggedInUser) {
        val gson = Gson()
        val stringValue = gson.toJson(value)

        encryptedPreferences?.edit()?.apply {
            putString(key, stringValue)
        }?.apply()
    }

    fun readData(key: String): LoggedInUser? {
        var user: LoggedInUser? = null
        val gson = Gson()
        val stringValue = encryptedPreferences!!.getString(key, "")!!
        if (stringValue != "") {
            user = gson.fromJson(stringValue, LoggedInUser::class.java)
        }

        return user
    }

}