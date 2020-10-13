package com.neillon.cashee.authentication.ui.login.with_email.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.cashee.authentication.usecase.login.LoginWithEmailAndPasswordUseCase
import com.neillon.cashee.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginPasswordViewModel(
    private val loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase
) : ViewModel() {

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error
    val handleLoginSuccess = SingleLiveEvent<Boolean>()

    fun loginWithEmail(email: String, password: String) = viewModelScope.launch {
        try {
            val params = LoginWithEmailAndPasswordUseCase.Params(email, password)
            loginWithEmailAndPasswordUseCase.execute(params)
            _error.value = null
            handleLoginSuccess.value = true
        } catch (e: Exception) {
            _error.value = e.localizedMessage
        }
    }
}