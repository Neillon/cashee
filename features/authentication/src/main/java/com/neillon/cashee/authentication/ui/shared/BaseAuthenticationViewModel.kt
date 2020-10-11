package com.neillon.cashee.authentication.ui.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

open class BaseAuthenticationViewModel: ViewModel() {

    suspend fun validEmail(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            val emailRegex = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")

            val matchesRegex = emailRegex.matcher(email).matches()
            val isNotNullOrEmpty = !email.isNullOrBlank()

            return@withContext isNotNullOrEmpty && matchesRegex
        }
    }
}