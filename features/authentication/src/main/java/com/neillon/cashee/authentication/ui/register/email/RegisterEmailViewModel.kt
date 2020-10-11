package com.neillon.cashee.authentication.ui.register.email

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neillon.cashee.authentication.ui.shared.BaseAuthenticationViewModel

class RegisterEmailViewModel: BaseAuthenticationViewModel() {
    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email

    fun updateEmail(thisEmail: String) {
        _email.value = thisEmail
    }
}