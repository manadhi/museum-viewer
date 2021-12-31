package com.udhipe.musemviewer.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udhipe.musemviewer.data.login.LoginRepository

import com.udhipe.musemviewer.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

//    private val _loginForm = MutableLiveData<LoginFormState>()
//    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _loginForm = MutableLiveData<LoginFormState>().apply {
        value = LoginFormState()
    }
    val loginFormState: LiveData<LoginFormState>
        get() = _loginForm

    fun login(username: String, password: String): Boolean {
//        loginResult.value =loginRepository.login(username, password)
        val result = loginRepository.login(username, password)
        _loginResult.value = result
        return result
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.length > 2
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}