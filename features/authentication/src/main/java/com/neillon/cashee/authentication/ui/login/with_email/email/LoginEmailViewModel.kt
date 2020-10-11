package com.neillon.cashee.authentication.ui.login.with_email.email

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neillon.cashee.authentication.ui.shared.BaseAuthenticationViewModel

class LoginEmailViewModel : BaseAuthenticationViewModel() {

    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email

    fun updateEmail(thisEmail: String) {
        _email.value = thisEmail
    }


}