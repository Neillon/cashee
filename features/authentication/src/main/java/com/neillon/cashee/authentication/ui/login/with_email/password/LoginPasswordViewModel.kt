package com.neillon.cashee.authentication.ui.login.with_email.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.usecase.UseCase
import com.neillon.cashee.authentication.usecase.login.LoginWithEmailAndPasswordUseCase
import com.neillon.cashee.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginPasswordViewModel(
    private val loginWithEmailAndPasswordUseCase: UseCase<LoginWithEmailAndPasswordUseCase.Params, User>
) : ViewModel() {

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error
    private val _loadingLogin = MutableLiveData(false)
    val loadingLogin: LiveData<Boolean> = _loadingLogin

    val handleLoginSuccess = SingleLiveEvent<Boolean>()

    fun loginWithEmail(email: String, password: String) = viewModelScope.launch {
        _loadingLogin.value = true
        try {
            val params = LoginWithEmailAndPasswordUseCase.Params(email, password)
            val user = loginWithEmailAndPasswordUseCase.execute(params)

            handleLoginSuccess.value = true
        } catch (e: Exception) {
            _error.value = e.localizedMessage
        } finally {
            _loadingLogin.value = false
        }
    }
}