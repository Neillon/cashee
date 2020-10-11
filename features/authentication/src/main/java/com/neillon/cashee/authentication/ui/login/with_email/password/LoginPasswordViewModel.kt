package com.neillon.cashee.authentication.ui.login.with_email.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.cashee.authentication.usecase.login.LoginWithEmailAndPasswordUseCase
import kotlinx.coroutines.launch

class LoginPasswordViewModel(
    private val loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase
) : ViewModel() {

    fun loginWithEmail(email: String, password: String) = viewModelScope.launch {
        val params = LoginWithEmailAndPasswordUseCase.Params(email, password)
        loginWithEmailAndPasswordUseCase.execute(params)
    }
}