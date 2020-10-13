package com.neillon.cashee.authentication.ui.login.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.usecase.login.LoginWithGoogleUseCase
import com.neillon.cashee.authentication.usecase.UseCase
import com.neillon.cashee.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authenticationUseCase: UseCase<LoginWithGoogleUseCase.Params, User>
) : ViewModel() {

    val handleLoginResult = SingleLiveEvent<User>()
    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loginWithGoogle(credential: AuthCredential) = viewModelScope.launch {
        try {
            val user =
                authenticationUseCase.execute(LoginWithGoogleUseCase.Params(credential = credential))
            handleLoginResult.value = user
            _error.value = null
        } catch (e: Exception) {
            _error.value = e.localizedMessage
        }
    }
}