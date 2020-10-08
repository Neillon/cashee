package com.neillon.cashee.authentication.ui.login.with_email

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.regex.Pattern.compile

class LoginEmailViewModel : ViewModel() {

    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email

    suspend fun validEmail(): Boolean {
        return withContext(Dispatchers.IO) {
            val text = _email.value.toString()
            val emailRegex = compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")

            val matchesRegex = emailRegex.matcher(text).matches()
            val isNotNullOrEmpty = !text.isNullOrBlank()

            return@withContext isNotNullOrEmpty && matchesRegex
        }
    }

    fun updateEmail(thisEmail: String) {
        _email.value = thisEmail
    }

}